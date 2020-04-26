package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/4/22 9:50 下午
 * @Description: 二叉树的右视图
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 */
public class RightSideView {
    /**
     * 利用BFS层次遍历方法来解决，记录每一层最右边的那个元素
     * 时间复杂度：O(N)，每个节点都只遍历了一遍
     * 空间复杂度：O(N)，使用了额外的队列空间
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        if (root.left == null && root.right == null) {
            result.add(root.val);
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
                if (i + 1 == size) {
                    result.add(treeNode.val);
                }
            }
        }

        return result;
    }

    public List<Integer> rightSideView2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        if (root.left == null && root.right == null) {
            result.add(root.val);
            return result;
        }


        return result;
    }

    private void dfs(List<Integer> list, TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        // 先访问根节点 -》 右子树节点 -》 左子树节点
        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，
        // 又因为每次我们先访问右子树，所以当前节点一定是最右面的一个节点，因此将当前节点加入res中。
        if (depth == list.size()) {
            list.add(node.val);
        }
        depth++;
        dfs(list, node.right, depth);
        dfs(list, node.left, depth);
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
