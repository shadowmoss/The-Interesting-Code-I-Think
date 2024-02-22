package com.ltx.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TargetInterfaceDynamicProxy implements InvocationHandler {
    // 动态代理的调用处理器，也需要持有被代理类型的实例,
    // 这样在invoke方法中,调用方法时才能通过反射调用对应实例的具体方法。
    private Object proxied;
    public TargetInterfaceDynamicProxy(Object proxied){
        this.proxied = proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() + ", method: " + method + ", args: " +args);
        if(args != null){
            for(Object arg : args){
                System.out.println(" " + arg);
            }
        }
        return method.invoke(proxied,args);
    }
}
