package com.mino.mdiary.util;

import com.mino.mdiary.entity.vo.ListNode;
import com.mino.mdiary.exercise.java.algorithm.ListReversion;

import java.util.LinkedList;

public class ListNodeUtil {


    /**
     * 翻转列表(非递归)
     * @param linkedList   单链表
     * @param <T>  泛型
     * @return
     */
    public static <T> ListNode<T> reverseLinkedList(ListNode<T> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode<T> next = head.getNext();
        head.setNext(null);
        while (next != null) {
            ListNode<T> temp = next.getNext();
            next.setNext(head);
            head = next;
            next = temp;
        }
        return head;
    }

    /**
     * 翻转列表(递归)
     * @param head   链表头节点
     * @param <T>  泛型
     * @return
     */
    public static <T> ListNode<T> reverseLinkedList1(ListNode<T> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode<T> newHead = reverseLinkedList1(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }

    public static void main(String[] args) {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        System.out.println(node1);

        ListNode<Integer> newNode = reverseLinkedList(node1);

        System.out.println(newNode);

        newNode = reverseLinkedList1(newNode);

        System.out.println(newNode);
    }
}
