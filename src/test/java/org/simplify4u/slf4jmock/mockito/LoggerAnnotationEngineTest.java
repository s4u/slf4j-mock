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

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.MDCAdapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LoggerAnnotationEngineTest {

    @Test
    void classWithNoAnnotationShouldBeProcessed() throws Exception {

        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(new Object());

        assertThat(autoCloseable).isNotNull();
        autoCloseable.close();
    }

    static class TwoMDC {

        @Mock
        private MDCAdapter mdcAdapter1;

        @Mock
        private MDCAdapter mdcAdapter2;
    }

    @Test
    void twoMDCMockShouldThrowException() {

        TwoMDC testClass = new TwoMDC();

        assertThatThrownBy(() -> MockitoAnnotations.openMocks(testClass))
                .isExactlyInstanceOf(MockRuntimeException.class)
                .hasMessageContaining("contains more then one mock for MDCAdapter");
    }

    static class LoggerMockWithOutClassUnderTest {

        @Mock
        private Logger logger1;
    }


    @Test
    void noClassUnderTestAndMockWithOutNameThrowException() {
        Object classUnderTest = new LoggerMockWithOutClassUnderTest();

        assertThatThrownBy(() -> MockitoAnnotations.openMocks(classUnderTest))
                .isExactlyInstanceOf(MockRuntimeException.class)
                .hasMessageContaining("We have unnamed Logger for @Mock or @Spy");
    }

    static class LoggerMockkWithTwoUnnamedMocks {

        @Mock
        private Logger logger1;

        @Mock
        private Logger logger2;
    }

    @Test
    void loggerMockMustHaveUniqueName() {
        Object classUnderTest = new LoggerMockkWithTwoUnnamedMocks();

        assertThatThrownBy(() -> MockitoAnnotations.openMocks(classUnderTest))
                .isExactlyInstanceOf(MockRuntimeException.class)
                .hasMessageContaining("Logger Mock must have unique name");
    }

    static class TestWithClassUnderTestWitOutLogger {

        @Mock
        private Logger logger1;

        @InjectMocks
        private Object cut;
    }

    @Test
    void classUnderTestWitOutLogger() {
        Object classUnderTest = new TestWithClassUnderTestWitOutLogger();

        assertThatThrownBy(() -> MockitoAnnotations.openMocks(classUnderTest))
                .isExactlyInstanceOf(MockRuntimeException.class)
                .hasMessageContaining("Classes under test doesn't have defined Logger");
    }

    static class ClassUnderTestTwoLogger {
        private static final Logger LOGGER1 = LoggerFactory.getLogger("Logger1");
        private static final Logger LOGGER2 = LoggerFactory.getLogger("Logger2");
    }

    static class TestWithClassUnderTestTwoLogger {

        @Mock
        private Logger logger1;

        @InjectMocks
        private ClassUnderTestTwoLogger cut;
    }

    @Test
    void classUnderTestTwoLogger() {
        Object classUnderTest = new TestWithClassUnderTestTwoLogger();

        assertThatThrownBy(() -> MockitoAnnotations.openMocks(classUnderTest))
                .isExactlyInstanceOf(MockRuntimeException.class)
                .hasMessageContaining("Classes under test has define to many Loggers");
    }

}
