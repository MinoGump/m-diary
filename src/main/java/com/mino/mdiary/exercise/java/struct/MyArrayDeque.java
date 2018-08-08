package com.mino.mdiary.exercise.java.struct;

import java.util.*;
import java.util.function.Consumer;

public class MyArrayDeque<T> {

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private Object[] array;
    private int front;
    private int end;


    public MyArrayDeque() {
        this(16);
    }

    public MyArrayDeque(int n) {
        array = new Object[n];
        front = 0;
        end = 0;
    }

    public MyArrayDeque(T... elements) {
        array = new Object[elements.length];
        front = 0;
        end = 0;
    }

    public int size() {
        return sub(end, front, array.length);
    }

    private void extendArray(int needed) {
        final int oldCapacity = array.length;
        int newCapacity;
        // Double capacity if small; else grow by 50%
        int jump = (oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1);
        if (jump < needed || (newCapacity = (oldCapacity + jump)) - MAX_ARRAY_SIZE > 0) {
            // 内存对齐
            newCapacity = newCapacity(needed, jump);
        }
        final Object[] newArray = array = Arrays.copyOf(array, newCapacity);
    }

    private int newCapacity(int needed, int jump) {
        final int oldCapacity = array.length, minCapacity;
        if ((minCapacity = oldCapacity + needed) - MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0) {
                // overflow
                throw new IllegalStateException("Sorry, deque too big");
            }
            return Integer.MAX_VALUE;
        }
        if (needed > jump) return minCapacity;
        return (oldCapacity + jump - MAX_ARRAY_SIZE < 0) ? oldCapacity + jump : MAX_ARRAY_SIZE;
    }

    public void addFirst(T ele) {
        if (ele == null)
            throw new NullPointerException();
        final Object[] es = array;
        es[front = dec(front, es.length)] = ele;
        if (front == end) {
            extendArray(1);
        }
    }

    private boolean delete(int i) {
        final Object[] es = array;
        final int capacity = es.length;
        final int h, t;
        // number of elements before to-be-deleted elt
        final int head = sub(i, h = front, capacity);
        // number of elements after to-be-deleted elt
        final int back = sub(t = end, i, capacity) - 1;
        if (head < back) {
            // move front elements forwards
            if (h <= i) {
                System.arraycopy(es, h, es, h + 1, head);
            } else { // Wrap around
                System.arraycopy(es, 0, es, 1, i);
                es[0] = es[capacity - 1];
                System.arraycopy(es, h, es, h + 1, head - (i + 1));
            }
            es[h] = null;
            front = inc(h, capacity);
            return false;
        } else {
            // move back elements backwards
            end = dec(t, capacity);
            if (i <= end) {
                System.arraycopy(es, i + 1, es, i, back);
            } else { // Wrap around
                System.arraycopy(es, i + 1, es, i, capacity - (i + 1));
                es[capacity - 1] = es[0];
                System.arraycopy(es, 1, es, 0, t - 1);
            }
            es[end] = null;
            return true;
        }
    }

    protected static final int dec(int i, int modulus) {
        if (--i < 0) i = modulus - 1;
        return i;
    }

    protected static final int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }

    protected static final int sub(int i, int j, int modulus) {
        if ((i -= j) < 0) i += modulus;
        return i;
    }


    static final <E> E elementAt(Object[] es, int i) {
        return (E) es[i];
    }


    static final <E> E nonNullElementAt(Object[] es, int i) {
        @SuppressWarnings("unchecked") E e = (E) es[i];
        if (e == null)
            throw new ConcurrentModificationException();
        return e;
    }

    public Iterator<T> iterator() {
        return new MyArrayDeque.DeqIterator();
    }

    private class DeqIterator implements Iterator<T> {
        /** Index of element to be returned by subsequent call to next. */
        int cursor;

        /** Number of elements yet to be returned. */
        int remaining = size();

        /**
         * Index of element returned by most recent call to next.
         * Reset to -1 if element is deleted by a call to remove.
         */
        int lastRet = -1;

        DeqIterator() { cursor = front; }

        public final boolean hasNext() {
            return remaining > 0;
        }

        public T next() {
            if (remaining <= 0)
                throw new NoSuchElementException();
            final Object[] es = array;
            T e = nonNullElementAt(es, cursor);
            cursor = inc(lastRet = cursor, es.length);
            remaining--;
            return e;
        }

        void postDelete(boolean leftShifted) {
            if (leftShifted)
                cursor = dec(cursor, array.length);
        }

        public final void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            postDelete(delete(lastRet));
            lastRet = -1;
        }

        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            int r;
            if ((r = remaining) <= 0)
                return;
            remaining = 0;
            final Object[] es = array;
            if (es[cursor] == null || sub(end, cursor, es.length) != r)
                throw new ConcurrentModificationException();
            for (int i = cursor, _end = end, to = (i <= _end) ? _end : es.length;
                    ; i = 0, to = _end) {
                for (; i < to; i++)
                    action.accept(elementAt(es, i));
                if (to == _end) {
                    if (_end != end)
                        throw new ConcurrentModificationException();
                    lastRet = dec(_end, es.length);
                    break;
                }
            }
        }
    }

}
