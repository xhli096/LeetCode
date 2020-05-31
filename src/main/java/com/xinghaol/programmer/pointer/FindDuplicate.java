package com.xinghaol.programmer.pointer;

/**
 * @author: lixinghao
 * @date: 2020/5/26 7:14 下午
 * @Description: 287. 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 * <p>
 * 二分查找 & 快慢指针
 */
public class FindDuplicate {
    /**
     * 二分查找法
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int length = nums.length;
        int right = length - 1;
        int left = 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int i = 0; i < length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
