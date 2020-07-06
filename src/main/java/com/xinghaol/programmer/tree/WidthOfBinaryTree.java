package com.xinghaol.programmer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/6/27 11:16 下午
 * @Description: 662. 二叉树最大宽度
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 */
public class WidthOfBinaryTree {
    /**
     * 层次遍历，为子节点编序号，利用父节点和子节点序号的关系，左子树序号=根节点序号*2；右子树序号=根节点*2+1
     * 每一层宽度为，最右侧节点序号-最左侧序号节点
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        int max = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                Integer currentIndex = list.removeFirst();
                if (current.left != null) {
                    queue.offer(current.left);
                    list.add(currentIndex * 2);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                    list.add(currentIndex * 2 + 1);
                }
            }
            // list 中 size 为 1 的情况下，宽度也为 1，没有必要计算。
            if (list.size() >= 2) {
                max = Math.max(max, list.getLast() - list.getFirst() + 1);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rl = new TreeNode(3);
        TreeNode rr = new TreeNode(2);
        root.left = rl;
        root.right = rr;

        TreeNode rll = new TreeNode(5);
        TreeNode rlr = new TreeNode(3);
        rl.left = rll;
        rl.right = rlr;

        TreeNode rrr = new TreeNode(9);
        rr.right = rrr;

        WidthOfBinaryTree widthOfBinaryTree = new WidthOfBinaryTree();
        int width = widthOfBinaryTree.widthOfBinaryTree(root);
        System.out.println(width);
    }
}
