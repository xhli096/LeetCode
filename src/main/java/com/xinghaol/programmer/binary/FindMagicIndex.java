package com.xinghaol.programmer.binary;

/**
 * @author: lixinghao
 * @date: 2020/5/12 2:23 下午
 * @Description: 面试题 08.03. 魔术索引
 * https://leetcode-cn.com/problems/magic-index-lcci/
 */
public class FindMagicIndex {
    private int index = Integer.MAX_VALUE;

    public int findMagicIndex(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        int left = 0;
        int right = length - 1;

        findMinIndex(nums, left, right);
        if (index == Integer.MAX_VALUE) {
            return -1;
        } else {
            return index;
        }
    }

    private void findMinIndex(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        int mid = left + (right - left) / 2;
        System.out.println(nums[mid] + "  " + index);
        if (nums[mid] == mid && mid < index) {
            index = mid;
        }
        findMinIndex(nums, left, mid - 1);
        findMinIndex(nums, mid + 1, right);
    }

    public static void main(String[] args) {
        FindMagicIndex findMagicIndex = new FindMagicIndex();
        int magicIndex = findMagicIndex.findMagicIndex(new int[]{0, 0, 2});
        System.out.println(magicIndex);
    }
}
