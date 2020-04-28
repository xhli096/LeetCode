package com.xinghaol.programmer.binary;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author: lixinghao
 * @date: 2020/4/25 11:03 下午
 * @Description: 153. 寻找旋转排序数组中的最小值
 */
public class FindMin {
    public int findMin(int[] nums) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 说明mid的左面区间是有序的，最小值一定在右面区间内
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // nums[mid] < nums[right] 最小值在左面区间中
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        FindMin findMin = new FindMin();
        int min = findMin.findMin(new int[]{4, 5, 6, 7, 0, 1, 2});
        System.out.println(min);
    }
}
