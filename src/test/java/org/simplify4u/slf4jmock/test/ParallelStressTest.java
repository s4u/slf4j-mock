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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.plugins.MockitoLogger;
import org.simplify4u.slf4jmock.LoggerMock;
import org.simplify4u.slf4jmock.MDCMock;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.slf4j.spi.MDCAdapter;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ParallelStressTest {

    static abstract class TestCommon {

        static Stream<String> randomValues() {
            Random random = new Random();
            return IntStream.generate(() -> random.nextInt(100))
                    .limit(100)
                    .mapToObj(i -> "value-" + i);
        }

        @Mock
        MDCAdapter mdcMock;

        @Mock
        Logger logger;

        @InjectMocks
        Example sut;

        @ParameterizedTest
        @MethodSource("randomValues")
        void getMethodWithStub(String value) {

            when(mdcMock.get("key")).thenReturn(value);

            assertThat(MDC.get("key")).isEqualTo(value);

            verify(mdcMock).get("key");
            verifyNoMoreInteractions(mdcMock);
        }

        @ParameterizedTest
        @MethodSource("randomValues")
        void logInfoShouldBeLogged(String value) {

            // when
            sut.methodWithLogInfo(value);

            // then
            verify(logger).info(value);
            verifyNoMoreInteractions(logger);
        }

    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    class WithMockitoExtension extends TestCommon {
        // Mockito will do job for us
    }

    @Nested
    class ManualMock extends TestCommon {

        @BeforeEach
        void setup() {
            mdcMock = Mockito.mock(MDCAdapter.class);
            MDCMock.setMock(mdcMock);

            sut = new Example();
            logger = Mockito.mock(Logger.class);
            LoggerMock.setMock(Example.class, logger);
        }

        @AfterEach
        void cleanup() {
            MDCMock.clearMock();
            LoggerMock.clearMock(Example.class);
        }
    }
}
