package com.xinghaol.programmer.binary;

/**
 * @author: lixinghao
 * @date: 2020/4/20 11:19 下午
 * @Description: 搜索插入位置
 */
public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int length = nums.length;
        if (nums.length <= 0) {
            return 0;
        }

        // 特殊边界判断
        if (nums[length - 1] < target) {
            return length;
        }

        int left = 0;
        int right = length - 1;

        while (left < right) {
            // 使用右移代替除法
            int mid = (left + right) >>> 1;
            // 因为我们要找等于或者第一个大于target的位置，所以小于target的值的位置，一定不是解
            // 因此left = mid + 1
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // 退出循环后，一定有left == right
        return left;
    }
}
