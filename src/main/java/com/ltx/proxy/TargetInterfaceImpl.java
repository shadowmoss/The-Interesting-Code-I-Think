package com.ltx.proxy;

/**
 * 实现了目标代理接口的类型。
 */
public class TargetInterfaceImpl implements TargetInterface{
    @Override
    public void doSomething() {
        System.out.println("impl doSomething");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
    }
}
