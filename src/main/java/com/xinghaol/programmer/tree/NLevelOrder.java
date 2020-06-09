package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/6/8 11:43 下午
 * @Description: 429. N叉树的层序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 */
public class NLevelOrder {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node currentNode = queue.poll();
                list.add(currentNode.val);
                List<Node> children = currentNode.children;
                for (Node node : children) {
                    queue.offer(node);
                }
            }
            result.add(list);
        }

        return result;
    }
}
