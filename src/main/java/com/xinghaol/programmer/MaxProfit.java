package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/9 9:37 下午
 * @Description: 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 动态规划 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
 * 思路还是挺清晰的，还是DP思想：
 *      记录【今天之前买入的最小值】
 *      计算【今天之前最小值买入，今天卖出的获利】，也即【今天卖出的最大获利】
 *      比较【每天的最大获利】，取最大值即可
 */
public class MaxProfit {
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
}
