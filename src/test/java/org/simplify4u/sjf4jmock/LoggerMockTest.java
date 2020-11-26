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


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
class LoggerMockTest {

    static class UnderTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(UnderTest.class);
    }


    @Mock
    Logger logger;

    @InjectMocks
    UnderTest sut;

    @Test
    void shouldReturnCorrectMockByClass() {
        assertThat(LoggerMock.getLoggerMock(UnderTest.class)).isSameAs(logger);
    }

    @Test
    void shouldReturnCorrectMockByName() {
        assertThat(LoggerMock.getLoggerMock(UnderTest.class.getName())).isSameAs(logger);
    }

    @Test
    void shouldClearMock() {
        LoggerMock.clearInvocations();

        assertThat(LoggerMock.getLoggerMock(UnderTest.class)).isNotSameAs(logger);
    }

}
