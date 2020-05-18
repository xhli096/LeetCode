package com.xinghaol.programmer.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/5/18 11:30 下午
 * @Description: 217. 存在重复元素
 * https://leetcode-cn.com/problems/contains-duplicate/
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }

        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
