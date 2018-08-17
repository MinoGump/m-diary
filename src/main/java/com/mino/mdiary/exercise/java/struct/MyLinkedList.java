package com.mino.mdiary.exercise.java.struct;

import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyLinkedList<T> extends AbstractList<T> {

    private static class Node<E> {
        E item;
        MyLinkedList.Node<E> next;
        MyLinkedList.Node<E> prev;

        Node(MyLinkedList.Node<E> prev, E element, MyLinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    int size = 0;

    Node<T> first;

    Node<T> last;


    public MyLinkedList() {
    }

    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    private Node<T> node(int index) {
        //从前找还是从后找
        if (index < (size >> 1)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("invalid index");
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Node<T> f = first;
        while (f != null) {
            action.accept(f.item);
            f = f.next;
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    public MyLinkedList(T ... elements) {
        this();
        addAll(elements);
    }

    public void addFirst(T ele) {
        Node<T> f = first;
        Node<T> node = new Node<>(null, ele, first);
        first = node;
        if (f == null) {
            last = node;
        } else {
            f.prev = node;
        }
        size++;
        modCount++;
    }

    public void addLast(T ele) {
        Node<T> t = last;
        Node<T> node = new Node<>(last, ele, null);
        last = node;
        if (t == null) {
            first = node;
        } else {
            t.next = node;
        }
        size++;
        modCount++;
    }

    public T remove() {
        return removeFirst();
    }

    private T removeFirst() {
        final Node<T> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    private T unlinkFirst(Node<T> f) {
        T element = f.item;
        Node<T> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        modCount++;
        return element;
    }

    private T unlinkLast(Node<T> f) {
        T element = f.item;
        Node<T> prev = f.prev;
        f.item = null;
        f.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        modCount++;
        return element;
    }

    public T remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<T> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<T> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean removeAndPushFront(T ele) {
        Node<T> f = first;
        boolean flag = false;
        while (f != null) {
            if (ele.equals(f.item)) {
                Node<T> next = f.next;
                unlink(f);
                addFirst(ele);
                f = next;
                flag = true;
            } else {
                f = f.next;
            }
        }
        return flag;
    }

    private T unlink(Node<T> node) {
        T item = node.item;
        Node<T> prev = node.prev;
        Node<T> next = node.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.item = null;
        size--;
        modCount++;
        return item;
    }


    public void addAll(T ... elements) {
        for (T ele : elements) {
            addLast(ele);
        }
    }




}
