package com.kaishengit.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SubjectMethodInterceptor implements MethodInterceptor{
//    CGlib与jdk自带的动态代理最大的区别是
//    jdk需要接口 而CGlib不需要接口 jdk的性能是CGlib的六倍
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<");
        return result;
    }
}
