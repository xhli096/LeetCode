package com.xinghaol.programmer.tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/6/23 7:21 下午
 * @Description: 114. 二叉树展开为链表
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 */
public class Flatten {
    /**
     * 先序遍历，将左子树节点，均连接到右子树节点上
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            list.add(current);
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        root = list.removeFirst();
        root.left = null;
        TreeNode current = root;
        while (list.size() > 0) {
            current.right = list.removeFirst();
            current.right.left = null;
            current = current.right;
        }
    }
}
