package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/6/24 8:33 上午
 * @Description: 314. 二叉树的垂直遍历
 * https://leetcode-cn.com/problems/binary-tree-vertical-order-traversal/
 */
public class VerticalOrder {
    /**
     * 根据题目要求，当前节点坐标为x，则其左子树节点坐标为x-1，右子树节点坐标为x+1，则把根节点的值是为0；
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 层次遍历，并记录所在列数，参照根节点，根节点所在为第0列
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> indexQueue = new LinkedList<>();
        queue.offer(root);
        indexQueue.offer(0);

        // 记录已经访问过的列数
        Set<Integer> set = new HashSet<>();
        // 偏移量
        int offset = 0;

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            int currentIndex = indexQueue.poll();
            if (!set.contains(currentIndex)) {
                if (currentIndex >= 0) {
                    result.add(new LinkedList<>());
                } else {
                    result.add(0, new LinkedList<>());
                    offset++;
                }
                set.add(currentIndex);
            }
            // 根据偏移量获得linkedList，并且把值加进去
            result.get(offset + currentIndex).add(currentNode.val);

            if (currentNode.left != null) {
                queue.offer(currentNode.left);
                indexQueue.offer(currentIndex - 1);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
                indexQueue.offer(currentIndex + 1);
            }
        }

        return result;
    }
}
