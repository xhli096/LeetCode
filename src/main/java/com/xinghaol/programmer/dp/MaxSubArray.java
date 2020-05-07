package com.xinghaol.programmer.dp;

/**
 * @author: lixinghao
 * @date: 2020/5/3 2:48 下午
 * @Description:
 */
public class MaxSubArray {
    /**
     * current = dp[i-1] + nums[index];
     * current >  nums[index] => dp[i] = current;
     * else : dp[i] = nums[index];
     * max = Math.max(current, max);
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        if (nums.length == 1) {
            return nums[0];
        }
        int max = nums[0];
        int[] dp = new int[length];
        dp[0] = nums[0];
        int index = 0;
        for (index = 1; index < length; index++) {
            int current = dp[index - 1] + nums[index];
            if (current < nums[index]) {
                dp[index] = nums[index];
            } else {
                dp[index] = current;
            }
            current = dp[index];
            max = Math.max(current, max);
        }

        return max;
    }

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        maxSubArray.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
    }
}
