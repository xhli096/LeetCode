package com.xinghaol.programmer.array;

/**
 * @author: lixinghao
 * @date: 2020/6/17 11:45 下午
 * @Description: 1014. 最佳观光组合
 * https://leetcode-cn.com/problems/best-sightseeing-pair/
 */
public class MaxScoreSightseeingPair {
    /**
     * 暴力解法，超出时间限制
     *
     * @param A
     * @return
     */
    public int maxScoreSightseeingPair(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int length = A.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                int current = A[j] + A[i] + i - j;
                max = Math.max(current, max);
            }
        }

        return max;
    }

    /**
     * 根据公式res = A[i] + A[j] + i - j （i < j），我们求每个景点j的时候，他的A[j] - j 实际上是固定的，要想让res最大，我们就要想办法让A[i] + i尽可能大
     *
     * @param A
     * @return
     */
    public int maxScoreSightseeingPair2(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int current = A[0] + 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < A.length; i++) {
            // 处理每一项时，A[j]-j都是确定的，只需要保证A[i]+i最大即可
            max = Math.max(max, A[i] - i + current);
            current = Math.max(current, A[i] + i);
        }

        return max;
    }
}
