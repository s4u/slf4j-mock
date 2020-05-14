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

package org.simplify4u.sl4jmock.test;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.simplify4u.sjf4jmock.LoggerMock;
import org.slf4j.Logger;

public class ExampleTest {

    private static final String INFO_TEST_MESSAGE = "info log test message";
    private static final String WARN_TEST_MESSAGE = "warn log test message";
    private static final String DEBUG_TEST_MESSAGE = "debug log test message";
    private static final String DEBUG_TEST_FORMAT = "Debug: {}";

    private Example sut;

    @Before
    public void setup() {
        LoggerMock.clearInvocations();
        sut = new Example();
    }


    @Test
    public void logDebugShouldNotBeLogged() {

        // given
        Logger logger = LoggerMock.getLoggerMock(Example.class);
        when(logger.isDebugEnabled()).thenReturn(false);

        // when
        sut.methodWithLogDebug(DEBUG_TEST_FORMAT, DEBUG_TEST_MESSAGE);

        // then
        verify(logger).isDebugEnabled();
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void logDebugShouldBeLogged() {

        // given
        Logger logger = LoggerMock.getLoggerMock(Example.class);
        when(logger.isDebugEnabled()).thenReturn(true);

        // when
        sut.methodWithLogDebug(DEBUG_TEST_FORMAT, DEBUG_TEST_MESSAGE);

        // then
        verify(logger).isDebugEnabled();
        verify(logger).debug(DEBUG_TEST_FORMAT, DEBUG_TEST_MESSAGE);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void logInfoShouldBeLogged() {

        // when
        sut.methodWithLogInfo(INFO_TEST_MESSAGE);

        // then
        Logger logger = LoggerMock.getLoggerMock(Example.class);
        verify(logger).info(INFO_TEST_MESSAGE);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void logWarnShouldBeLoggedTwice() {

        // when
        sut.methodWithLogWarn(WARN_TEST_MESSAGE);

        // then
        Logger logger = LoggerMock.getLoggerMock(Example.class.getName());
        verify(logger, times(2)).warn(WARN_TEST_MESSAGE);
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void logError10Times() {

        // when
        sut.logError100();

        // then
        Logger logger = LoggerMock.getLoggerMock(Example.class.getName());
        verify(logger, times(10)).error(anyString(), any(Object.class));
        verifyNoMoreInteractions(logger);
    }

}
