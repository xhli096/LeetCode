package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/6/27 10:05 上午
 * @Description: 513. 找树左下角的值
 * https://leetcode-cn.com/problems/find-bottom-left-tree-value/
 */
public class FindBottomLeftValue {
    /**
     * 层次遍历，选择最后一层的最左边的一个节点
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                integers.add(current.val);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            list.add(integers);
        }

        return list.get(list.size() - 1).get(0);
    }
}
