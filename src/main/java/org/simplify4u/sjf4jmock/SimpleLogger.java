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

import org.slf4j.impl.AbstractSimpleLoggerDelegate;

/**
 * Access to SLF4J SimpleLogger by own implementation.
 */
class SimpleLogger extends AbstractSimpleLoggerDelegate {

    private static final long serialVersionUID = -1050011592280122282L;

    SimpleLogger(String name) {
        super(name);
    }
}
