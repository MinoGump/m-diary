package com.mino.mdiary.exercise.java.struct;

public class MyNode<T> {

    private T item;
    private MyNode<T> next;

    public MyNode(T item, MyNode<T> next) {
        this.item = item;
        this.next = next;
    }

    public MyNode() {
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public MyNode<T> getNext() {
        return next;
    }

    public void setNext(MyNode<T> next) {
        this.next = next;
    }
}
