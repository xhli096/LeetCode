package com.xinghaol.programmer.dp;

import com.alibaba.fastjson.JSON;

/**
 * @author: lixinghao
 * @date: 2020/5/18 10:03 下午
 * @Description: 152. 乘积最大子数组
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 */
public class MaxProduct {
    /**
     * 动态规划
     * weiwei哥题解：https://leetcode-cn.com/problems/maximum-product-subarray/solution/dong-tai-gui-hua-li-jie-wu-hou-xiao-xing-by-liweiw/
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        // dp[i][0] 存储以nums[i]为结尾的积的最小值；dp[i][1] 存储以nums[i]
        int[][] dp = new int[length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int max = nums[0];

        for (int i = 1; i < length; i++) {
            if (nums[i] >= 0) {
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][1]);
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][0]);
            } else {
                int val1 = Math.max(nums[i], nums[i] * dp[i - 1][0]);
                int val2 = Math.min(nums[i], nums[i] * dp[i - 1][1]);
                dp[i][0] = Math.min(val1, val2);
                dp[i][1] = Math.max(val1, val2);
            }

            max = Math.max(dp[i][1], max);
        }
        System.out.println(JSON.toJSONString(dp));

        return max;
    }

    public static void main(String[] args) {
        MaxProduct maxProduct = new MaxProduct();
        int product = maxProduct.maxProduct(new int[]{-4, -3});
        System.out.println(product);
    }
}
