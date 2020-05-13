package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/5/11 11:56 上午
 * @Description:
 */
public class MyPow {
    public double myPow(double x, int n) {
        if (x == 0.0) {
            return 0.0;
        }
        if (n == 0) {
            return 1;
        }

        if (n > 0) {
            return pow(x, n);
        } else {
            return 1.0 / pow(x, n);
        }
    }

    /**
     * 快速幂+递归的解法
     * 1、当计算x^n时可以先递归计算出y=x^「n/2」,「a」表示向下取整；
     * 2、递归计算：当为偶数时，x^n = y^2;
     * 当为奇数时，x^n = y ^ 2 * x;多乘以一个x
     * 3、当n=0时，递归结束返回1；
     *
     * @param x
     * @param n
     * @return
     */
    private double pow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double y = pow(x, n / 2);

        return n % 2 == 0 ? y * y : y * y * x;
    }

    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        double v = myPow.myPow(2, -2);
        System.out.println(v);
    }
}
