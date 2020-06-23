package com.xinghaol.programmer.tree;

import com.alibaba.fastjson.JSON;

/**
 * @author: lixinghao
 * @date: 2020/6/22 2:25 下午
 * @Description: 108. 将有序数组转换为二叉搜索树
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return null;
        }

        return buildBinarySearchTree(nums, 0, length - 1);
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

    public static void main(String[] args) {
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        TreeNode treeNode = sortedArrayToBST.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(JSON.toJSONString(treeNode));
    }
}
