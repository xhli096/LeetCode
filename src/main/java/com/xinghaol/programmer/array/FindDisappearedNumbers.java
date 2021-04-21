package com.xinghaol.programmer.array;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @author: lixinghao
 * @date: 2020/9/1 9:52 上午
 * @Description: 448. 找到所有数组中消失的数字
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * 示例:
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [5,6]
 */
public class FindDisappearedNumbers {

    /**
     * 大数据量下会超时
     *
     * @param nums
     *
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        if (nums.length <= 0) {
            return result;
        }
        for (int i = 1; i <= nums.length; i++) {
            result.add(i);
        }
        System.out.println(JSON.toJSONString(result));
        for (int i = 0; i < nums.length; i++) {
            int index = result.indexOf(nums[i]);
            if (index != -1) {
                result.remove(index);
            }
        }

        return result;
    }

    /**
     * 原地修改法，第一次修改以nums[i]为下标的，第二次统计整数
     *
     * @param nums
     *
     * @return
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums.length <= 0) {
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] *= -1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FindDisappearedNumbers findDisappearedNumbers = new FindDisappearedNumbers();
        List<Integer> disappearedNumbers = findDisappearedNumbers.findDisappearedNumbers(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 });
        System.out.println(JSON.toJSONString(disappearedNumbers));
    }

    /**
     * 抽屉法，一次遍历
     *
     * @param nums
     *
     * @return
     */
    public List<Integer> findDisappearedNumbers3(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return new ArrayList<>();
        }
        for (int i = 0; i < length; i++) {
            while (nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (nums[i] - 1 != i) {
                result.add(i + 1);
            }
        }

        return result;
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index2] ^ nums[index1];
        nums[index1] = nums[index1] ^ nums[index2];
    }
}
