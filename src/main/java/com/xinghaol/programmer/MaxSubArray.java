package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/25 6:26 下午
 * @Description: 连续子数组的最大和
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 * 示例1:
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 提示：
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 */
public class MaxSubArray {
    /**
     * 动态规划
     * 如果前一个元素为负数，则为做了负贡献。如果为整数，则统计。
     * num[i-1] <= 0 , num[i] = num[i] + 0;
     * num[i-1] > 0, num[i] = num[i] + num[i-1];
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 0) {
            return 0;
        }
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }

        return res;
    }
}
