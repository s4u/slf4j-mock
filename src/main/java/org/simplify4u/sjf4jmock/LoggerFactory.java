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
package org.simplify4u.sjf4jmock;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class LoggerFactory implements ILoggerFactory {

    private static final Logger LOGGER = new SimpleLogger(LoggerFactory.class.getName());

    private Map<String, Logger> loggers = new HashMap<>();

    /**
     * New class should be create by package member only.
     */
    LoggerFactory() {
    }

    @Override
    public Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, this::createNewLoggerMock);
    }

    @SuppressWarnings("java:S1312") // Loggers should be "private static final" and should share a naming convention
    private Logger createNewLoggerMock(String name) {

        LOGGER.info("Create mock for logger: {}", name);

        Logger mock = mock(Logger.class);
        when(mock.getName()).thenReturn(name);

        new DelegateMockToSimpleLogger(name).delegate(mock);

        return mock;
    }

    void clearInvocations() {
        loggers.forEach((name, logger) -> Mockito.clearInvocations(logger));
    }
}
