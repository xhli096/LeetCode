package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/6/28 2:58 下午
 * @Description: 1038. 从二叉搜索树到更大和树
 * https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/
 */
public class BstToGst {
    /**
     * 恢复得到的树有时结构发生了变化，虽然也是二叉搜索树
     *
     * @param root
     * @return
     */
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                currentNode = node.right;
            }
        }
        int[] pointArrays = new int[list.size()];
        int pre = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            pointArrays[i] = list.get(i) + pre;
            pre = pointArrays[i];
        }

        return buildBinarySearchTree(pointArrays, 0, pointArrays.length - 1);
    }

    private TreeNode buildBinarySearchTree(int[] nums, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return null;
        }
        int middle = startIndex + (endIndex - startIndex) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = buildBinarySearchTree(nums, startIndex, middle - 1);
        root.right = buildBinarySearchTree(nums, middle + 1, endIndex);

        return root;
    }

    /**
     * 根据题意发现是二叉搜索树中序遍历后，得到的每个节点变化后的最大值，为后缀和相加。
     * 反向中序遍历
     *
     * @param root
     * @return
     */
    private int sum = 0;

    public TreeNode bstToGst2(TreeNode root) {
        if (root != null) {
            bstToGst2(root.right);
            sum = sum + root.val;
            root.val = sum;
            bstToGst2(root.left);
        }
        return root;
    }
}
