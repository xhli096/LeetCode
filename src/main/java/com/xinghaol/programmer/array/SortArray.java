package com.xinghaol.programmer.array;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/3/31 10:10 上午
 * @Description: 排序数组
 * 给你一个整数数组 nums，将该数组升序排列。
 * <p>
 * 示例 1：
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 * <p>
 * 提示：
 * 1 <= nums.length <= 50000
 * -50000 <= nums[i] <= 50000
 * <p>
 * 十二种排序：https://leetcode-cn.com/problems/sort-an-array/solution/shi-er-chong-pai-xu-suan-fa-bao-ni-man-yi-dai-gift/
 */
public class SortArray {
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);

        return nums;
    }

    public int[] sortArray2(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 快速排序
     *
     * @param nums
     * @param left
     * @param right
     */
    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int value = nums[left];
        int l = left;
        int r = right;
        while (l < r) {
            while (l < r && nums[r] > value) {
                r--;
            }
            if (l < r) {
                nums[l++] = nums[r];
            }
            while (l < r && nums[l] < value) {
                l++;
            }
            if (l < r) {
                nums[r--] = nums[l];
            }
        }

        nums[l] = value;

        quickSort(nums, left, l - 1);
        quickSort(nums, l + 1, right);
    }

    /**
     * 选择排序
     */
    public void selectSort(int[] nums) {
        int minIndex;
        int j;
        for (int i = 0; i < nums.length - 1; i++) {
            for (minIndex = j = i; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int tmp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = tmp;
            }
        }
    }

    /**
     * 插入排序
     */
    public void insertSort(int[] nums) {

    }
}
