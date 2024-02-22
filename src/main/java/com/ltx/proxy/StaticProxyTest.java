package com.ltx.proxy;

/**
 * 静态代理类型测试
 */
public class StaticProxyTest {
    public static void consumer(TargetInterface targetInterface){
        targetInterface.doSomething();
        targetInterface.somethingElse("execute proxy");
    }
    public static void main(String[] args) {
        TargetInterfaceImpl targetInterface = new TargetInterfaceImpl();
        TargetInterfaceStaticProxy targetInterfaceStaticProxy = new TargetInterfaceStaticProxy(targetInterface);
        consumer(targetInterfaceStaticProxy);
    }
}
