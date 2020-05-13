package com.xinghaol.programmer.dp;

/**
 * @author: lixinghao
 * @date: 2020/5/13 11:56 上午
 * @Description: 70. 爬楼梯
 * https://leetcode-cn.com/problems/climbing-stairs/
 */
public class ClimbStairs {
    /**
     * 斐波那契数列
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int pre = 1;
        int last = 2;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = pre + last;
            pre = last;
            last = sum;
        }

        return sum;
    }

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        int result = climbStairs.climbStairs(4);
        System.out.println(result);
    }
}
