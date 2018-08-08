package com.mino.mdiary.exercise.java.dsaa.chapter3;

import com.mino.mdiary.exercise.java.struct.MyArrayDeque;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Iterator;

public class Test3_30 {

    class SelfAdjustingArrayList<T> extends MyArrayDeque<T> {

        public SelfAdjustingArrayList(int n) {
            super(n);
        }

        public SelfAdjustingArrayList() {
            super();
        }

        public SelfAdjustingArrayList(T... elements) {
            super(elements);
        }

        public boolean find(T ele) {
            int count = 0;
            Iterator<T> it = this.iterator();
            while (it.hasNext()) {
                T cur = it.next();
                if (cur.equals(ele)) {
                    it.remove();
                    count++;
                }
            }
            for (int i = 0; i < count; ++i) {
                addFirst(ele);
            }
            return count > 0;
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
        SelfAdjustingArrayList<Integer> selfAdjustingArrayList = new SelfAdjustingArrayList<>();
        selfAdjustingArrayList.addFirst(1);
        selfAdjustingArrayList.addFirst(2);
        selfAdjustingArrayList.addFirst(3);
        selfAdjustingArrayList.addFirst(4);
        selfAdjustingArrayList.addFirst(5);

        selfAdjustingArrayList.print(System.out);

        selfAdjustingArrayList.find(1);

        selfAdjustingArrayList.print(System.out);

        selfAdjustingArrayList.find(0);

        selfAdjustingArrayList.print(System.out);
    }
}
