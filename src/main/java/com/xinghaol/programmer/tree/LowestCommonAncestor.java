package com.xinghaol.programmer.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/5/10 11:22 上午
 * @Description: 236. 二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
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

    /**
     * 终止条件：
     * 当越过叶节点，则直接返回 null ；
     * 当 root 等于 p, q，则直接返回 root ；
     * 递推工作：
     * 开启递归左子节点，返回值记为 left ；
     * 开启递归右子节点，返回值记为 right ；
     * 返回值： 根据 left 和 right ，可展开为四种情况；
     * 1、当 left 和 right 同时为空 ：说明 root 的左 / 右子树中都不包含 p, q ，返回 null ；
     * 2、当 left 和 right 同时不为空 ：说明 p, q 分列在 root 的 异侧 （分别在 左 / 右子树），因此 root 为最近公共祖先，返回 root ；
     * 3、当 left 为空 ，right 不为空 ：p, q 都不在 root 的左子树中，直接返回 right 。具体可分为两种情况：
     * 3.1、p, q 其中一个在 root 的 右子树 中，此时 right 指向 p（假设为 p ）；
     * 3.2、p, q 两节点都在 root 的 右子树 中，此时的 right 指向 最近公共祖先节点 ；
     * 4、当 left 不为空 ， right 为空 ：与情况 3. 同理；
     * <p>
     * 复杂度分析：
     * 时间复杂度 O(N) ： 其中 N 为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度 O(N) ： 最差情况下，递归深度达到 N ，系统使用 O(N) 大小的额外空间。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        // 不在左子树上
        if (left == null) {
            return right;
        }
        // 不在右子树上
        if (right == null) {
            return left;
        }

        return root;
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

    /**
     * 235. 二叉搜索树的最近公共祖先
     * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
     * 终止条件：
     * * 当越过叶节点，则直接返回 null ；
     * * 当 root 等于 p, q，则直接返回 root ；
     * * 递推工作：
     * * 开启递归左子节点，返回值记为 left ；
     * * 开启递归右子节点，返回值记为 right ；
     * * 返回值： 根据 left 和 right ，可展开为四种情况；
     * * 1、当 left 和 right 同时为空 ：说明 root 的左 / 右子树中都不包含 p, q ，返回 null ；
     * * 2、当 left 和 right 同时不为空 ：说明 p, q 分列在 root 的 异侧 （分别在 左 / 右子树），因此 root 为最近公共祖先，返回 root ；
     * * 3、当 left 为空 ，right 不为空 ：p, q 都不在 root 的左子树中，直接返回 right 。具体可分为两种情况：
     * * 3.1、p, q 其中一个在 root 的 右子树 中，此时 right 指向 p（假设为 p ）；
     * * 3.2、p, q 两节点都在 root 的 右子树 中，此时的 right 指向 最近公共祖先节点 ；
     * * 4、当 left 不为空 ， right 为空 ：与情况 3. 同理；
     * * <p>
     * * 复杂度分析：
     * * 时间复杂度 O(N) ： 其中 N 为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
     * * 空间复杂度 O(N) ： 最差情况下，递归深度达到 N ，系统使用 O(N) 大小的额外空间。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor3(root.left, p, q);
        TreeNode right = lowestCommonAncestor3(root.right, p, q);
        // 不在左子树上
        if (left == null) {
            return right;
        }
        // 不在右子树上
        if (right == null) {
            return left;
        }

        return root;
    }
}
