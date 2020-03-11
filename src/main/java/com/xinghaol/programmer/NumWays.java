package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/11 3:14 下午
 * @Description: 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：n = 7
 * 输出：21
 * 提示：
 * <p>
 * 0 <= n <= 100
 * <p>
 * 青蛙跳台阶题目本质就是一个斐波那契数列
 */
public class NumWays {
    public int numWays(int n) {
        if (n <= 1) {
            return 1;
        }
        int first = 0;
        int second = 1;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = first + second;
            first = second;
            second = sum % 1000000007;
        }

        return sum % 1000000007;
    }
}
