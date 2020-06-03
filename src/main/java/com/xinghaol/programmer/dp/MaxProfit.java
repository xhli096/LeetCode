package com.xinghaol.programmer.dp;

/**
 * @author: lixinghao
 * @date: 2020/3/9 9:37 下午
 * @Description: 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 动态规划 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
 * 思路还是挺清晰的，还是DP思想：
 * 记录【今天之前买入的最小值】
 * 计算【今天之前最小值买入，今天卖出的获利】，也即【今天卖出的最大获利】
 * 比较【每天的最大获利】，取最大值即可
 */
public class MaxProfit {
    /**
     * 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int min = prices[0];
        int max = 0;

        for (int i = 1; i < prices.length; i++) {
            // (当前元素) - (最小值) 与当前的最大值比较
            max = Math.max(prices[i] - min, max);
            // 记录当前的最小值
            min = Math.min(min, prices[i]);
        }

        return max;
    }


    /**
     * 买卖股票的最佳时机 II
     * 因为并没有限制交易的次数，所以将三维的数组降维为二维的数组
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int length = prices.length;
        if (length == 0) {
            return 0;
        }
        int[][] dp = new int[length + 1][2];
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;

        // 计算每天收益
        int max = 0;
        for (int i = 0; i < length; i++) {
            // 前一天持有未持有，继续按兵不动；前一天持有，果断卖掉
            dp[i + 1][0] = Math.max(dp[i][0], dp[i][1] + prices[i]);
            // 前一天持有，继续搬兵不动；前一天未持有，今天买入
            dp[i + 1][1] = Math.max(dp[i][1], dp[i][0] - prices[i]);

            // 最后只取dp[i][0]的状态，因为dp[i][1]还持有股票，最终不能持有股票
            max = Math.max(max, dp[i + 1][0]);
        }

        // 下面的代码简化了dp数组，仅使用一个变量来进行记录最大的收益值。
        /*
        if (prices.length == 0) return 0;
        int n = prices.length;
        int dp_i0 = 0, dp_i1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int tmp = dp_i0;
            dp_i0 = Math.max(dp_i0, dp_i1 +prices[i]);
            dp_i1 = Math.max(dp_i1, tmp - prices[i]);
        }
        return dp_i0;
         */

        return max;
    }

    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
        int profit = maxProfit.maxProfit3(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
        System.out.println(profit);
    }

    public int maxProfit3(int[] prices) {
        int length = prices.length;
        if (length == 0) {
            return 0;
        }
        int k = 2;
        int[][][] dp = new int[length + 1][k + 1][2];
        dp[0][1][0] = 0;
        dp[0][1][1] = Integer.MIN_VALUE;
        dp[0][2][0] = 0;
        dp[0][2][1] = Integer.MIN_VALUE;
        int max = 0;

        for (int i = 0; i < length; i++) {
            // 当前交易次数为1表示交易的次数为1；注意这里记买入为交易，卖出为不交易.
            // 所以买入操作时，需要dp[i][k-1][0/1]。这里需要注意理解
            dp[i + 1][1][0] = Math.max(dp[i][1][0], dp[i][1][1] + prices[i]);
            dp[i + 1][1][1] = Math.max(dp[i][1][1], dp[i][0][0] - prices[i]);
            dp[i + 1][2][0] = Math.max(dp[i][2][0], dp[i][2][1] + prices[i]);
            dp[i + 1][2][1] = Math.max(dp[i][2][1], dp[i][1][0] - prices[i]);

            max = Math.max(dp[i + 1][2][0], max);
        }

        return max;
    }
}
