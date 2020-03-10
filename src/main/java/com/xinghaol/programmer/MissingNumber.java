package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/10 4:45 下午
 * @Description: 0～n-1中缺失的数字
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= 数组长度 <= 10000
 * <p>
 * 数字递增，则使用二分法，查找数值与下表不对应的第一个左面的数字，下表即为缺失的元素。
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // 用长度为1的数组，已验证，就是left <= right的循环条件
        int middle = 0;
        while (left <= right) {
            middle = (left + right) / 2;
            if (nums[middle] != middle) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return left;
    }
}
