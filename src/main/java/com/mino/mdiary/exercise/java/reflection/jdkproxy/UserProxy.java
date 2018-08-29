package com.mino.mdiary.exercise.java.reflection.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserProxy implements InvocationHandler {

    private IUser target;

    public UserProxy(IUser target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=== before eating");

        if (method.getName().equals("print")) {
            int result = target.getInteger();
            System.out.println(result);
        }

        System.out.println("=== after eating");


        return null;
    }
}
