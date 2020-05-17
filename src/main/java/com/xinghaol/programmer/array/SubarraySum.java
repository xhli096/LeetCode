package com.xinghaol.programmer.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/5/15 3:37 下午
 * @Description: 560. 和为K的子数组
 */
public class SubarraySum {
    /**
     * 使用暴力算法进行查找
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum1(int[] nums, int k) {
        int count = 0;

        /**
         * 在区间中可能会有相互抵消的元素
         */
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 使用普通前缀和的方法来进行
     * <p>
     * 前缀和：从第0项到当前项的总和
     * sum是从第0项到第j项的求和；
     * <p>
     * 从下标为i到j的子数组和为k，则prefixSum[j] - prefixSum[i] = k;
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        int length = nums.length;
        int[] prefixSum = new int[length + 1];
        prefixSum[0] = 0;

        // 计算前缀和数组
        for (int i = 0; i < length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                // 下标从i到j的子数组的和为k
                if (prefixSum[j + 1] - prefixSum[i] == k) {
                    count++;
                }
            }
        }

        return count;
    }

    public int subarraySum3(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;

            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }

            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}
