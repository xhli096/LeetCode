package com.xinghaol.programmer.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: lixinghao
 * @date: 2020/6/24 1:00 上午
 * @Description: 1302. 层数最深叶子节点的和
 * https://leetcode-cn.com/problems/deepest-leaves-sum/
 */
public class DeepestLeavesSum {
    /**
     * 层次遍历，每层遍历完后，设置result，最后一层遍历结束，result值即为最后一层节点值
     *
     * @param root
     * @return
     */
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int currentNumber = 0;
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                currentNumber += currentNode.val;
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result = currentNumber;
        }

        return result;
    }
}
