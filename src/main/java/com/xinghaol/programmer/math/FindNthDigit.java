package com.xinghaol.programmer.math;

/**
 * @author: lixinghao
 * @date: 2020/6/26 10:28 下午
 * @Description: 400. 第N个数字
 * https://leetcode-cn.com/problems/nth-digit/
 */
public class FindNthDigit {
    /**
     * 第n个数字，10中算是有两个数字，1和0
     * 123456789 =》 9个数字，9*1位
     * 10 ~ 99 =》 90个数字，90 * 2位
     * 100~999 =》 900个数字，900*3位
     * ....
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        if (n <= 9) {
            return n;
        }
        long base = 1;
        int digit = 1;
        //是因为n>=10的时候前面是一共0-9有10个数，10对应于base第0个数的第0位，如果不减接下去循环就变成base的第0个数第1位
        n--;
        while (n > 9 * base * digit) {
            n -= 9 * base * digit;
            base *= 10;
            digit++;
        }
        return String.valueOf(base + n / digit).charAt(n % digit) - '0';

    }
}
