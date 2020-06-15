package com.xinghaol.programmer.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: lixinghao
 * @date: 2020/6/15 2:43 下午
 * @Description: 112. 路径总和
 * https://leetcode-cn.com/problems/path-sum/
 */
public class HasPathSum {
    /**
     * 非递归迭代方法，采用减法+先序遍历的方法
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> valueStack = new ArrayDeque<>();
        stack.push(root);
        valueStack.push(sum - root.val);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int currentSum = valueStack.pop();
            if (currentSum == 0 && node.left == null && node.right == null) {
                return true;
            }
            if (node.right != null) {
                stack.push(node.right);
                valueStack.push(currentSum - node.right.val);
            }
            if (node.left != null) {
                stack.push(node.left);
                valueStack.push(currentSum - node.left.val);
            }
        }

        return false;
    }

    /**
     * 递归，减法求解
     * 时间复杂度：O(N)，每个节点都需要遍历一次
     * 空间复杂度：最坏情况下，整棵树是非平衡的，例如每个节点都只有一个孩子，递归会调用 N 次（树的高度），因此栈的空间开销是O(N)
     * 最好情况下，整棵树是完全平衡的，高度只有 log(N)，O(log(N)).
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        sum -= root.val;
        // 达到叶子节点，判断是否为0
        if (root.left == null && root.right == null) {
            return sum == 0;
        }

        return hasPathSum2(root.left, sum) || hasPathSum2(root.right, sum);
    }
}
