package com.xinghaol.programmer.string;

/**
 * @author: lixinghao
 * @date: 2020/5/27 10:14 下午
 * @Description:
 */
public class Multiply {
    /**
     * 该算法是通过两数相乘时，乘数某位与被乘数某位相乘，与产生结果的位置的规律来完成。具体规律如下：
     * <p>
     * 乘数 num1 位数为 M，被乘数 num2 位数为 N， num1 x num2 结果 res 最大总位数为 M+N
     * num1[i] x num2[j] 的结果为 tmp(位数为两位，"0x","xy"的形式)，其第一位位于 res[i+j]，第二位位于 res[i+j+1]。
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if ((num1.equals("0")) || (num2.equals("0"))) {
            return "0";
        }
        int length1 = num1.length();
        int length2 = num2.length();
        int[] result = new int[length1 + length2];
        for (int i = length1 - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = length2 - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (result[i + j + 1] + n1 * n2);
                // 本位
                result[i + j + 1] = sum % 10;
                // 进位
                result[i + j] += sum / 10;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            // 排除高位没有进位，为0的情况
            if (i == 0 && result[i] == 0) {
                continue;
            }
            stringBuilder.append(result[i]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Multiply multiply = new Multiply();
        String result = multiply.multiply("123", "45");
        System.out.println(result);
    }

    public String multiply2(String a, String b) {
        char[] aCs = a.toCharArray();
        char[] bCs = b.toCharArray();
        int[] re = new int[aCs.length + bCs.length];
        char[] res = new char[re.length];
        for (int i = 0; i < bCs.length; i++) {
            for (int j = 0; j < aCs.length; j++) {
                re[i + j + 1] += (bCs[i] - '0') * (aCs[j] - '0');
            }
        }

        for (int i = re.length - 1; i >= 0; i--) {
            res[i] = (char) ('0' + (re[i] % 10));
            if (i > 0) {
                re[i - 1] += re[i] / 10;
            }
        }
        int i = 0;
        while (i < res.length - 1 && res[i] == '0') {
            res[i++] = 0;
        }
        return String.valueOf(res).trim();
    }
}
