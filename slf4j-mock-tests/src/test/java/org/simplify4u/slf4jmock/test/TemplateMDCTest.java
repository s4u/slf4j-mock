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
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.simplify4u.slf4jmock.MDCMock;
import org.slf4j.MDC;
import org.slf4j.spi.MDCAdapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class TemplateMDCTest {

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    static class MDCTestCommon {

        @Mock
        MDCAdapter mdcMock;

        @Test
        void noMDCInteraction() {
            verifyNoInteractions(mdcMock);
        }

        @Test
        void verifyPut() {

            MDC.put("key", "value");

            verify(mdcMock).put("key", "value");
            verifyNoMoreInteractions(mdcMock);
        }

        @Test
        @Order(1)
        void getMethodWithStub() {

            when(mdcMock.get("key")).thenReturn("value");

            assertThat(MDC.get("key")).isEqualTo("value");

            verify(mdcMock).get("key");
            verifyNoMoreInteractions(mdcMock);
        }

        @Test
        @Order(2)
        void getMethodWithOutStub() {

            // we have active stubbing from previous test
            assertThat(MDC.get("key")).isNull();

            verify(mdcMock).get("key");
            verifyNoMoreInteractions(mdcMock);
        }

    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    class WithMockitoExtension extends MDCTestCommon {
        // Mockito will do job for us
    }

    @Nested
    class ManualMock extends MDCTestCommon {

        @BeforeEach
        void setup() {
            mdcMock = Mockito.mock(MDCAdapter.class);
            MDCMock.setMock(mdcMock);
        }

        @AfterEach
        void cleanup() {
            MDCMock.clearMock();
        }
    }
}
