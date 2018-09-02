package com.mino.mdiary.exercise.java.struct;

import java.util.*;
import java.util.function.Consumer;

/**
 * 实现一颗TreeSet，它的迭代器是一个二叉查找树，且每个树节点都存储它的父节点
 */
public class MyTreeSet<AnyType extends Comparable<? super AnyType>> {

    private BNode<AnyType> root;
    private int modCount = 0;
    private AnyType min;        // 删除当前node下的最小值存储用

    private static class BNode<AnyType> {
        BNode<AnyType> parent;
        BNode<AnyType> left;
        BNode<AnyType> right;
        AnyType ele;

        public BNode() {
        }

        public BNode(AnyType element) {
            this(null, null, null, element);
        }

        public BNode(BNode<AnyType> parent, BNode<AnyType> left, BNode<AnyType> right, AnyType ele) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.ele = ele;
        }
    }

    public void makeEmpty() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public void insert(AnyType x) {
        root = insert(x, root, null);
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("empty binary search tree");
        }
        Queue<BNode<AnyType>> queue = new LinkedList<>();
        Queue<String> stringQueue = new LinkedList<>();
        queue.add(root);
        printTree(stringQueue, queue);
    }

    public Iterator<AnyType> iterator() {
        return new MyTreeSetIterator();
    }

    private class MyTreeSetIterator implements Iterator<AnyType> {
        private BNode<AnyType> current = findMin(root);
        private BNode<AnyType> previous;
        private int mCount = modCount;
        private boolean atEnd = false;
        private boolean readyToRemove = false;

        @Override
        public void remove() {
            if (modCount != mCount) {
                throw new ConcurrentModificationException();
            }
            if (!readyToRemove) {
                throw new IllegalStateException();
            }
            MyTreeSet.this.remove(previous.ele, root);
            readyToRemove = false;
        }

        @Override
        public void forEachRemaining(Consumer<? super AnyType> action) {
            while (hasNext()) {
                AnyType ele = next();
                action.accept(ele);
            }
        }

        @Override
        public boolean hasNext() {
            return !atEnd;
        }

        @Override
        public AnyType next() {
            if (modCount != mCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AnyType ele = current.ele;
            previous = current;
            if (current.right != null) {
                current = findMin(current.right);
            } else {
                BNode<AnyType> child = current;
                current = current.parent;
                //可以找到一个祖先，且最初的current节点在这个祖先的左子树
                while (current != null && current.left != child) {
                    child = current;
                    current = current.parent;
                }
                if (current == null)//这里很有可能是，在到了最右点，一直执行上面循环到了根节点，然后根节点没有父节点，设置为true
                    atEnd = true;
            }
            readyToRemove = true;
            return ele;
        }
    }

    private BNode<AnyType> findMin(BNode<AnyType> node) {
        if (node != null) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        return node;
    }

    private BNode<AnyType> remove(AnyType element, BNode<AnyType> node) {
        if (node == null) return null;
        int compareResult = element.compareTo(node.ele);
        if (compareResult < 0) {
            node.left = remove(element, node.left);
        } else if (compareResult > 0) {
            node.right = remove(element, node.right);
        } else if (node.left != null && node.right != null) {
            node.right = removeMin(node);
            node.ele = min;
        } else {
            node = node.left != null ? node.left : node.right;
        }
        return node;
    }

    private BNode<AnyType> removeMin(BNode<AnyType> root) {
        if (root == null) return null;
        if (root.left == null) {
            min = root.ele;
            return root.right;
        }
        root.left = removeMin(root.left);
        return root;
    }

    private BNode<AnyType> insert(AnyType x, BNode<AnyType> root, BNode<AnyType> parent) {
        if (root == null) {
            return new BNode<>(parent, null, null, x);
        }
        int compareResult = x.compareTo(root.ele);
        if (compareResult < 0) {
            root.left = insert(x, root.left, root);
        } else if (compareResult > 0) {
            root.right = insert(x, root.right, root);
        } else {
            // do nothing
        }
        return root;
    }

    private boolean contains(AnyType x, BNode<AnyType> root) {
        if (root == null) return false;
        int compareResult = x.compareTo(root.ele);
        if (compareResult < 0) {
            return contains(x, root.left);
        } else if (compareResult > 0) {
            return contains(x, root.right);
        } else {
            return true;
        }
    }

    /**
     * 先序遍历
     */
    private void printTree(Queue<String> stringQueue, Queue<BNode<AnyType>> queue) {
        if (queue.size() == 0) return;
        Queue<BNode<AnyType>> nextLevel = new LinkedList<>();
        BNode<AnyType> t = queue.poll();
        if (stringQueue != null) {
            int size = stringQueue.size();
            for (int i = 0; i < size; ++i) {
                System.out.printf(stringQueue.poll());
            }
            System.out.println();
        }
        while (t != null) {
            System.out.printf(String.valueOf(t.ele) + " ");
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


    public static void main(String[] args) {
        MyTreeSet<Integer> binarySearchTree = new MyTreeSet<>();
        binarySearchTree.insert(4);
        binarySearchTree.insert(6);
        binarySearchTree.insert(2);
        binarySearchTree.insert(3);
        binarySearchTree.insert(1);
        binarySearchTree.insert(8);
        binarySearchTree.insert(5);
        binarySearchTree.remove(3);

        binarySearchTree.insert(3);
        binarySearchTree.printTree();

        Iterator<Integer> iterator = binarySearchTree.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();
        iterator = binarySearchTree.iterator();
        iterator.forEachRemaining(each -> {
            System.out.print(each.intValue());
        });
    }
}
