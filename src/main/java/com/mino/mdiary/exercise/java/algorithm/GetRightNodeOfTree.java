package com.mino.mdiary.exercise.java.algorithm;

import com.mino.mdiary.exercise.java.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class GetRightNodeOfTree {

    /**
     * 获取一棵二叉树每一层最右边的节点
     * 如
     *          a
     *         / \
     *        b  c
     *       /\  /\
     *      d e f g    ->   acg
     *
     *
     *          a
     *         / \
     *        b  c
     *       /\  /
     *      d e f      ->   acf
     *
     *          a
     *         / \
     *        b  c
     *       /\  /
     *      d e f
     *       /
     *      g          ->   acfg
     */
    public static void main(String[] args) {
        TreeNode g = new TreeNode("g");
        TreeNode e = new TreeNode("e");
        TreeNode d = new TreeNode("d", null, g);
        TreeNode b = new TreeNode("b", d, e);
        TreeNode f = new TreeNode("f");
        TreeNode c = new TreeNode("c", f, null);
        TreeNode a = new TreeNode("a", b, c);

//        TreeNode g = new TreeNode("g");
//        TreeNode e = new TreeNode("e", g, null);
//        TreeNode d = new TreeNode("d");
//        TreeNode b = new TreeNode("b", d, e);
//        TreeNode f = new TreeNode("f");
//        TreeNode c = new TreeNode("c", f, null);
//        TreeNode a = new TreeNode("a", b, c);

        List<TreeNode> resultNodes = new ArrayList<>();
        getRightNode(a, 0, -10000, resultNodes);
        resultNodes.forEach(input -> System.out.println(input.value));
    }

    public static int getRightNode(TreeNode node, int currentDepth, int maxDepth, List<TreeNode> resultNodes) {
        if (node == null) {
            return maxDepth;
        }
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
            resultNodes.add(node);
            maxDepth = getRightNode(node.right, currentDepth+1, maxDepth, resultNodes);
            maxDepth = getRightNode(node.left, currentDepth+1, maxDepth, resultNodes);
        } else {
            maxDepth = getRightNode(node.right, currentDepth + 1, maxDepth, resultNodes);
            maxDepth = getRightNode(node.left, currentDepth+1, maxDepth, resultNodes);
        }
        return maxDepth;
    }

}
