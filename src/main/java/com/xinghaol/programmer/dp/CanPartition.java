package com.xinghaol.programmer.dp;

/**
 * @author: lixinghao
 * @date: 2020/5/9 2:25 下午
 * @Description:
 */
public class CanPartition {
    public boolean canPartition(int[] nums) {
        int length = nums.length;
        // 当数组长度为0时
        if (length == 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            // 当sum为奇数时，一定无法分为两部分相同的数组
            return false;
        }
        int target = sum / 2;
        // 对dp数组进行初始化
        boolean[][] dp = new boolean[length][target + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        // 第一行单独处理
        if (nums[0] < target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j - nums[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[length - 1][target];
    }

    public boolean canPartition2(int[] nums) {
        return false;
    }

    public static void main(String[] args) {
        CanPartition canPartition = new CanPartition();
        boolean b = canPartition.canPartition(new int[]{1, 5, 11, 5});
        System.out.println(b);
    }
}
