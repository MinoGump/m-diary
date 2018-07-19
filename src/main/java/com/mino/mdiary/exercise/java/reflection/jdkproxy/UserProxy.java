package com.mino.mdiary.exercise.java.reflection.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserProxy implements InvocationHandler {

    private Object target;

    public UserProxy(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=== before eating");

        Object result = method.invoke(target, args);

        System.out.println("=== after eating");

        return result;
    }
}
