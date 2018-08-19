package com.mino.mdiary.exercise.java.struct;

import java.util.LinkedList;
import java.util.Queue;

public class AvlTree<T extends Comparable<? super T>> {

    AvlNode<T> root;

    public AvlTree() {
    }

    private static class AvlNode<T> {
        T element;
        int height;
        AvlNode<T> left;
        AvlNode<T> right;

        AvlNode(T element) {
            this(element, null, null);
        }

        AvlNode(T element, AvlNode<T> left, AvlNode<T> right) {
            this.element = element;
            this.height = 0;
            this.left = left;
            this.right = right;
        }
    }

    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        AvlNode<T> minNode = findMin(root);
        return minNode != null ? minNode.element : null;
    }

    public T findMax() {
        AvlNode<T> maxNode = findMax(root);
        return maxNode != null ? maxNode.element : null;
    }

    public void insert(T x) {
        this.root = insert(x, root);
    }

    public void remove(T x) {
        this.root = remove(x, root);
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("empty binary search tree");
        }
        Queue<AvlNode<T>> queue = new LinkedList<>();
        Queue<String> stringQueue = new LinkedList<>();
        queue.add(root);
        printTree(stringQueue, queue);
    }

    /**
     * 先序遍历
     */
    private void printTree(Queue<String> stringQueue, Queue<AvlNode<T>> queue) {
        if (queue.size() == 0) return;
        Queue<AvlNode<T>> nextLevel = new LinkedList<>();
        AvlNode<T> t = queue.poll();
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

    private boolean contains(T x, AvlNode<T> t) {
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


    private AvlNode<T> findMin(AvlNode<T> t) {
        if (t == null) return null;
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    private AvlNode<T> findMax(AvlNode<T> t) {
        if (t == null) return null;
        while (t.right != null) {
            t = t.right;
        }
        return t;
    }

    private AvlNode<T> insert (T x, AvlNode<T> t) {
        if (t == null) return new AvlNode<>(x, null, null);
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }
        return balance(t);
    }

    private static final int ALLOWED_IMBALANCE = 1;
    private AvlNode<T> balance(AvlNode<T> t) {
        if (t == null) return null;
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            if (height(t.left) >= height(t.right)) {
                t = rotateWithLeftChild(t);
            } else {
                t = doubleWithLeftChild(t);
            }
        } else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
            if (height(t.right) >= height(t.left)) {
                t = rotateWithRightChild(t);
            } else {
                t = doubleWithRightChild(t);
            }
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> t) {
        AvlNode<T> leftNode = t.left;
        if (leftNode == null) return t;
        t.left = leftNode.right;
        leftNode.right = t;
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        leftNode.height = Math.max(height(leftNode.left), t.height) + 1;
        return leftNode;
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> t) {
        AvlNode<T> rightNode = t.right;
        if (rightNode == null) return t;
        t.right = rightNode.left;
        rightNode.left = t;
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        rightNode.height = Math.max(height(rightNode.right), t.height) + 1;
        return rightNode;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> t) {
        t.left = rotateWithRightChild(t.left);
        return rotateWithLeftChild(t);
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> t) {
        t.right = rotateWithLeftChild(t.right);
        return rotateWithRightChild(t);
    }

    public AvlNode<T> remove(T x, AvlNode<T> root) {
        if (root == null) return null;
        int compareResult = x.compareTo(root.element);

        if (compareResult < 0) {
            root.left = remove(x, root.left);
        } else if (compareResult > 0) {
            root.right = remove(x, root.right);
        } else if (root.left != null && root.right != null) {
            root.right = removeMin(root.right);
            root.element = min;
        } else {
            root = root.left != null ? root.left : root.right;
        }
        return balance(root);
    }

    private T min;

    private AvlNode<T> removeMin(AvlNode<T> root) {
        if (root == null) return null;
        if (root.left == null) {
            min = root.element;
            return root.right;
        }
        root.left = removeMin(root.left);
        return root;
    }
}
