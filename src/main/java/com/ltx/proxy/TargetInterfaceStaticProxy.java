package com.ltx.proxy;

/**
 * 具体的静态代理类型
 * 也就是类成员包含一个对应的目标接口类型的实例，并且静态代理类也实现目标接口，这样去实现静态代理。
 */
public class TargetInterfaceStaticProxy implements TargetInterface {
    private TargetInterface targetInterface;
    public TargetInterfaceStaticProxy(TargetInterface targetInterface){
        this.targetInterface = targetInterface;
    }
    @Override
    public void doSomething() {
        System.out.println("pre proxy doSomething");
        targetInterface.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("pre proxy somethingElse");
        targetInterface.somethingElse(arg);
    }
}
