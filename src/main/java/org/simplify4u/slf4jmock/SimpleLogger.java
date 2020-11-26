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

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.impl.SimpleLoggerDelegate;

/**
 * Access to SLF4J SimpleLogger by own implementation.
 */
public class SimpleLogger implements Logger {

    @SuppressWarnings("java:S1312") // Loggers should be "private static final" and should share a naming convention
    private final Logger delegateLogger;

    /**
     * Create SimpleLogger instance with name
     *
     * @param name a name of Logger
     */
    public SimpleLogger(String name) {
        delegateLogger = new SimpleLoggerDelegate(name);
    }

    /**
     * Create SimpleLogger instance with name of class
     *
     * @param klass a class for Logger
     */
    public SimpleLogger(Class<?> klass) {
        this(klass.getName());
    }

    @Override
    public String getName() {
        return delegateLogger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return delegateLogger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        delegateLogger.trace(msg);
    }

    @Override
    public void trace(String format, Object arg) {
        delegateLogger.trace(format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        delegateLogger.trace(format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        delegateLogger.trace(format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        delegateLogger.trace(msg, t);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return delegateLogger.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String msg) {
        delegateLogger.trace(marker, msg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        delegateLogger.trace(marker, format, arg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        delegateLogger.trace(marker, format, arg1, arg2);
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        delegateLogger.trace(marker, format, argArray);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        delegateLogger.trace(marker, msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return delegateLogger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        delegateLogger.debug(msg);
    }

    @Override
    public void debug(String format, Object arg) {
        delegateLogger.debug(format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        delegateLogger.debug(format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        delegateLogger.debug(format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        delegateLogger.debug(msg, t);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return delegateLogger.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String msg) {
        delegateLogger.debug(marker, msg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        delegateLogger.debug(marker, format, arg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        delegateLogger.debug(marker, format, arg1, arg2);
    }

    @Override
    public void debug(Marker marker, String format, Object... argArray) {
        delegateLogger.debug(marker, format, argArray);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        delegateLogger.debug(marker, msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return delegateLogger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        delegateLogger.info(msg);
    }

    @Override
    public void info(String format, Object arg) {
        delegateLogger.info(format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        delegateLogger.info(format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        delegateLogger.info(format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        delegateLogger.info(msg, t);
    }


    @Override
    public boolean isInfoEnabled(Marker marker) {
        return delegateLogger.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String msg) {
        delegateLogger.info(marker, msg);
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        delegateLogger.info(marker, format, arg);
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        delegateLogger.info(marker, format, arg1, arg2);
    }

    @Override
    public void info(Marker marker, String format, Object... argArray) {
        delegateLogger.info(marker, format, argArray);
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        delegateLogger.info(marker, msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return delegateLogger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        delegateLogger.warn(msg);
    }

    @Override
    public void warn(String format, Object arg) {
        delegateLogger.warn(format, arg);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        delegateLogger.warn(format, arg1, arg2);
    }

    @Override
    public void warn(String format, Object... arguments) {
        delegateLogger.warn(format, arguments);
    }

    @Override
    public void warn(String msg, Throwable t) {
        delegateLogger.warn(msg, t);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return delegateLogger.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String msg) {
        delegateLogger.warn(marker, msg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        delegateLogger.warn(marker, format, arg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        delegateLogger.warn(marker, format, arg1, arg2);
    }

    @Override
    public void warn(Marker marker, String format, Object... argArray) {
        delegateLogger.warn(marker, format, argArray);
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        delegateLogger.warn(marker, msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return delegateLogger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        delegateLogger.error(msg);
    }

    @Override
    public void error(String format, Object arg) {
        delegateLogger.error(format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        delegateLogger.error(format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        delegateLogger.error(format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        delegateLogger.error(msg, t);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return delegateLogger.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String msg) {
        delegateLogger.error(marker, msg);
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        delegateLogger.error(marker, format, arg);
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        delegateLogger.error(marker, format, arg1, arg2);
    }

    @Override
    public void error(Marker marker, String format, Object... argArray) {
        delegateLogger.error(marker, format, argArray);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        delegateLogger.error(marker, msg, t);
    }
}
