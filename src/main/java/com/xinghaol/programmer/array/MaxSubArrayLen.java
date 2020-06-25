package com.xinghaol.programmer.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/6/23 10:01 下午
 * @Description: 325. 和等于 k 的最长子数组长度
 * https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k/
 */
public class MaxSubArrayLen {
    /**
     * 前缀和解法，时间复杂度O(n^2)
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLen(int[] nums, int k) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        // 计算前缀和
        int[] value = new int[length + 1];
        value[0] = 0;
        for (int i = 0; i < length; i++) {
            value[i + 1] = value[i] + nums[i];
        }
        int max = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length + 1; j++) {
                if (value[j] - value[i] == k) {
                    max = Math.max(j - i, max);
                }
            }
        }

        return max;
    }

    /**
     * 前缀和 + hash
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLen2(int[] nums, int k) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int max = 0;

        // key：值，value：下标
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int currentSum = 0;
        for (int i = 0; i < length; i++) {
            currentSum += nums[i];
            // 是否存在之前的值了
            if (map.containsKey(currentSum - k)) {
                int index = i - map.get(currentSum - k);
                max = Math.max(max, index);
            }
            if (!map.containsKey(currentSum)) {
                map.put(currentSum, i);
            }
        }

        return max;
    }
}
