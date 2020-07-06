package com.xinghaol.programmer.array;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/28 10:01 下午
 * @Description: 209. 长度最小的子数组
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 * 示例：
 * <p>
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * <p>
 * 进阶：
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
public class MinSubArrayLen {
    /**
     * 滑动窗口思想，当>=s记录当前长度移动左侧区间
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }

        int count = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;
        while (right < length) {
            sum += nums[right++];
            while (sum >= s) {
                count = Math.min(count, right - left);
                sum -= nums[left++];
            }
        }

        return count == Integer.MAX_VALUE ? 0 : count;
    }

    public static void main(String[] args) {
        MinSubArrayLen minSubArrayLen = new MinSubArrayLen();
        int result = minSubArrayLen.minSubArrayLen(11, new int[]{1, 2, 3, 4, 5});
        System.out.println("result:" + result);
    }

    /**
     * 前缀和 + 二分查找
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
