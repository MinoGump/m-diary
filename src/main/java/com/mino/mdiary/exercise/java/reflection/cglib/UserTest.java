package com.mino.mdiary.exercise.java.reflection.cglib;

public class UserTest {

    public static void main(String[] args) throws Throwable {
        User proxy = (User) new UserProxy(new User()).getInstance();
        proxy.print();
    }
}
