package com.xinghaol.programmer.greedy;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2021/4/12 10:45 下午
 * @Description:
 */
public class LargestNumber {

    /**
     * 贪心算法
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] ss = new String[n];

        for (int i = 0; i < n; i++) {
            ss[i] = "" + nums[i];
        }

        Arrays.sort(ss, (a, b) -> {
            String ab = a + b;
            String ba = b + a;

            return ba.compareTo(ab);
        });
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : ss) {
            stringBuilder.append(s);
        }

        // 处理前导0
        int len = stringBuilder.length();
        int k = 0;
        while (k < len - 1 && stringBuilder.charAt(k) == '0') {
            k++;
        }

        return stringBuilder.substring(k);
    }

    public static void main(String[] args) {
        LargestNumber largestNumber = new LargestNumber();
        String s = largestNumber.largestNumber(new int[] { 3, 30, 34, 5, 9 });
        System.out.println(s);
    }
}
