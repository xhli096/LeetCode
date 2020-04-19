package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/4/15 11:42 下午
 * @Description: 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 * 示例：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class SumOfLeftLeaves {
    /**
     * 注意是求叶子节点
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                // 此时是叶子节点，treeNode.left.left == null && treeNode.left.right == null, 二者均满足才是叶子节点
                if (treeNode.left != null && treeNode.left.left == null && treeNode.left.right == null) {
                    list.add(treeNode.left.val);
                }
                treeNode = treeNode.left;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop().right;
            }
        }
        int result = 0;
        for (Integer integer : list) {
            result += integer;
        }

        return result;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
