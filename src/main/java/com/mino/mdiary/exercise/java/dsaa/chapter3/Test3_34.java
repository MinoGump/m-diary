package com.mino.mdiary.exercise.java.dsaa.chapter3;

import com.mino.mdiary.exercise.java.struct.MyNode;
import org.junit.Test;

import java.util.HashSet;

public class Test3_34 {

    /**
     * 保存查询法
     * @param myNode
     * @param <T>
     * @return
     */
    public <T> boolean isLoop(MyNode<T> myNode) {
        HashSet<T> traveledSet = new HashSet<>();
        MyNode<T> head = myNode;
        while (head != null) {
            if (traveledSet.contains(head.getItem())) {
                return true;
            }
            traveledSet.add(head.getItem());
            head = head.getNext();
        }
        return false;
    }

    /**
     * 快慢指针法
     * @param myNode
     * @param <T>
     * @return
     */
    public <T> boolean isLoop1(MyNode<T> myNode) {
        MyNode<T> fast = myNode.getNext();
        MyNode<T> slow = myNode;
        while (fast != null) {
            if (fast.getItem().equals(slow.getItem())) {
                return true;
            }
            fast = fast.getNext();
            if (fast != null) {
                fast = fast.getNext();
                slow = slow.getNext();
            } else {
                break;
            }
        }
        return false;
    }

    @Test
    public void test() {
        MyNode<Integer> node5 = new MyNode<>(5, null);
        MyNode<Integer> node4 = new MyNode<>(4, node5);
        MyNode<Integer> node3 = new MyNode<>(3, node4);
        MyNode<Integer> node2 = new MyNode<>(2, node3);
        MyNode<Integer> node1 = new MyNode<>(1, node2);
        MyNode<Integer> noLoop = new MyNode<>(0, node1);



        System.out.println(isLoop(noLoop));
        System.out.println(isLoop1(noLoop));

        MyNode<Integer> loop1 = new MyNode<>(0, node1);
        node5.setNext(loop1);

        System.out.println(isLoop(loop1));
        System.out.println(isLoop1(loop1));

        MyNode<Integer> loop2 = new MyNode<>(0, node1);
        node5.setNext(node3);

        System.out.println(isLoop(loop2));
        System.out.println(isLoop1(loop2));
    }
}
