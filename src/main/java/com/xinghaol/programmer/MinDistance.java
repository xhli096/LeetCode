package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/4/6 9:44 下午
 * @Description: 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 题解：https://leetcode-cn.com/problems/edit-distance/solution/jian-dan-dpmiao-dong-by-sweetiee/
 */
public class MinDistance {
    /**
     * 状态定义：
     * dp[i][j]表示word1的前i个字母转换成word2的前j个字母所使用的最少操作。
     * <p>
     * 状态转移：
     * i指向word1，j指向word2;
     * 若当前字母相同，则dp[i][j] = dp[i - 1][j - 1];
     * 否则取增删替三个操作的最小值 + 1， 即: dp[i][j] = min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]) + 1
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int lenght1 = word1.length();
        int lenght2 = word2.length();
        int[][] dp = new int[lenght1 + 1][lenght2 + 1];

        // 横纵坐标位置下标为0的位置均不存储
        for (int i = 0; i <= lenght1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= lenght2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= lenght1; i++) {
            for (int j = 1; j <= lenght2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                }
            }
        }

        return dp[lenght1][lenght2];
    }
}
