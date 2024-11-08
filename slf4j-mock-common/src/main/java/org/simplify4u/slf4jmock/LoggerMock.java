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
package org.simplify4u.slf4jmock;

import org.simplify4u.slf4jmock.internal.ProxyMock;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

/**
 * SLF4J Logger mock service.
 */
public final class LoggerMock {

    private static final ILoggerFactory loggerFactory = LoggerMock::getLoggerProxyByName;

    private static final Map<String, Logger> loggers = new HashMap<>();

    private LoggerMock() {
        // Utility classes
    }

    private static Logger createNewLoggerProxy(String name) {
        return (Logger) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[] {Logger.class, ProxyMock.class},
                new MockInvocationHandler(name, () ->
                        mock(SimpleLogger.class, withSettings().spiedInstance(new SimpleLogger(name))
                                .defaultAnswer(CALLS_REAL_METHODS).stubOnly())
                )
        );
    }

    private static Logger getLoggerProxyByName(String name) {
        synchronized (loggers) {
            return loggers.computeIfAbsent(name, LoggerMock::createNewLoggerProxy);
        }
    }

    private static ProxyMock getProxyByName(String name) {
        return (ProxyMock) getLoggerProxyByName(name);
    }

    public static ILoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    /**
     * Set Mock for given Logger at current thread.
     *
     * @param klass Logger class
     * @param mock Mock to assign to Logger
     */
    public static void setMock(Class<?> klass, Logger mock) {
        setMock(klass.getName(), mock);
    }

    /**
     * Set Mock for given Logger at current thread.
     *
     * @param name Logger name
     * @param mock Mock to assign to Logger
     */

    public static void setMock(String name, Logger mock) {
        getProxyByName(name).setMock(mock);
    }

    /**
     * Clear Mock for Logger at current thread.
     *
     * @param klass Logger class
     */
    public static void clearMock(Class<?> klass) {
        clearMock(klass.getName());
    }

    /**
     * Clear Mock for Logger at current thread.
     *
     * @param name Logger name
     */
    public static void clearMock(String name) {
        getProxyByName(name).clearMock();
    }
}
