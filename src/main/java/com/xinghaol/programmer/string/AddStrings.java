package com.xinghaol.programmer.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/5/28 11:45 上午
 * @Description:
 */
public class AddStrings {
    /**
     * 计算每一位，记录每一位的进位
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int length1 = num1.length();
        int length2 = num2.length();

        int right1 = length1 - 1;
        int right2 = length2 - 1;

        List<String> list = new ArrayList<>();
        int carry = 0;
        while (right1 >= 0 && right2 >= 0) {
            int value = Integer.parseInt(num1.substring(right1, right1 + 1)) + Integer.parseInt(num2.substring(right2, right2 + 1)) + carry;
            carry = value / 10;
            value = value % 10;
            list.add(String.valueOf(value));
            right1--;
            right2--;
        }
        while (right1 >= 0) {
            int value = Integer.parseInt(num1.substring(right1, right1 + 1)) + carry;
            carry = value / 10;
            value = value % 10;

            list.add(String.valueOf(value));
            right1--;
        }
        while (right2 >= 0) {
            int value = Integer.parseInt(num2.substring(right2, right2 + 1)) + carry;
            carry = value / 10;
            value = value % 10;

            list.add(String.valueOf(value));
            right2--;
        }
        if (carry > 0) {
            list.add(String.valueOf(carry));
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            stringBuilder.append(list.get(i));
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        AddStrings addStrings = new AddStrings();
        String result = addStrings.addStrings("408", "5");
        System.out.println(result);
    }

    public String addStrings2(String num1, String num2) {
        int len = Math.max(num1.length(), num2.length());
        char[] c1 = new char[len + 1];
        char[] c2 = new char[len + 1];
        for (int i = 0; i < len + 1; i++) {
            c1[i] = '0';
            c2[i] = '0';
        }
        for (int i = num1.length() - 1; i >= 0; i--) {
            c1[num1.length() - i - 1] = num1.charAt(i);
        }
        for (int i = num2.length() - 1; i >= 0; i--) {
            c2[num2.length() - i - 1] = num2.charAt(i);
        }
        int h = 0;
        for (int i = 0; i < len; i++) {
            int t = h + (c1[i] - '0') + (c2[i] - '0');
            int a = t % 10;
            h = t / 10;
            c1[i] = (char) ('0' + a);
        }
        if (h != 0) {
            c1[len] = '1';
            len++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            sb.append(c1[i]);
        }
        return sb.toString();
    }
}
