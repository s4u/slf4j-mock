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

import org.simplify4u.slf4jmock.ProxyMock;
import org.slf4j.Logger;

/**
 * SLF4J Logger mock service.
 *
 * @deprecated please use {@link org.simplify4u.slf4jmock.LoggerMock}
 * <p>
 * This class will be removed.
 * <p>
 * More information how to use library in new way you can found at
 * https://www.simplify4u.org/slf4j-mock/
 */
@Deprecated
public class LoggerMock extends org.simplify4u.slf4jmock.LoggerMock {

    /**
     * Get mock for logger.
     *
     * @param klass Class for logger mock
     *
     * @return logger mock
     *
     * @deprecated please add to your test <p><code>@Mock Logger logger</code></p>
     */
    @Deprecated
    public static Logger getLoggerMock(Class<?> klass) {
        return getLoggerMock(klass.getName());
    }

    /**
     * Get mock for logger.
     *
     * @param name Class name for logger mock
     *
     * @return logger mock
     *
     * @deprecated please add to your test <p><code>@Mock Logger logger</code></p>
     */
    @Deprecated
    public static Logger getLoggerMock(String name) {
        return (Logger) getProxyByName(name).getMock();
    }

    /**
     * Clear invocation on all Logger mocks.
     *
     * @deprecated please add to your test <code>@Mock Logger logger</code>
     * <p>
     * This method will be removed
     */
    @Deprecated
    public static void clearInvocations() {
        getAllProxies().forEach(ProxyMock::clearMock);
    }
}
