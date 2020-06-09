package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/6/8 10:23 上午
 * @Description: 144. 二叉树的前序遍历
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class PreorderTraversal {
    /**
     * 非递归遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            result.add(currentNode.val);
            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }

        return result;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        preorder(root, result);

        return result;
    }

    private void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    /**
     * 栈中只保存右子树节点
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> output = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        // 只将右节点入栈，左节点与自身节点直接访问
        TreeNode temp = root;
        while (temp != null) {
            // 访问自身节点
            output.add(temp.val);
            // 如果存在右节点则入栈
            if (temp.right != null) {
                stack.push(temp.right);
            }
            // 访问左节点
            if (temp.left != null) {
                temp = temp.left;
            } else {
                // 弹出栈顶节点
                if (!stack.isEmpty()) {
                    temp = stack.pop();
                } else {
                    break;
                }
            }
        }
        return output;
    }
}
