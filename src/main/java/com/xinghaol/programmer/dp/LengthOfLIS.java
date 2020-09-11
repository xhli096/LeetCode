package com.xinghaol.programmer.dp;

/**
 * @author: lixinghao
 * @date: 2020/3/14 8:58 下午
 * @Description: 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class LengthOfLIS {
    /**
     * 方法一：动态规划
     * 思路与算法
     *
     * 定义 dp[i]dp[i] 为考虑前 ii 个元素，以第 ii 个数字结尾的最长上升子序列的长度，注意 \textit{nums}[i]nums[i] 必须被选取。
     *
     * 我们从小到大计算 dp[]dp[] 数组的值，在计算 dp[i]dp[i] 之前，我们已经计算出 dp[0 \ldots i-1]dp[0…i−1] 的值，则状态转移方程为：
     *
     * dp[i] = \text{max}(dp[j]) + 1, \text{其中} \, 0 \leq j < i \, \text{且} \, \textit{num}[j]<\textit{num}[i]
     * dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     *
     * 即考虑往 dp[0 \ldots i-1]dp[0…i−1] 中最长的上升子序列后面再加一个 \textit{nums}[i]nums[i]。由于 dp[j]dp[j] 代表 \textit{nums}[0 \ldots j]nums[0…j] 中以 \textit{nums}[j]nums[j] 结尾的最长上升子序列，所以如果能从 dp[j]dp[j] 这个状态转移过来，那么 \textit{nums}[i]nums[i] 必然要大于 \textit{nums}[j]nums[j]，才能将 \textit{nums}[i]nums[i] 放在 \textit{nums}[j]nums[j] 后面以形成更长的上升子序列。
     *
     * 最后，整个数组的最长上升子序列即所有 dp[i]dp[i] 中的最大值。
     *
     * \text{LIS}_{\textit{length}}= \text{max}(dp[i]), \text{其中} \, 0\leq i < n
     * LIS
     * length
     * ​
     *  =max(dp[i]),其中0≤i<n
     *
     * 时间复杂度：O(n^2)，其中 n 为数组nums的长度。动态规划的状态数为 n，计算状态 dp[i] 时，需要O(n) 的时间遍历，
     * dp[0…i−1] 的所有状态，所以总时间复杂度为O(n^2)。
     * 空间复杂度：O(n)，需要额外使用长度为 n 的 dp 数组。
     *
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 手工过程
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        Integer a = null;
        int b = a + 1;
        System.out.println(b);
    }
}
