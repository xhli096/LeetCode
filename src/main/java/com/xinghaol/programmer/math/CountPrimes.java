package com.xinghaol.programmer.math;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/24 12:18 上午
 * @Description: 204. 计数质数
 * https://leetcode-cn.com/problems/count-primes/
 */
public class CountPrimes {
    /**
     * 排除法，从2开始，只需判断到sqrt(n)即可；而他的倍数也一定不是质数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrim[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrim[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) {
                count++;
            }
        }

        return count;
    }
}
