package com.xinghaol.programmer.binary;

import com.alibaba.fastjson.JSON;

import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/4/22 4:59 下午
 * @Description: 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return new int[]{-1, -1};
        }

        int[] result = new int[]{-1, -1};
        int prePosition = findPrePosition(nums, target);
        if (prePosition == -1) {
            return result;
        }
        int lastPosition = findLastPosition(nums, target);
        if (lastPosition == -1) {
            return result;
        }
        result[0] = prePosition;
        result[1] = lastPosition;

        return result;
    }

    /**
     * 查找范围区间的开始位置
     *
     * @param nums
     * @param target
     * @return
     */
    private int findPrePosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 严格小于一定不是解
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // nums[mid] >= target;
                right = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    private int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            // 严格大于目标值，也一定不是右区间
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                // num[mid] <= target
                left = mid;
            }
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchRange searchRange = new SearchRange();

        System.out.println(JSON.toJSONString(searchRange.searchRange(new int[]{5,7,7,8,8,10}, 8)));
    }
}
