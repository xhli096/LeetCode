package com.xinghaol.programmer.tree;

/**
 * @author: lixinghao
 * @date: 2020/5/16 11:01 下午
 * @Description:
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
