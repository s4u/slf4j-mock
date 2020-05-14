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

package org.simplify4u.sl4jmock.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example {

    private static final Logger LOGGER = LoggerFactory.getLogger(Example.class);

    public void methodWithLogDebug(String format, String args) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(format, args);
        }
    }

    public void methodWithLogInfo(String message) {
        LOGGER.info(message);
    }

    public void methodWithLogWarn(String message) {
        LOGGER.warn(message);
        LOGGER.warn(message);
    }

    public void logError100() {
        for (int i = 0; i < 10; i++) {
            LOGGER.error("error: {}", i);
        }
    }
}
