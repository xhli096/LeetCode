package com.xinghaol.programmer.dp;

/**
 * @author: lixinghao
 * @date: 2020/5/8 6:54 下午
 * @Description: 221. 最大正方形
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 * <p>
 * 题解：https://leetcode-cn.com/problems/maximal-square/solution/li-jie-san-zhe-qu-zui-xiao-1-by-lzhlyle/
 */
public class MaximalSquare {
    /**
     * 正方形的大小，限制于上方、左上和左边0的限制。只要出现0，则就要缩小。
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows <= 0) {
            return 0;
        }
        int cols = matrix[0].length;

        int max = 0;
        int[][] dp = new int[rows + 1][cols + 1];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]), dp[i][j]) + 1;
                    max = Math.max(max, dp[i + 1][j + 1]);
                }
            }
        }

        return max * max;
    }

    public static void main(String[] args) {
        MaximalSquare maximalSquare = new MaximalSquare();
        int max = maximalSquare.maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '0', '1', '1'},
                {'1', '0', '0', '1', '0'}
        });
        System.out.println(max);
    }
}
