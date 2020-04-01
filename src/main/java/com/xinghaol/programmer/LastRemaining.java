package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/30 10:48 下午
 * @Description: 圆圈中最后剩下的数字
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * <p>
 * 示例 1：
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 * <p>
 * 输入: n = 10, m = 17
 * 输出: 2
 * <p>
 * 限制：
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 */
public class LastRemaining {
    public static void main(String[] args) {
        System.out.println(lastValue(5,
                3));
    }

    /**
     * 约瑟夫环
     *
     * @param n
     * @param m
     * @return
     */
    public static int lastRemaining(int n, int m) {
        boolean[] flag = new boolean[n];

        int count = n;

        int i = 0;
        int index = 0;
        while (true) {
            if (!flag[i]) {
                index++;
                if (index == m) {
                    flag[i] = true;
                    index = 0;
                    count--;
                }

                if (count == 1) {
                    break;
                }
            }
            if (i == (flag.length - 1)) {
                i = 0;
            } else {
                i++;
            }
        }

        for (int j = 0; j < flag.length; j++) {
            if (!flag[j]) {
                count = j;
            }
        }

        return count;
    }

    public static int lastValue(int n, int m) {
        int f = 0;
        for (int i = 2; i <= n; ++i) {
            f = (m + f) % i;
            System.out.println("f: " + f);
        }
        return f;
    }
}
