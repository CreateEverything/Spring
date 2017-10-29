package com.kaishengit.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectInvocationHandler implements InvocationHandler {
    private Object target;
//    在创建对象的时候传入目标对象作为参数
    public SubjectInvocationHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<");
        Object result = method.invoke(target,args);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
        return result;
    }
}
