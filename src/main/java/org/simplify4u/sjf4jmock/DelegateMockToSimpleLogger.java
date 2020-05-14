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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

import org.slf4j.Logger;
import org.slf4j.Marker;

class DelegateMockToSimpleLogger {

    private final SimpleLogger delegate;

    public DelegateMockToSimpleLogger(String name) {
        this.delegate = new SimpleLogger(name);
    }

    @SuppressWarnings("java:S2629")
    // java:S2629 - "Preconditions" and logging arguments should not require evaluation
    public void delegate(Logger mock) {

        delegateAnswer(mock).isTraceEnabled();
        delegateAnswer(mock).isTraceEnabled(any(Marker.class));
        delegateAnswer(mock).trace(anyString());
        delegateAnswer(mock).trace(anyString(), any(Object.class));
        delegateAnswer(mock).trace(anyString(), any(Object.class), any(Object.class));
        delegateAnswer(mock).trace(anyString(), any(Object[].class));
        delegateAnswer(mock).trace(anyString(), any(Throwable.class));
        delegateAnswer(mock).trace(any(Marker.class), anyString());
        delegateAnswer(mock).trace(any(Marker.class), anyString(), any(Object.class));
        delegateAnswer(mock).trace(any(Marker.class), anyString(), any(Object.class), any(Object.class));
        delegateAnswer(mock).trace(any(Marker.class), anyString(), any(Object[].class));
        delegateAnswer(mock).trace(any(Marker.class), anyString(), any(Throwable.class));

        delegateAnswer(mock).isDebugEnabled();
        delegateAnswer(mock).isDebugEnabled(any(Marker.class));
        delegateAnswer(mock).debug(anyString());
        delegateAnswer(mock).debug(anyString(), any(Object.class));
        delegateAnswer(mock).debug(anyString(), any(Object.class), any(Object.class));
        delegateAnswer(mock).debug(anyString(), any(Object[].class));
        delegateAnswer(mock).debug(anyString(), any(Throwable.class));
        delegateAnswer(mock).debug(any(Marker.class), anyString());
        delegateAnswer(mock).debug(any(Marker.class), anyString(), any(Object.class));
        delegateAnswer(mock).debug(any(Marker.class), anyString(), any(Object.class), any(Object.class));
        delegateAnswer(mock).debug(any(Marker.class), anyString(), any(Object[].class));
        delegateAnswer(mock).debug(any(Marker.class), anyString(), any(Throwable.class));

        delegateAnswer(mock).isInfoEnabled();
        delegateAnswer(mock).isInfoEnabled(any(Marker.class));
        delegateAnswer(mock).info(anyString());
        delegateAnswer(mock).info(anyString(), any(Object.class));
        delegateAnswer(mock).info(anyString(), any(Object.class), any(Object.class));
        delegateAnswer(mock).info(anyString(), any(Object[].class));
        delegateAnswer(mock).info(anyString(), any(Throwable.class));
        delegateAnswer(mock).info(any(Marker.class), anyString());
        delegateAnswer(mock).info(any(Marker.class), anyString(), any(Object.class));
        delegateAnswer(mock).info(any(Marker.class), anyString(), any(Object.class), any(Object.class));
        delegateAnswer(mock).info(any(Marker.class), anyString(), any(Object[].class));
        delegateAnswer(mock).info(any(Marker.class), anyString(), any(Throwable.class));

        delegateAnswer(mock).isWarnEnabled();
        delegateAnswer(mock).isWarnEnabled(any(Marker.class));
        delegateAnswer(mock).warn(anyString());
        delegateAnswer(mock).warn(anyString(), any(Object.class));
        delegateAnswer(mock).warn(anyString(), any(Object.class), any(Object.class));
        delegateAnswer(mock).warn(anyString(), any(Object[].class));
        delegateAnswer(mock).warn(anyString(), any(Throwable.class));
        delegateAnswer(mock).warn(any(Marker.class), anyString());
        delegateAnswer(mock).warn(any(Marker.class), anyString(), any(Object.class));
        delegateAnswer(mock).warn(any(Marker.class), anyString(), any(Object.class), any(Object.class));
        delegateAnswer(mock).warn(any(Marker.class), anyString(), any(Object[].class));
        delegateAnswer(mock).warn(any(Marker.class), anyString(), any(Throwable.class));

        delegateAnswer(mock).isErrorEnabled();
        delegateAnswer(mock).isErrorEnabled(any(Marker.class));
        delegateAnswer(mock).error(anyString());
        delegateAnswer(mock).error(anyString(), any(Object.class));
        delegateAnswer(mock).error(anyString(), any(Object.class), any(Object.class));
        delegateAnswer(mock).error(anyString(), any(Object[].class));
        delegateAnswer(mock).error(anyString(), any(Throwable.class));
        delegateAnswer(mock).error(any(Marker.class), anyString());
        delegateAnswer(mock).error(any(Marker.class), anyString(), any(Object.class));
        delegateAnswer(mock).error(any(Marker.class), anyString(), any(Object.class), any(Object.class));
        delegateAnswer(mock).error(any(Marker.class), anyString(), any(Object[].class));
        delegateAnswer(mock).error(any(Marker.class), anyString(), any(Throwable.class));
    }

    private Logger delegateAnswer(Logger mock) {
        return doAnswer(i -> i.getMethod().invoke(delegate, i.getArguments())).when(mock);
    }
}
