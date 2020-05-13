package com.xinghaol.programmer.pointer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/5/12 7:58 下午
 * @Description: 26. 删除排序数组中的重复项
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicates {
    /**
     * 快慢指针解法
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return length;
        }
        int slow = 1;
        int pre = nums[0];
        int result = length;

        for (int i = 1; i < length; i++) {
            if (nums[i] != pre) {
                nums[slow] = nums[i];
                pre = nums[i];
                slow++;
            } else {
                result--;
            }
        }
        return result;
    }

    public int removeDuplicates2(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return length;
        }

        int pre = nums[0];
        int result = length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(pre);
        for (int i = 1; i < length; i++) {
            if (nums[i] == pre) {
                result--;
            } else {
                pre = nums[i];
                queue.offer(pre);
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            nums[index++] = queue.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();

        int i = removeDuplicates.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
        System.out.println(i);
    }
}
