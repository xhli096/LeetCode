package com.xinghaol.programmer.week.week0524;

import com.xinghaol.programmer.tree.TreeNode;

/**
 * @author: lixinghao
 * @date: 2020/5/24 11:27 上午
 * @Description: 5418. 二叉树中的伪回文路径
 * https://leetcode-cn.com/contest/weekly-contest-190/problems/pseudo-palindromic-paths-in-a-binary-tree/
 */
public class PseudoPalindromicPaths {
    int r = 0;

    private void go(TreeNode rt, int[] d) {
        if (rt == null) {
            return;
        }
        // 当前节点的值+1
        d[rt.val]++;
        // 当前节点为叶子节点。
        if (rt.left == null && rt.right == null) {
            int ct = 0;
            // 统计当前的路径上，值为奇数的个数。如果为奇数的个数 > 1，那么一定不能组成回文串。
            for (int i = 1; i <= 9; ++i) {
                if (d[i] % 2 == 1) {
                    ct++;
                }
            }
            if (ct == 1 || ct == 0) {
                r++;
            }
            d[rt.val]--;
            return;
        }
        // 递归处理
        go(rt.left, d);
        go(rt.right, d);
        d[rt.val]--;
    }


    public int pseudoPalindromicPaths(TreeNode root) {
        go(root, new int[10]);
        return r;
    }
}
