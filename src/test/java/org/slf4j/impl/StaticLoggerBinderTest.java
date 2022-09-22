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

package org.slf4j.impl;

import org.junit.jupiter.api.Test;
import org.simplify4u.slf4jmock.LoggerMock;
import org.slf4j.ILoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

class StaticLoggerBinderTest {

    @Test
    void staticLoggerBinderReturnCorrectFactory() {
        assertThat(StaticLoggerBinder.getSingleton().getLoggerFactory())
                .isInstanceOf(ILoggerFactory.class);
    }

    @Test
    void staticLoggerBinderReturnCorrectFactoryClassStr() {
       assertThat(StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr())
                .startsWith(LoggerMock.class.getName());
    }

}
