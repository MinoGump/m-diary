package com.mino.mdiary.exercise.java.struct;

public class TreeNode {

    public String value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(String val) {
        this.value = val;
    }

    public TreeNode(String val, TreeNode l, TreeNode r) {
        this.value = val;
        this.left = l;
        this.right = r;
    }
}
