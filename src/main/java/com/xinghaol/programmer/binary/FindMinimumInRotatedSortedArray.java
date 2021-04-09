package com.xinghaol.programmer.binary;

/**
 * @author: lixinghao
 * @date: 2021/4/8 12:15 上午
 * @Description: 153. 寻找旋转排序数组中的最小值
 */
public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }

        int left = 0;
        int right = length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 左侧有序
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // 右侧有序
                right = mid;
            }

            left = mid;
        }

        return nums[left];
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray findMinimumInRotatedSortedArray = new FindMinimumInRotatedSortedArray();
        int min = findMinimumInRotatedSortedArray.findMin(new int[] { 4, 5, 6, 7, 0, 1, 2 });
        System.out.println(min);
    }
}
