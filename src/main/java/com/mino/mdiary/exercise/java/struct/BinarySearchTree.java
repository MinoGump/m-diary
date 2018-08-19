package com.mino.mdiary.exercise.java.struct;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    BinaryNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void makeEmpty() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        BinaryNode<T> minNode = findMin(root);
        return minNode != null ? minNode.element : null;
    }

    public T findMax() {
        BinaryNode<T> maxNode = findMax(root);
        return maxNode != null ? maxNode.element : null;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("empty binary search tree");
        }
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        Queue<String> stringQueue = new LinkedList<>();
        queue.add(root);
        printTree(stringQueue, queue);
    }

    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) return false;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) return null;
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t == null) return null;
        while (t.right != null) {
            t = t.right;
        }
        return t;
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);     //leaf node
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            // do nothing
        }
        return t;
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) return t;

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.right = removeMin(t.right);
            t.element = min;
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private T min;

    private BinaryNode<T> removeMin(BinaryNode<T> root) {
        if (root == null) return null;
        if (root.left == null) {
            min = root.element;
            return root.right;
        }
        root.left = removeMin(root.left);
        return root;
    }

    /**
     * 先序遍历
     */
    private void printTree(Queue<String> stringQueue, Queue<BinaryNode<T>> queue) {
        if (queue.size() == 0) return;
        Queue<BinaryNode<T>> nextLevel = new LinkedList<>();
        BinaryNode<T> t = queue.poll();
        if (stringQueue != null) {
            int size = stringQueue.size();
            for (int i = 0; i < size; ++i) {
                System.out.printf(stringQueue.poll());
            }
            System.out.println();
        }
        while (t != null) {
            System.out.printf(String.valueOf(t.element) + " ");
            if (t.left != null) {
                stringQueue.add("| ");
                nextLevel.add(t.left);
            }
            if (t.right != null) {
                stringQueue.add("\\ ");
                nextLevel.add(t.right);
            }
            t = queue.poll();
        }
        System.out.println();
        printTree(stringQueue, nextLevel);
    }


    private class BinaryNode<T> {

        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        public BinaryNode(T element) {
            this(element, null, null);
        }

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }
}