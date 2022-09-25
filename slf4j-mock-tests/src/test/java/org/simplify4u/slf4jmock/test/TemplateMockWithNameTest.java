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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

@ExtendWith(MockitoExtension.class)
class TemplateMockWithNameTest {

    @Mock(name = "org.simplify4u.slf4jmock.test.Example")
    Logger logger1;

    @Mock(name = "org.simplify4u.slf4jmock.test.Example2")
    Logger logger2;

    @Test
    void testNamedMock() {
        Example sut = new Example();

        sut.methodWithLogInfo("Info message");

        Mockito.verify(logger1).info("Info message");
        Mockito.verifyNoInteractions(logger2);
    }

}
