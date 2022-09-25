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

import org.simplify4u.slf4jmock.MDCMock;
import org.slf4j.spi.MDCAdapter;

/**
 * Implementation for MDC Binder
 */
public class StaticMDCBinder extends MDCMock {

    static final StaticMDCBinder SINGLETON = new StaticMDCBinder();

    /**
     * Return the singleton of this class.
     *
     * @return the StaticMDCBinder singleton
     */
    public static StaticMDCBinder getSingleton() {
        return SINGLETON;
    }

    public MDCAdapter getMDCA() {
        return getMock();
    }
}
