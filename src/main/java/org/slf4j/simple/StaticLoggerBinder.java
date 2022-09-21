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
package org.slf4j.simple;

import org.simplify4u.slf4jmock.LoggerMock;

/**
 * Implementation of Logger Binder.
 */
public class StaticLoggerBinder extends LoggerMock {

    private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

    @SuppressWarnings("unused")
    public static final String REQUESTED_API_VERSION = "1.7";

    public static StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }
}
