package com.xinghaol.programmer.tree;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/5/10 11:22 上午
 * @Description:
 */
public class LowestCommonAncestor {
    private Map<Integer, TreeNode> parent = new HashMap<>();
    private Set<Integer> set = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        // 记录p的父节点路径
        while (p != null) {
            set.add(p.val);
            p = parent.get(p.val);
        }
        // 找q的父路径上与p相同值的节点
        while (q != null) {
            if (set.contains(q.val)) {
                return q;
            } else {
                q = parent.get(q.val);
            }
        }
        return root;
    }

    /**
     * 访问整个树，记录其父子关系
     *
     * @param root
     */
    private void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        node1.left = node3;
        node1.right = node2;
        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(8);
        node2.left = node5;
        node2.right = node6;
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(4);
        node4.left = node7;
        node4.right = node8;

        LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();
        TreeNode node = lowestCommonAncestor.lowestCommonAncestor(root, node1, node2);
        System.out.println(node.toString());
    }
}
