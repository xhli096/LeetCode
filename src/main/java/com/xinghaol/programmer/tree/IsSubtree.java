package com.xinghaol.programmer.tree;

/**
 * @author: lixinghao
 * @date: 2020/5/7 4:54 下午
 * @Description:
 */
public class IsSubtree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        }
        if (s == null) {
            return false;
        }

        return isSameTree(s, t) || isSameTree(s.left, t) || isSameTree(s.right, t);
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (t == null && s == null) {
            return true;
        }
        if (s != null && t == null) {
            return false;
        }
        if (s == null && t != null) {
            return false;
        }

        return s.val == t.val && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    public boolean isSubtree2(TreeNode s, TreeNode t) {
        // 要匹配的头节点 t 为 null 则 t 树一定为 s 树的子树。
        if (t == null) {
            return true;
        }
        // 要匹配的 t 不为空情况下，如果匹配树的头节点 s 为空则不符合。
        if (s == null) {
            return false;
        }
        // 根节点开始或者在左子树和右子树中匹配 t 树。
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSameNode(s,t);
    }

    public boolean isSameNode(TreeNode s, TreeNode t){
        // 两个节点都为空，则表示两个节点相同。
        if (s == null && t == null) {
            return true;
        }
        // 不同时为空的情况下如果有一个节点为空，则表示两个节点不相同。
        if (s == null || t == null) {
            return false;
        }
        // 两个节点值不一样，表示两个节点不相同。
        if (s.val != t.val) {
            return false;
        }
        // 继续遍历比较左右子节点。
        return isSameNode(s.left, t.left) && isSameNode(s.right, t.right);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {

        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
