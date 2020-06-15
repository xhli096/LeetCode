package com.xinghaol.programmer.string;

/**
 * @author: lixinghao
 * @date: 2020/6/9 10:59 下午
 * @Description: 7. 整数反转
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class ReverseNumber {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            // 对溢出int的范围做判断
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        ReverseNumber reverseNumber = new ReverseNumber();
        int reverse = reverseNumber.reverse(1534236469);
        System.out.println(reverse);
    }
}
