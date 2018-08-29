package com.mino.mdiary.exercise.java.algorithm;

import com.mino.mdiary.exercise.java.struct.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class TraverseTree<T> {

    public enum TraverseOrder {
        PRE_ORDER,
        IN_ORDER,
        POST_ORDER
    }

    private static final Function<TreeNode, Void> FUNCTION = new Function<TreeNode, Void>() {
        @Override
        public Void apply(TreeNode treeNode) {
            System.out.print(treeNode.value + " ");
            return null;
        }
    };

    public static void traverseTreeByPreOrder(TreeNode treeNode, Function<TreeNode, ? extends Object> function) {
        if (treeNode != null) {
            function.apply(treeNode);
            traverseTreeByPreOrder(treeNode.left, function);
            traverseTreeByPreOrder(treeNode.right, function);
        }
    }

    public static void traverseTreeByInOrder(TreeNode treeNode, Function<TreeNode, ? extends Object> function) {
        if (treeNode != null) {
            traverseTreeByInOrder(treeNode.left, function);
            function.apply(treeNode);
            traverseTreeByInOrder(treeNode.right, function);
        }
    }

    public static void traverseTreeByPostOrder(TreeNode treeNode, Function<TreeNode, ? extends Object> function) {
        if (treeNode != null) {
            traverseTreeByPostOrder(treeNode.left, function);
            traverseTreeByPostOrder(treeNode.right, function);
            function.apply(treeNode);
        }
    }

    public static void traverseTreeByPreOrder1(TreeNode treeNode, final Function<TreeNode, ? extends Object> function) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        treeNodeStack.push(treeNode);
        while (!treeNodeStack.isEmpty()) {
            treeNode = treeNodeStack.pop();
            function.apply(treeNode);
            if (treeNode.right != null) {
                treeNodeStack.add(treeNode.right);
            }
            if (treeNode.left != null) {
                treeNodeStack.add(treeNode.left);
            }
        }
    }

    public static void traverseTreeByInOrder1(TreeNode treeNode, final Function<TreeNode, ? extends Object> function) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        Stack<TreeNode> treeNodeStack = new Stack<>();
        TreeNode tempTreeNode = treeNode;
        while (tempTreeNode != null || !treeNodeStack.isEmpty()) {
            while (tempTreeNode != null) {
                treeNodeStack.add(tempTreeNode);
                tempTreeNode = tempTreeNode.left;
            }
            tempTreeNode = treeNodeStack.pop();
            treeNodeList.add(tempTreeNode);
            tempTreeNode = tempTreeNode.right;
        }
        treeNodeList.stream().forEach(each -> {function.apply(each);});
    }

    public static void traverseTreeByPostOrder1(TreeNode treeNode, final Function<TreeNode, ? extends Object> function) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        Stack<TreeNode> treeNodeStack = new Stack<>();
        treeNodeStack.push(treeNode);
        while (!treeNodeStack.isEmpty()) {
            TreeNode tempTreeNode = treeNodeStack.pop();
            if (tempTreeNode != null) {
                treeNodeList.add(tempTreeNode);
                treeNodeStack.push(tempTreeNode.left);
                treeNodeStack.push(tempTreeNode.right);
            }
        }
        Collections.reverse(treeNodeList);
        treeNodeList.forEach(each -> function.apply(each));
    }


    public static void traverseTree(TreeNode treeNode, Function<TreeNode, ? extends Object> function, TraverseOrder traverseOrder) {
        if (traverseOrder == null) {
            return;
        } else if (traverseOrder == TraverseOrder.PRE_ORDER) {
            traverseTreeByPreOrder(treeNode, function);
        } else if (traverseOrder == TraverseOrder.IN_ORDER) {
            traverseTreeByInOrder(treeNode, function);
        } else if (traverseOrder == TraverseOrder.POST_ORDER) {
            traverseTreeByPostOrder(treeNode, function);
        }
    }


    public static void traverseTree1(TreeNode treeNode, Function<TreeNode, ? extends Object> function, TraverseOrder traverseOrder) {
        if (traverseOrder == null) {
            return;
        } else if (traverseOrder == TraverseOrder.PRE_ORDER) {
            traverseTreeByPreOrder1(treeNode, function);
        } else if (traverseOrder == TraverseOrder.IN_ORDER) {
            traverseTreeByInOrder1(treeNode, function);
        } else if (traverseOrder == TraverseOrder.POST_ORDER) {
            traverseTreeByPostOrder1(treeNode, function);
        }
    }

    /**
     *
     *         a
     *        / \
     *       b  c
     *      /\  /
     *     d e f
     *     \
     *     g
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode g = new TreeNode("g");
        TreeNode e = new TreeNode("e");
        TreeNode d = new TreeNode("d", null, g);
        TreeNode b = new TreeNode("b", d, e);
        TreeNode f = new TreeNode("f");
        TreeNode c = new TreeNode("c", f, null);
        TreeNode a = new TreeNode("a", b, c);

        traverseTree(a, FUNCTION, TraverseOrder.POST_ORDER);
        System.out.println();
        traverseTree1(a, FUNCTION, TraverseOrder.POST_ORDER);

    }
}
