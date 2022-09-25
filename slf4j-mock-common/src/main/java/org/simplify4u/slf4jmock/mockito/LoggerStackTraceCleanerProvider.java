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

package org.simplify4u.slf4jmock.mockito;

import org.mockito.exceptions.stacktrace.StackTraceCleaner;

import java.lang.reflect.Method;

/**
 * Cleanup stacktrace of not imported classes.
 */
public class LoggerStackTraceCleanerProvider implements org.mockito.plugins.StackTraceCleanerProvider {

    @Override
    public StackTraceCleaner getStackTraceCleaner(StackTraceCleaner defaultCleaner) {
        return candidate -> defaultCleaner.isIn(candidate)
                && !isJdkInternal(candidate)
                && !isSlf4jMock(candidate);
    }

    private static boolean isJdkInternal(StackTraceElement candidate) {
        return candidate.isNativeMethod()
                || candidate.getLineNumber() < 0
                || candidate.getClassName().startsWith("sun.")
                || candidate.getClassName().startsWith("jdk.internal.reflect")
                || candidate.getClassName().equals(Method.class.getName());
    }

    private static boolean isSlf4jMock(StackTraceElement candidate) {
        return "org.simplify4u.slf4jmock.MockInvocationHandler".equals(candidate.getClassName())
                || "org.slf4j.MDC".equals(candidate.getClassName());
    }
}
