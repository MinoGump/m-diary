package com.mino.mdiary.exercise.java.reflection.jdkproxy;

import java.util.LinkedList;

public class User implements IUser{

    public LinkedList<Integer> linkedList = new LinkedList<>();

    public User() {
        linkedList.add(1);
        linkedList.add(2);
    }

    @Override
    public int getInteger() {
        linkedList.removeFirst();
        return linkedList.peekFirst();
    }

    @Override
    public void print() {
        System.out.println("=== eating");
    }
}
