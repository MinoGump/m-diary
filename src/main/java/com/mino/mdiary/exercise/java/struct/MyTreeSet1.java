package com.mino.mdiary.exercise.java.struct;

import java.util.*;
import java.util.function.Consumer;

/**
 * 实现一颗TreeSet，它的迭代器是一个二叉查找树，且每个树节点都存储它的前驱链和后继链
 */
public class MyTreeSet1<AnyType extends Comparable<? super AnyType>> {

    private BNode<AnyType> root;
    private int modCount = 0;
    private AnyType min;        // 删除当前node下的最小值存储用

    private static class BNode<AnyType> {
        BNode<AnyType> lt;
        BNode<AnyType> gt;
        BNode<AnyType> left;
        BNode<AnyType> right;
        AnyType ele;

        public BNode() {
        }

        public BNode(AnyType element) {
            this(null, null, null, null, element);
        }

        public BNode(BNode<AnyType> lt, BNode<AnyType> gt, BNode<AnyType> left, BNode<AnyType> right, AnyType ele) {
            this.lt = lt;
            this.gt = gt;
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
        root = insert(x, root, null, null);
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

    public void printFromLToR() {
        BNode<AnyType> minNode = findMin(root);

        while (minNode != null) {
            System.out.printf(minNode.ele.toString());
            minNode = minNode.gt;
        }
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
            MyTreeSet1.this.remove(previous.ele, root);
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
            if (current.gt != null) {
                current = current.gt;
            } else {
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
            BNode<AnyType> lt = node.lt;
            BNode<AnyType> gt = node.gt;
            node.right = removeMin(node.right);
            node.ele = min;
            lt.gt = gt;
            gt.lt = lt;
        } else {
            BNode<AnyType> lt = node.lt;
            BNode<AnyType> gt = node.gt;
            lt.gt = gt;
            gt.lt = lt;
            node = node.left != null ? node.left : node.right;
        }
        return node;
    }

//    private BNode<AnyType> remove(BNode<AnyType> node) {
//        if (node == null) return null;
//        if (node.left != null && node.right != null) {
//            node.right = removeMin(node);
//            node.ele = min;
//        } else {
//            node = node.left != null ? node.left : node.right;
//        }
//        return node;
//    }

    private BNode<AnyType> removeMin(BNode<AnyType> root) {
        if (root == null) return null;
        if (root.left == null) {
            min = root.ele;
            return root.right;
        }
        root.left = removeMin(root.left);
        return root;
    }

    private BNode<AnyType> insert(AnyType x, BNode<AnyType> node, BNode<AnyType> maxLt, BNode<AnyType> minGt) {
        if (node == null) {
            BNode<AnyType> resultNode = new BNode<>(maxLt, minGt, null, null, x);
            if (maxLt != null) {
                maxLt.gt = resultNode;
            }
            if (minGt != null) {
                minGt.lt = resultNode;
            }
            return resultNode;
        }
        int compareResult = x.compareTo(node.ele);
        if (compareResult < 0) {
            if (minGt != null && minGt.ele.compareTo(node.ele) > 0) minGt = node;
            else if (minGt == null && node.ele.compareTo(x) > 0) minGt = node;
            node.left = insert(x, node.left, maxLt, minGt);
        } else if (compareResult > 0) {
            if (maxLt != null && maxLt.ele.compareTo(node.ele) < 0) maxLt = node;
            else if (maxLt == null && node.ele.compareTo(x) < 0) maxLt = node;
            node.right = insert(x, node.right, maxLt, minGt);
        } else {
            // do nothing
        }
        return node;
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


    /**
     * 判断node1 的左节点是否为 node2
     * @param node1
     * @param node2
     * @return
     */
    private boolean isLeftChild(BNode<AnyType> node1, BNode<AnyType> node2) {
        return node1 != null && node1.left != null && node1.left.equals(node2);
    }


    public static void main(String[] args) {
        MyTreeSet1<Integer> binarySearchTree = new MyTreeSet1<>();
        binarySearchTree.insert(4);
        binarySearchTree.insert(6);
        binarySearchTree.insert(2);
        binarySearchTree.insert(3);
        binarySearchTree.insert(1);
        binarySearchTree.remove(3);
        binarySearchTree.insert(8);
        binarySearchTree.insert(5);

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

        binarySearchTree.printTree();
        binarySearchTree.printFromLToR();
    }
}
