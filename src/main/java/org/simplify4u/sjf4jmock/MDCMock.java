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

import org.mockito.Mockito;
import org.slf4j.spi.MDCAdapter;

/**
 * MDC Mock service.
 * <p>
 * We have only one instance created by static context in {@link org.slf4j.MDC}, so we must call {@link
 * #clearInvocations()} or {@link #reset()} before/after each test.
 */
public final class MDCMock {

    private static final MDCAdapter mdcAdapter = Mockito.mock(MDCAdapter.class);

    private MDCMock() {
    }

    /**
     * Mock for {@link MDCAdapter} .
     *
     * @return a mock for {@link MDCAdapter}
     */
    public static MDCAdapter getMock() {
        return mdcAdapter;
    }

    /**
     * Clear invocation on {@link MDCAdapter}
     */
    public static void clearInvocations() {
        Mockito.clearInvocations(mdcAdapter);
    }

    /**
     * Reset Mock for {@link MDCAdapter}
     */
    public static void reset() {
        Mockito.reset(mdcAdapter);
    }
}
