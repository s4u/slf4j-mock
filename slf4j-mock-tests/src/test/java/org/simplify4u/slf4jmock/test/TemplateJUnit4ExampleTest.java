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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class TemplateJUnit4ExampleTest {

    private static final String INFO_TEST_MESSAGE = "info log test message from JUnit4";

    @Mock
    Logger logger;

    @InjectMocks
    Example sut;

    @Test
    public void logInfoShouldBeLogged() {

        // when
        sut.methodWithLogInfo(INFO_TEST_MESSAGE);

        // then
        Mockito.verify(logger).info(INFO_TEST_MESSAGE);
        Mockito.verifyNoMoreInteractions(logger);
    }
}
