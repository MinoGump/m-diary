package com.mino.mdiary.exercise.java.algorithm;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ListReversion {

    @Test
    public void test() {
        ListNode node = initNodeList();

        System.out.println(node);

        ListNode newNode = recursiveReverse(node);

        System.out.println(newNode);

        node = initNodeList();

        System.out.println(node);

        newNode = notRecursiveReverse(node);

        System.out.println(newNode);

    }

    private ListNode notRecursiveReverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode next = node.next;
        node.next = null;
        while (next != null) {
            ListNode temp = next.next;
            next.next = node;
            node = next;
            next = temp;
        }
        return node;
    }

    private ListNode initNodeList() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return node1;
    }

    private ListNode recursiveReverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode newNode = recursiveReverse(node.next);
        node.next.next = node;
        node.next = null;
        return newNode;
    }

    class ListNode {
        int val;
        ListNode next;


        ListNode(int value) {
            val = value;
        }

        @Override
        public String toString() {
            return "" + val + (next != null ? next.toString() : "");
        }
    }
}
