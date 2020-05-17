package com.xinghaol.programmer.array;

/**
 * @author: lixinghao
 * @date: 2020/5/17 7:28 下午
 * @Description: 283. 移动零
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int index = 0;

        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }

        while (index < length) {
            nums[index++] = 0;
        }
    }
}
