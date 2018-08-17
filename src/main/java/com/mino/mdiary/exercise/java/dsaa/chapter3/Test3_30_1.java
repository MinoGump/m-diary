package com.mino.mdiary.exercise.java.dsaa.chapter3;

import com.mino.mdiary.exercise.java.struct.MyLinkedList;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Iterator;

public class Test3_30_1 {

    class SelfAdjustingLinkedList<T> extends MyLinkedList<T> {


        public SelfAdjustingLinkedList() {
            super();
        }

        public SelfAdjustingLinkedList(T ... element) {
            super(element);
        }

        public boolean find(T ele) {
            int count = 0;
            return removeAndPushFront(ele);
        }

        public void print(PrintStream printStream) {
            StringBuilder sb = new StringBuilder();
            Iterator<T> it = this.iterator();
            while (it.hasNext()) {
                T entry = it.next();
                sb.append(entry.toString()).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            printStream.println(sb.toString());
        }
    }

    /**
     * 自调整表，所有的插入都在前端进行，自调整表添加一个find操作，当一个元素被find访问时，它就被移动到表到前端而不改变其他项的相对顺序
     *
     * */
    @Test
    public void test() {
        SelfAdjustingLinkedList<Integer> selfAdjustingLinkedList = new SelfAdjustingLinkedList<>();
        selfAdjustingLinkedList.addLast(1);
        selfAdjustingLinkedList.addLast(2);
        selfAdjustingLinkedList.addLast(3);
        selfAdjustingLinkedList.addLast(4);
        selfAdjustingLinkedList.addLast(5);

        selfAdjustingLinkedList.print(System.out);

        selfAdjustingLinkedList.find(6);
        selfAdjustingLinkedList.find(4);
        selfAdjustingLinkedList.find(2);
        selfAdjustingLinkedList.find(3);
        selfAdjustingLinkedList.find(6);

        selfAdjustingLinkedList.print(System.out);
    }
}
