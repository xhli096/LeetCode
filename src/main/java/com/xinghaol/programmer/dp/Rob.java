package com.xinghaol.programmer.dp;

import com.xinghaol.programmer.tree.TreeNode;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/5/13 2:16 下午
 * @Description: 198. 打家劫舍
 */
public class Rob {
    private int[] memo;

    /**
     * 打家劫舍1
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        memo = new int[length];
        Arrays.fill(memo, -1);
        return dp(nums, 0);
    }

    private int dp(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }
        int res = 0;

        if (memo[start] != -1) {
            res = memo[start];
        } else {
            res = Math.max(dp(nums, start + 1),
                    nums[start] + dp(nums, start + 2));
        }
        memo[start] = res;

        return res;
    }

    /**
     * 递归处理，实质是从后向前的运算。但是在大数据规模的情况下，会超时。
     * 发现在计算过程，start+1 start+2等，会计算很多重复的状态，可以使用备忘录进行记录；
     *
     * @param nums
     * @param start
     * @return
     */
    private int dp2(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }

        int res = Math.max(dp(nums, start + 1),
                nums[start] + dp(nums, start + 2));

        return res;
    }

    public static void main(String[] args) {
        Rob rob = new Rob();
        int rob1 = rob.rob(new int[]{2, 7, 9, 3, 1});
        System.out.println(rob1);
        System.out.println(rob.dp2(new int[]{2, 7, 9, 3, 1}, 0));
    }

    /**
     * 213. 打家劫舍 II
     * https://leetcode-cn.com/problems/house-robber-ii/
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        return 0;
    }


    /**
     * 打家劫舍3
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 示例 1:
     * 输入: [3,2,3,null,3,null,1]
     *
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     *
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     * 示例 2:
     * 输入: [3,4,5,1,3,null,1]
     *
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     *
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
     *
     * @param root
     * @return
     */
    public int rob3(TreeNode root) {
        return 0;
    }
}
