package com.xinghaol.programmer.dp;

/**
 * @author: lixinghao
 * @date: 2020/6/9 5:00 下午
 * @Description: 面试题46. 把数字翻译成字符串
 * 题解：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/solution/mian-shi-ti-46-ba-shu-zi-fan-yi-cheng-zi-fu-chua-6/
 */
public class TranslateNum {
    /**
     * 最长考虑两位
     * dp[i] = dp[i-1]+dp[i-1]; // i-1 是仅考虑翻译当前的一位，i-2 是考虑翻译当前和当前位置的前一位。
     * dp[0] = 0; dp[1] = 1;
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        if (num < 10) {
            return 1;
        }
        String value = String.valueOf(num);
        int length = value.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= length; i++) {
            String s = value.substring(i - 2, i);
            if (s.compareTo("10") >= 0 && s.compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[length];
    }

    public static void main(String[] args) {
        TranslateNum translateNum = new TranslateNum();
        int result = translateNum.translateNum(12258);
        System.out.println(result);
    }
}
