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

package org.simplify4u.slf4jmock.test;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.simplify4u.slf4jmock.LoggerMock;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExampleTest {

    private static final String INFO_TEST_MESSAGE = "info log test message";
    private static final String WARN_TEST_MESSAGE = "warn log test message";
    private static final String DEBUG_TEST_MESSAGE = "debug log test message";
    private static final String DEBUG_TEST_FORMAT = "Debug: {}";

    static abstract class ExampleTestCommon {

        @Mock
        Logger logger;

        @InjectMocks
        Example sut;


        @Test
        void logDebugShouldNotBeLogged() {

            // given
            when(logger.isDebugEnabled()).thenReturn(false);

            // when
            sut.methodWithLogDebug(DEBUG_TEST_FORMAT, DEBUG_TEST_MESSAGE);

            // then
            verify(logger).isDebugEnabled();
            verifyNoMoreInteractions(logger);
        }

        @Test
        void logDebugShouldBeLogged() {

            // given
            when(logger.isDebugEnabled()).thenReturn(true);

            // when
            sut.methodWithLogDebug(DEBUG_TEST_FORMAT, DEBUG_TEST_MESSAGE);

            // then
            verify(logger).isDebugEnabled();
            verify(logger).debug(DEBUG_TEST_FORMAT, DEBUG_TEST_MESSAGE);
            verifyNoMoreInteractions(logger);
        }

        @Test
        void logInfoShouldBeLogged() {

            // when
            sut.methodWithLogInfo(INFO_TEST_MESSAGE);

            // then
            verify(logger).info(INFO_TEST_MESSAGE);
            verifyNoMoreInteractions(logger);
        }

        @Test
        void logWarnShouldBeLoggedTwice() {

            // when
            sut.methodWithLogWarn(WARN_TEST_MESSAGE);

            // then
            verify(logger, times(2)).warn(WARN_TEST_MESSAGE);
            verifyNoMoreInteractions(logger);
        }

        @Test
        void logError10Times() {

            // when
            sut.logError100();

            // then
            verify(logger, times(10)).error(anyString(), any(Object.class));
            verifyNoMoreInteractions(logger);
        }

        @Test
        void shouldLogProperLocationOnVerify() {

            sut.methodWithLogInfo(WARN_TEST_MESSAGE);

            Assertions.assertThatThrownBy(() -> verify(logger).warn(WARN_TEST_MESSAGE))
                    .hasMessageContaining("-> at org.simplify4u.slf4jmock.test.Example.methodWithLogInfo");
        }
    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    class WithMockitoExtension extends ExampleTestCommon {
        // Mockito will do job for us
    }

    @Nested
    class ManualMock extends ExampleTestCommon {

        @BeforeEach
        void setup() {
            logger = Mockito.mock(Logger.class);
            LoggerMock.setMock(Example.class.getName(), logger);
        }

        @AfterEach
        void cleanup() {
            LoggerMock.clearMock(Example.class.getName());
        }
    }
}
