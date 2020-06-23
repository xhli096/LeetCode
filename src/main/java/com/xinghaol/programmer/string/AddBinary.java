package com.xinghaol.programmer.string;


/**
 * @author: lixinghao
 * @date: 2020/6/23 3:09 下午
 * @Description: 67. 二进制求和
 * https://leetcode-cn.com/problems/add-binary/
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        int lengthA = a.length();
        int lengthB = b.length();
        int indexA = lengthA - 1;
        int indexB = lengthB - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder();
        while (indexA >= 0 && indexB >= 0) {
            int current = (a.charAt(indexA--) - '0') + (b.charAt(indexB--) - '0') + carry;
            if (current >= 2) {
                carry = current / 2;
                current = current % 2;
            } else {
                carry = 0;
            }
            result.append(current);
        }
        while (indexA >= 0) {
            int current = (a.charAt(indexA--) - '0') + carry;
            if (current >= 2) {
                carry = current / 2;
                current = current % 2;
            } else {
                carry = 0;
            }
            result.append(current);
        }
        while (indexB >= 0) {
            int current = (b.charAt(indexB--) - '0') + carry;
            if (current >= 2) {
                carry = current / 2;
                current = current % 2;
            } else {
                carry = 0;
            }
            result.append(current);
        }
        if (carry != 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        String result = addBinary.addBinary("1010", "1011");
        System.out.println(result);
    }
}
