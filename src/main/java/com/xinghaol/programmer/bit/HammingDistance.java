package com.xinghaol.programmer.bit;

/**
 * @author: lixinghao
 * @date: 2020/9/1 11:35 下午
 * @Description: 461. 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 注意：
 * 0 ≤ x, y < 231.
 * 示例:
 * 输入: x = 1, y = 4
 * 输出: 2
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 */
public class HammingDistance {
    /**
     * 循环1的个数
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int result = x ^ y;
        int count = 0;
        // 100 & 011 => 000
        while (result != 0) {
            count++;
            result &= (result - 1);
        }

        return count;
    }

    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        int count = hammingDistance.hammingDistance(1, 4);
        System.out.println(count);
    }
}
