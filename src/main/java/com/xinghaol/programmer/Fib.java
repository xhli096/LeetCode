package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/9 10:20 下午
 * @Description: 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *  
 *
 * 提示：
 * 0 <= n <= 100
 *
 * 不采用递归。采用循环相加，将数字交换，并每次对1000000007取模运算
 */
public class Fib {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        int sum = 0;
        for (int i = 0; i< n; i++) {
            sum = first + second;
            first = second;
            second = sum%1000000007;
        }

        return first;
    }
}
