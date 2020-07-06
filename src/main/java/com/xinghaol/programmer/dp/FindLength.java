package com.xinghaol.programmer.dp;

import com.alibaba.fastjson.JSON;

/**
 * @author: lixinghao
 * @date: 2020/7/1 9:41 下午
 * @Description: 718. 最长重复子数组
 * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 */
public class FindLength {
    /**
     * 时间复杂度：O(n*m)
     * 空间复杂度：O(m*n)
     * Dp思路：
     * 1、若A B某一项不同，则公共序列不包含他们dp[i][j] = 0;
     * 2、若A、B某一项相同，则考虑他们之前的序列
     *  2.1、它们之前的一项不同，则无法提供公共长度，dp[i][j] = 0 + 1;
     *  2.2、它们之前的一项相同，则可以提供公共长度，dp[i][j] = dp[i-1][j-1]+1;
     *
     *  双层循环计算所有情况
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        int lengthA = A.length;
        int lengthB = B.length;
        int[][] dp = new int[lengthA + 1][lengthB + 1];
        int max = 0;

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < dp[1].length; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= lengthA; i++) {
            for (int j = 1; j <= lengthB; j++) {
                // A[i-1] == B[j-1]的时候，当前这个才能为1
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                max = Math.max(max, dp[i][j]);
                System.out.println(JSON.toJSONString(dp));
                System.out.println("=======================");
            }
        }

        return max;
    }

    public static void main(String[] args) {
        FindLength findLength = new FindLength();
        int length = findLength.findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});
        System.out.println(length);
    }
}
