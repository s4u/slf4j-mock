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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import org.simplify4u.slf4jmock.internal.ProxyMock;

class MockInvocationHandler implements InvocationHandler, ProxyMock {

    private final String name;
    private final ThreadLocal<Object> mockThreadLocal;

    public MockInvocationHandler(String name, Supplier<?> defaultMock) {
        this.name = name;
        this.mockThreadLocal = ThreadLocal.withInitial(defaultMock);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getDeclaringClass() == ProxyMock.class) {
            return method.invoke(this, args);
        }

        return method.invoke(mockThreadLocal.get(), args);
    }

    @Override
    public void setMock(Object mock) {
        mockThreadLocal.set(mock);
    }

    @Override
    public Object getMock() {
        return mockThreadLocal.get();
    }

    @Override
    public void clearMock() {
        mockThreadLocal.remove();
    }

    @Override
    public String getMockName() {
        return name;
    }
}
