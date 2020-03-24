package com.xinghaol.programmer;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/3/24 2:16 下午
 * @Description: 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 */
public class MaxDepth {
    /**
     * 时间复杂度：我们每个结点只访问一次，因此时间复杂度为 O(N)，
     * 其中 N 是结点的数量。
     * <p>
     * 空间复杂度：在最糟糕的情况下，树是完全不平衡的，例如每个结点只剩下左子结点，递归将会被调用 N 次（树的高度），
     * 因此保持调用栈的存储将是 O(N)。但在最好的情况下（树是完全平衡的），树的高度将是 log(N)。
     * 因此，在这种情况下的空间复杂度将是 O(log(N))。
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        int depth = Math.max(left, right);
        return 1 + depth;
    }

    /**
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)。
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }
        int depth = 0;
        while (!stack.isEmpty()) {
            // 返回第一个元素
            Pair<TreeNode, Integer> currentNode = stack.poll();
            root = currentNode.getKey();
            int currentDepth = currentNode.getValue();
            if (root != null) {
                depth = Math.max(depth, currentDepth);
                stack.add(new Pair<>(root.left, currentDepth + 1));
                stack.add(new Pair<>(root.right, currentDepth + 1));
            }
        }

        return depth;
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
