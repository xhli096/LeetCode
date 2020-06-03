package com.xinghaol.programmer.math;

/**
 * @author: lixinghao
 * @date: 2020/6/2 2:51 下午
 * @Description:
 */
public class SumNums {
    public int sumNums(int n) {
        return n == 1 ? n : (n + sumNums(n-1));
    }
}
