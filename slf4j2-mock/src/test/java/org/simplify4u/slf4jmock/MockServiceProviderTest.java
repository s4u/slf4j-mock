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

package org.simplify4u.slf4jmock;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MockServiceProviderTest {

    private MockServiceProvider serviceProvider;

    @BeforeEach
    void setUp() {
        serviceProvider = new MockServiceProvider();
    }

    @Test
    void getLoggerFactory() {
        assertNotNull(serviceProvider.getLoggerFactory());
    }

    @Test
    void getMarkerFactory() {
        assertNotNull(serviceProvider.getMarkerFactory());
    }

    @Test
    void getMDCAdapter() {
        assertNotNull(serviceProvider.getMDCAdapter());
    }

    @Test
    void initialize() {
        assertDoesNotThrow(() -> serviceProvider.initialize());
    }
}
