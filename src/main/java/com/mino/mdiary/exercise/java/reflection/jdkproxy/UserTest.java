package com.mino.mdiary.exercise.java.reflection.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class UserTest {

    public static void main(String[] args) {
        IUser user = new User();
        InvocationHandler handler =  new UserProxy(user);

        IUser userProxy = (IUser) Proxy.newProxyInstance(handler.getClass().getClassLoader(), user
                .getClass().getInterfaces(), handler);

        userProxy.print();
    }
}
