package com.mino.mdiary.exercise.java.algorithm;

import com.mino.mdiary.exercise.java.struct.TreeNode;

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
            System.out.println(treeNode.value);
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

    public static void traverseTreeByPreOrder1(TreeNode treeNode, Function<TreeNode, ? extends Object> function) {
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

    public static void main(String[] args) {
        TreeNode g = new TreeNode("g");
        TreeNode e = new TreeNode("e");
        TreeNode d = new TreeNode("d", null, g);
        TreeNode b = new TreeNode("b", d, e);
        TreeNode f = new TreeNode("f");
        TreeNode c = new TreeNode("c", f, null);
        TreeNode a = new TreeNode("a", b, c);

        traverseTreeByPreOrder1(a, FUNCTION);
        traverseTreeByPreOrder(a, FUNCTION);
    }
}
