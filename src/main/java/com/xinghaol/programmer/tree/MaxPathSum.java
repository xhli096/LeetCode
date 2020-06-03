package com.xinghaol.programmer.tree;

/**
 * @author: lixinghao
 * @date: 2020/6/3 10:47 下午
 * @Description: 124. 二叉树中的最大路径和
 */
public class MaxPathSum {
    private int max = Integer.MIN_VALUE;

    /**
     * 使用递归的方法
     * 因为根节点有为负值的可能性
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);

        return max;
    }

    /**
     * 返回经过root的单边分支最大和， 即Math.max(root, root+left, root+right)
     * 手工过程一下
     *
     * @param root
     * @return
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 如果某一个分支路径为负数，则不选取那个分支
        int leftMax = Math.max(0, dfs(root.left));
        int rightMax = Math.max(0, dfs(root.right));
        max = Math.max(max, root.val + leftMax + rightMax);

        // 返回经由root的路径上的最大值
        // 返回上一层的路径，一定不可能同时经过左子树和右子树
        return root.val + Math.max(leftMax, rightMax);
    }
}
