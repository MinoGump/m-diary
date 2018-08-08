package com.mino.mdiary.exercise.java.dsaa.chapter3;

import org.junit.Test;

public class Test3_20 {

    class ElementNode<T> {
        public T ele;
        public boolean isDeleted;
        public ElementNode<T> next;

        public ElementNode(T ele) {
            this.ele = ele;
            this.isDeleted = false;
        }
    }

    public <T> void lazyDelete(ElementNode<T> head, int n) {
        if (head == null) {
            return;
        }
        int deletedCount = 0;
        int unDeletedCount = 0;
        int i = 0;
        ElementNode<T> node = head;
        ElementNode<T> tempNode = head;
        while(node != null && i++ <= n) {
            if (node.isDeleted) deletedCount++;
            else unDeletedCount++;
            tempNode = node;
            node = node.next;
        }
        tempNode.isDeleted = true;
        if (deletedCount + 2 <= unDeletedCount || i <= n) {
            return;
        }
        i = 0;
        node = head;
        while (i++ <= n && node != null) {
            if (node.isDeleted) {
                tempNode.next = node.next;
                node = node.next;
                i--;
            } else {
                tempNode = node;
                node = node.next;
            }
        }

    }

    /***
     * lazy deletion 懒惰删除  每个元素附加一个bit域作为isDeleted标记，删除时软删，当已删除的元素过半时，则遍历整个表删除所有被标记的元素
     */
    @Test
    public void test() {
        ElementNode<Integer> head = new ElementNode<>(1);
        ElementNode<Integer> n1 = new ElementNode<>(2);
        ElementNode<Integer> n2 = new ElementNode<>(3);
        ElementNode<Integer> n3 = new ElementNode<>(4);
        ElementNode<Integer> n4 = new ElementNode<>(5);
        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        lazyDelete(head, 3);
        lazyDelete(head, 4);
        lazyDelete(head, 5);
        while (head != null) {
            System.out.println(head.ele);
            head = head.next;
        }
    }

}
