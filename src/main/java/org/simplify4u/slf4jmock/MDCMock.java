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

import org.slf4j.spi.MDCAdapter;

import java.lang.reflect.Proxy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

/**
 * MDC Mock service.
 */
public class MDCMock {

    private static final MDCAdapter mdcAdapterProxy = initMockProxy();

    protected MDCMock() {
    }

    private static MDCAdapter initMockProxy() {
        return (MDCAdapter) Proxy.newProxyInstance(MDCMock.class.getClassLoader(),
                new Class<?>[]{MDCAdapter.class, ProxyMock.class},
                new MockInvocationHandler("MDC", () -> mock(MDCAdapter.class, withSettings().stubOnly()))
        );
    }

    /**
     * Mock for {@link MDCAdapter} .
     *
     * @return a mock for {@link MDCAdapter}
     */
    protected MDCAdapter getMock() {
        return mdcAdapterProxy;
    }

    /**
     * Set Mock for MDC at current thread.
     *
     * @param mock a Mock for use at current thread
     */
    public static void setMock(MDCAdapter mock) {
        ((ProxyMock) mdcAdapterProxy).setMock(mock);
    }

    /**
     * Clear Mock for MDC at current thread.
     */
    public static void clearMock() {
        ((ProxyMock) mdcAdapterProxy).clearMock();
    }
}
