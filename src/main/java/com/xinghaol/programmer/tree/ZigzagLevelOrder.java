package com.xinghaol.programmer.tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/5/18 10:48 下午
 * @Description: 103. 二叉树的锯齿形层次遍历
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        if (root.left == null && root.right == null) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            result.add(list);
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println(JSON.toJSONString(queue));
            List<Integer> list = new ArrayList<>(size);
            Collections.fill(list, 0);
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                list.add(current.val);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            if (index % 2 != 0) {
                Collections.reverse(list);
            }
            result.add(list);
            index++;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode l = new TreeNode(2);
        TreeNode r = new TreeNode(4);
        root.left = l;
        root.right = r;

        TreeNode lr = new TreeNode(1);
        l.left = lr;
        TreeNode lrl = new TreeNode(5);
        TreeNode lrr = new TreeNode(1);
        lr.left = lrl;
        lr.right = lrr;

        TreeNode rl = new TreeNode(3);
        TreeNode rr = new TreeNode(-1);
        r.left = rl;
        r.right = rr;

        TreeNode rlr = new TreeNode(6);
        rl.right = rlr;
        TreeNode rrr = new TreeNode(8);
        rr.right = rrr;


        TreeNode treeNode = new TreeNode(1);
        ZigzagLevelOrder zigzagLevelOrder = new ZigzagLevelOrder();
        List<List<Integer>> lists = zigzagLevelOrder.zigzagLevelOrder(root);
        System.out.println(JSON.toJSONString(lists));
    }
}
