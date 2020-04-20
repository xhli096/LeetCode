package com.xinghaol.programmer.binary;

/**
 * @author: lixinghao
 * @date: 2020/4/19 11:26 下午
 * @Description: 二分查找模板一
 * LeetCode题目参考：https://leetcode-cn.com/problems/binary-search/
 */
public class Solution1 {
    public int search(int[] nums, int target) {
        // 用于特值判断
        int length = nums.length;
        if (length <= 0) {
            return -1;
        }
        int left = 0;
        int right = length - 1;

        while (left <= right) {
            // 为了防止left + right 整形溢出，写成如下形式：left + (right - left) / 2 《=》 (left + right) / 2
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // 下一轮搜索区间：[mid + 1, right]
                left = mid + 1;
            } else {
                // 下一轮搜索区间：[left, mid - 1]
                right = mid - 1;
            }
        }

        return -1;
    }

    public int search(int[] nums, int left, int right, int target) {
        while (left < right) {
            // 选择中位数时，向下取整
            int mid = left + (right - left) / 2;
            if (check(mid)) {
                // 下一轮搜索区间：[mid + 1, right]
                left = mid + 1;
            } else {
                // 下一轮搜索区间：[left, mid]
                right = mid;
            }
        }

        // 当退出循环时，仅有一个位置没有看到。即left = right的位置
        // 视情况，是否需要单独判断 left （或者 right）这个下标的元素是否符合题意
        return -1;
    }

    public int search2(int[] nums, int left, int right, int target) {
        while (left + 1 < right) {
            // 向上取整
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        // 退出循环后，有left + 1 == right；即区间中剩余[left, right]两个元素
        if (nums[left] == target) {
            return left;
        } else if (nums[right] == target) {
            return right;
        }

        return -1;
    }

    private boolean check(int mid) {
        // 一些判断操作
        return true;
    }
}
