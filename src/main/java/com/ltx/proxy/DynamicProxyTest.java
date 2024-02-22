package com.ltx.proxy;

import com.ltx.proxy.dynamic.TargetInterfaceDynamicProxy;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    public static void consumer(TargetInterface target) {
        target.doSomething();
        target.somethingElse("dynamic proxy execute");
    }
    public static void main(String[] args) {
        TargetInterface targetInterface = new TargetInterfaceImpl();
        // 插入一个代理然后再次调用:
        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(TargetInterface.class.getClassLoader(),
                                                                        new Class[]{TargetInterface.class},
                                                                        new TargetInterfaceDynamicProxy(targetInterface));
        consumer(proxy);
    }
}
