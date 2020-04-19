package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/4/16 10:50 下午
 * @Description: 二叉树的层次遍历 II
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 */
public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            result.add(list);
            return result;
        }
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.add(root);
        Stack<List<Integer>> stack = new Stack<>();
        while (!treeNodeQueue.isEmpty()) {
            int size = treeNodeQueue.size();
            List<Integer> currentLevelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = treeNodeQueue.poll();
                currentLevelList.add(currentNode.val);
                if (currentNode.left != null) {
                    treeNodeQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    treeNodeQueue.offer(currentNode.right);
                }
            }
            stack.push(currentLevelList);
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
