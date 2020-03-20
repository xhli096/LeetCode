package com.xinghaol.programmer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/3/19 10:44 下午
 * @Description: 中序遍历二叉树
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class InorderTraversal {
    /**
     * 递归计算二叉树中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();

        result.addAll(inorderTraversal(root.left));
        result.add(root.val);
        result.addAll(inorderTraversal(root.right));

        return result;
    }

    /**
     * 非递归中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();

        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        TreeNode parent = root;
        while (parent != null || !treeNodeStack.isEmpty()) {
            if (parent != null) {
                treeNodeStack.push(parent);
                parent = parent.left;
            } else {
                parent = treeNodeStack.pop();
                result.add(parent.val);
                parent = parent.right;
            }
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
