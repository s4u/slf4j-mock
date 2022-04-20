/*
 * Copyright 2020 Slawomir Jaranowski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.simplify4u.slf4jmock.mockito;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.configuration.InjectingAnnotationEngine;
import org.mockito.internal.util.reflection.InstanceField;
import org.simplify4u.slf4jmock.LoggerMock;
import org.simplify4u.slf4jmock.MDCMock;
import org.simplify4u.slf4jmock.ProxyMock;
import org.simplify4u.slf4jmock.SimpleLogger;
import org.slf4j.Logger;
import org.slf4j.spi.MDCAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Extension for Mockito Annotation Engine.
 * <p>
 * Shouldn't be used directly.
 */
@SuppressWarnings("java:S1312") // Loggers should be "private static final" and should share a naming convention
public final class LoggerAnnotationEngine extends InjectingAnnotationEngine {

    private static final Logger FAKE_LOGGER = new SimpleLogger("");

    @Override
    public AutoCloseable process(Class<?> clazz, Object testInstance) {

        InstanceField spyToSet = prepareSpyLogger(testInstance);

        AutoCloseable processResult = super.process(clazz, testInstance);

        Optional<MDCAdapter> mdcMoc = findMDCMock(testInstance);
        mdcMoc.ifPresent(MDCMock::setMock);

        Map<String, Logger> loggersMocks = findLoggersMocks(testInstance, spyToSet);

        loggersMocks.forEach(LoggerMock::setMock);

        return () -> {
            processResult.close();
            MDCMock.clearMock();
            loggersMocks.keySet().forEach(LoggerMock::clearMock);
        };
    }

    private static List<InstanceField> allDeclaredFieldsOf(Object testInstance) {
        List<InstanceField> result = new ArrayList<>();
        for (Class<?> clazz = testInstance.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
                result.add(new InstanceField(field, testInstance));
            }
        }
        return result;
    }

    private static InstanceField prepareSpyLogger(Object testInstance) {

        List<InstanceField> spyMocks = allDeclaredFieldsOf(testInstance)
                .stream()
                .filter(field -> field.jdkField().getType() == Logger.class)
                .filter(field -> field.isAnnotatedBy(Spy.class))
                .collect(Collectors.toList());

        // find uninitialized spy logger
        List<InstanceField> nullSpy = spyMocks.stream()
                .filter(field -> field.read() == null)
                .collect(Collectors.toList());

        // set fake logger for spy
        // in next step real spy will be prepared
        nullSpy.forEach(field -> field.set(FAKE_LOGGER));

        return nullSpy.stream().findFirst().orElse(null);
    }

    private static Optional<MDCAdapter> findMDCMock(Object testInstance) {
        List<InstanceField> mdcFields = allDeclaredFieldsOf(testInstance)
                .stream()
                .filter(instanceField -> instanceField.jdkField().getType() == MDCAdapter.class)
                .filter(instanceField -> instanceField.isAnnotatedBy(Mock.class))
                .collect(Collectors.toList());

        if (mdcFields.size() > 1) {
            throw new MockRuntimeException("Class:  " + testInstance.getClass() + "\n"
                    + "contains more then one mock for MDCAdapter\n\n"
                    + mdcFields.stream()
                    .map(InstanceField::name)
                    .map(n -> "    @Mock\n    MDCAdapter " + n + ";")
                    .collect(Collectors.joining("\n\n"))
                    + "\n\nonly one or zero is allowed per unit test");
        }

        return mdcFields.stream().findFirst().map(InstanceField::read).map(MDCAdapter.class::cast);
    }

    private static List<InstanceField> findClassUnderTest(Object testInstance) {
        return allDeclaredFieldsOf(testInstance)
                .stream()
                .filter(instanceField -> instanceField.isAnnotatedBy(InjectMocks.class))
                .collect(Collectors.toList());
    }

    private static Map<String, Logger> findLoggersMocks(Object testInstance, InstanceField spyToSet) {

        Map<String, Logger> loggerMocks = allDeclaredFieldsOf(testInstance)
                .stream()
                .filter(field -> field.jdkField().getType() == Logger.class)
                .filter(field -> field.isAnnotatedBy(Mock.class) || field.isAnnotatedBy(Spy.class))
                .collect(Collectors.toMap(LoggerAnnotationEngine::getLoggerName,
                        LoggerAnnotationEngine::getLoggerFromFiled,
                        LoggerAnnotationEngine::mockLoggerMerge));

        if (!loggerMocks.containsKey("")) {
            // all mock have name
            return loggerMocks;
        }

        List<InstanceField> classesUnderTest = findClassUnderTest(testInstance);

        if (classesUnderTest.isEmpty()) {
            throw new MockRuntimeException("We have unnamed Logger for @Mock or @Spy"
                    + " we need at lease one class under test with @InjectMock in order to discover Logger name");
        }

        List<InstanceField> loggersUnderTest = classesUnderTest.stream()
                .map(InstanceField::read)
                .map(LoggerAnnotationEngine::allDeclaredFieldsOf)
                .flatMap(List::stream)
                .filter(field -> field.jdkField().getType() == Logger.class)
                .collect(Collectors.toList());

        if (loggersUnderTest.isEmpty()) {
            throw new MockRuntimeException("Classes under test doesn't have defined Logger");
        }

        if (loggersUnderTest.size() > 1) {
            throw new MockRuntimeException("Classes under test has define to many Loggers\n"
                    + "We need one Logger defined in all class under test for unnamed @Mock or @Spy");
        }

        InstanceField instanceField = loggersUnderTest.get(0);
        String loggerName = ((ProxyMock) instanceField.read()).getMockName();

        Logger oldLogger = loggerMocks.remove("");
        Logger newLogger;
        if (Mockito.mockingDetails(oldLogger).isSpy()) {
            // spy Logger without name ... we must create new
            newLogger = Mockito.spy(new SimpleLogger(loggerName));
            Optional.ofNullable(spyToSet).ifPresent(field -> field.set(newLogger));
        } else {
            newLogger = oldLogger;
        }

        loggerMocks.put(loggerName, newLogger);

        return loggerMocks;
    }

    private static Logger mockLoggerMerge(Logger l1, Logger l2) {
        throw new MockRuntimeException("Logger Mock must have unique name, "
                + " but we have: " + l1 + " and " + l2 + "\n"
                + "please define like:\n   @Mock(name=\"logger.name\")");
    }

    private static String getLoggerName(InstanceField field) {

        String name = Optional.ofNullable(field.annotation(Mock.class))
                .map(Mock::name)
                .orElse(null);

        if (name == null) {
            // get name from spy logger
            Optional<Logger> logger = Optional.ofNullable(field.read()).map(Logger.class::cast);
            name = logger.map(Logger::getName).orElse(null);
            logger.ifPresent(Mockito::clearInvocations);
        }

        return name;
    }

    private static Logger getLoggerFromFiled(InstanceField field) {
        return (Logger) field.read();
    }
}
