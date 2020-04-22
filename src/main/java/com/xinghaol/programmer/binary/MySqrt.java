package com.xinghaol.programmer.binary;

import org.junit.Test;

/**
 * @author: lixinghao
 * @date: 2020/4/21 11:53 下午
 * @Description: 二分查找-69. x 的平方根
 * https://leetcode-cn.com/problems/sqrtx/
 */
public class MySqrt {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        // 一个数的平方根最多不会超过它的一半
        int left = 0;
        int right = x / 2;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (Math.pow(mid, 2) > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return left;
    }

    @Test
    public void fun() {
        System.out.println(mySqrt(2147483647));
    }
}
