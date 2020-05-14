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

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * SLF4J Logger mock service.
 */
public class LoggerMock implements LoggerFactoryBinder {

    private static final LoggerFactory loggerFactory = new LoggerFactory();
    private static final String LOGGER_FACTORY_CLASS_STR = LoggerFactory.class.getName();

    @Override
    public ILoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    @Override
    public String getLoggerFactoryClassStr() {
        return LOGGER_FACTORY_CLASS_STR;
    }

    /**
     * Get mock for logger.
     *
     * @param klass Class for logger mock
     * @return logger mock
     */
    public static Logger getLoggerMock(Class<?> klass) {
        return loggerFactory.getLogger(klass.getName());
    }

    /**
     * Get mock for logger.
     *
     * @param name Class name for logger mock
     * @return logger mock
     */
    public static Logger getLoggerMock(String name) {
        return loggerFactory.getLogger(name);
    }

    /**
     * Clear invocation on all Logger mocks.
     */
    public static void clearInvocations() {
        loggerFactory.clearInvocations();
    }
}
