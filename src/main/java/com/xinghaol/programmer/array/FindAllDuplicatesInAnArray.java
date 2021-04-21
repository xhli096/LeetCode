package com.xinghaol.programmer.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2021/4/18 11:22 下午
 * @Description: 442. 数组中重复的数据
 */
public class FindAllDuplicatesInAnArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();

        Arrays.sort(nums);
        int index = 0;

        for(int i = 0; i < nums.length; i++) {
            if(index == 0 || nums[i] != nums[index - 1]) {
            } else {
                result.add(nums[i]);
            }
            index++;
        }

        return result;
    }

    /**
     * “抽屉原理” + 基于“异或运算”交换两个变量的值
     *  这里由于数组元素限定在数组长度的范围内，因此，我们可以通过一次遍历：
     *
     * 让数值 1 就放在索引位置 0 处；
     * 让数值 2 就放在索引位置 1 处；
     * 让数值 3 就放在索引位置 2 处；
     * ……
     *
     * 一次遍历以后，那些“无处安放”的元素就是我们要找的“出现两次的元素”。
     */
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }

        // 在index的位置，它的值不为 index+1，则进行异或交换。
        for (int i = 0; i < len; i++) {
            while (nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] - 1 != i) {
                res.add(nums[i]);
            }
        }
        return res;
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }
}
