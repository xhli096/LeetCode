package com.xinghaol.programmer;

import javax.swing.tree.TreeNode;

/**
 * @author: lixinghao
 * @date: 2020/3/10 12:50 下午
 * @Description: 找最长的路径，就是左右子树最深的节点。可能不为根节点，利用递归，每个节点都去找左右最深的节点。
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 */
public class DiameterOfBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root != null) {
            max(root);
            return max;
        }
        return 0;
    }

    private int max(TreeNode root) {
/*        if (root != null) {
            int leftMax = max(root.left);
            int rightMax = max(root.right);
            if (leftMax + rightMax > max) {
                max = leftMax + rightMax;
            }

            return Math.max(leftMax, rightMax) + 1;
        }*/
        return 0;
    }
}
