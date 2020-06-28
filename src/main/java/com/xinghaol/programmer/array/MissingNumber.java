package com.xinghaol.programmer.array;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/27 9:36 下午
 * @Description:
 */
public class MissingNumber {
    /**
     * 268. 缺失数字
     * https://leetcode-cn.com/problems/missing-number/
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > i) {
                return i;
            }
        }
        return nums[nums.length - 1] + 1;
    }

    /**
     * 异或方法
     * 由于异或运算（XOR）满足结合律，并且对一个数进行两次完全相同的异或运算会得到原来的数，因此我们可以通过异或运算找到缺失的数字。
     *
     * @param nums
     * @return
     */
    public int missingNumber2(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    /**
     * 利用求和公式求出1到n+1的和
     *
     * @param nums
     * @return
     */
    public int missingNumber3(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }
}
