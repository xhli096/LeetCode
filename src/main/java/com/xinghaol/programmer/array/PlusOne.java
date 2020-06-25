package com.xinghaol.programmer.array;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/24 12:29 上午
 * @Description: 66. 加一
 * https://leetcode-cn.com/problems/plus-one/
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        if (length == 0) {
            return new int[]{};
        }
        int[] tmp = new int[length + 1];
        int num = digits[length - 1] + 1;
        tmp[length] = num % 10;
        int carry = num / 10;
        int index = length - 2;

        while (index >= 0) {
            num = digits[index] + carry;
            tmp[index + 1] = num % 10;
            carry = num / 10;
            index--;
        }
        if (carry > 0) {
            tmp[0] = carry;
        }
        if (tmp[0] == 0) {
            int[] result = Arrays.copyOfRange(tmp, 1, tmp.length);
            return result;
        } else {
            return tmp;
        }
    }

    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        int[] result = plusOne.plusOne(new int[]{1, 2, 3});
        System.out.println(Arrays.toString(result));
    }
}
