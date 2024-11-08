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

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TemplateSpyLoggerTest {

    @Spy
    private Logger logger;

    @InjectMocks
    private Example sut;

    @Test
    void testSpy() {

        sut.methodWithLogInfo("Info message - SpyLoggerTest");

        Mockito.verify(logger).info("Info message - SpyLoggerTest");
        Mockito.verifyNoMoreInteractions(logger);

        assertThat(new File("target/slf4j-simpleLogger.log"))
                .content().contains("Info message - SpyLoggerTest");
    }

}
