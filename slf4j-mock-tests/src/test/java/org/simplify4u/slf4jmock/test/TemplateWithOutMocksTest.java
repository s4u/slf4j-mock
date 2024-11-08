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
import org.slf4j.MDC;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class TemplateWithOutMocksTest {

    private Example sut = new Example();

    @Test
    void loggerTest() {
        assertThatCode(() -> sut.methodWithLogInfo("Some message - WithOutMocksTest"))
                .doesNotThrowAnyException();

        assertThat(new File("target/slf4j-simpleLogger.log"))
                .content().contains("Some message - WithOutMocksTest");
    }

    @Test
    void mdcTest() {
        assertThat(MDC.get("key")).isNull();
    }
}
