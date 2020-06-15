package com.xinghaol.programmer.math;

/**
 * @author: lixinghao
 * @date: 2020/6/12 11:12 下午
 * @Description: 470. 用 Rand7() 实现 Rand10()
 */
public class Rand10 {
    /**
     * 拒绝采样
     * 结论：rand_n() 等概率生成[1,N] => (rand_x() - 1) * Y + rand_Y() 等概率生成 [1, x*Y]的随机数
     * 如果rand_n()中的n是2的倍数，都可以用来实现rand2()；N不是2的倍数，则产生的结果都不是等概率的。
     * <p>
     * rand()7 =》 rand10() 拒绝采样，不在范围内的丢弃掉。
     * (rand7() - 1) * 7 + rand7() => [1~49]之间的随机数，需要10的倍数，丢失41~49九个数字
     *
     * @return
     */
    public int rand10() {
        while (true) {
            int a = rand7();
            int b = rand7();

            // 生成1~49的随机数
            int num = (a - 1) * 7 + b;
            // 需要10的倍数
            if (num <= 40) {
                return num % 10 + 1;
            }
            // a的范围在1~9之间
            a = num - 40;
            b = rand7();
            // a-1 => 0~8  (a-1) * 8 => 0 ~56 +7 => 1 ~ 63
            // rand63()
            num = (a - 1) * 7 + b;
            if (num <= 60) {
                return num % 10 + 1;
            }
            a = num - 60;
            b = rand7();
            // rand21();
            num = (a - 1) * 7 + b;
            if (num <= 20) {
                return num % 10 + 1;
            }
            // num - 20 => 1;也干不了啥了
        }
    }

    public int rand7() {
        return 0;
    }
}
