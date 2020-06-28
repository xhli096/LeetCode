package com.xinghaol.programmer.array;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/27 9:52 上午
 * @Description: 628. 三个数的最大乘积
 * https://leetcode-cn.com/problems/maximum-product-of-three-numbers/
 */
public class MaximumProduct {
    /**
     * 首先进行无序数组的排序，有序数组如果全部为正数，则为后三个数字，为最大乘积
     * 如果有负数，则选择两个最小的负数，和最大的整数，即为最大乘积
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3], nums[0] * nums[1] * nums[nums.length - 1]);
    }
}
