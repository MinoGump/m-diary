package com.mino.mdiary.exercise.java.dsaa.chapter3;

import org.junit.Test;

public class Test3_24 {

    class DouStack<T> {
        private Object[] array;
        private int index1;
        private int index2;

        public DouStack(int length) {
            array = new Object[length];
            index1 = -1;
            index2 = length;
        }

        public void push(int stackNo, T value) throws Exception {
            if (stackNo != 1 && stackNo != 2) {
                throw new Exception("no this stack No.");
            }
            if (index1 + 1 == index2) {
                throw new Exception("Stack Overflow");
            }
            if (stackNo == 1) {
                array[++index1] = value;
            }
            if (stackNo == 2) {
                array[--index2] = value;
            }
        }

        public void pop(int stackNo) throws Exception {
            if (stackNo != 1 && stackNo != 2) {
                throw new Exception("no this stack No.");
            }
            if (index1 == -1 || index2 == array.length) {
                throw new Exception("no element to pop");
            }
            if (stackNo == 1) {
                --index1;
            }
            if (stackNo == 2) {
                ++index2;
            }
        }

        public void print() {
            StringBuilder sb1 = new StringBuilder();
            for (int i = 0; i <= index1; ++i) {
                sb1.append(array[i]).append(",");
            }
            System.out.println("当前栈1为(从栈底到栈顶)：[" + sb1.toString() + "]");
            StringBuilder sb2 = new StringBuilder();
            for (int i = array.length - 1; i >= index2; --i) {
                sb2.append(array[i]).append(",");
            }
            System.out.println("当前栈2为(从栈底到栈顶)：[" + sb2.toString() + "]");
        }
    }

    /**
     *  只用一个数组实现两个栈
     */
    @Test
    public void test() throws Exception {
        DouStack<Integer> douStack = new DouStack<>(5);
        douStack.push(1, 2);
        douStack.push(1, 5);
        douStack.push(1, 1);
        douStack.push(2, 3);
        douStack.print();
        douStack.pop(1);
        douStack.print();
    }
}
