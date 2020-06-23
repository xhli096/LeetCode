package com.xinghaol.programmer.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: lixinghao
 * @date: 2020/6/18 11:31 下午
 * @Description: 226. 翻转二叉树
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class InvertTree {
    /**
     * 递归解法
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    /**
     * 迭代解法
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            TreeNode left = current.left;
            current.left = current.right;
            current.right = left;
            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }

        return root;
    }
}
