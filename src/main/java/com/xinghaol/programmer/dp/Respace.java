package com.xinghaol.programmer.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/7/9 6:11 下午
 * @Description: 面试题 17.13. 恢复空格
 * https://leetcode-cn.com/problems/re-space-lcci/
 */
public class Respace {
    /**
     * 假设当前我们已经考虑完了前 i 个字符了，对于前 i + 1 个字符对应的最少未匹配数：
     * <p>
     * 1、第 i + 1 个字符未匹配，则 dp[i + 1] = dp[i] + 1，即不匹配数加 1;
     * 2、遍历前 i 个字符，若以其中某一个下标 idx 为开头、以第 i + 1 个字符为结尾的字符串正好在词典里，
     * 则 dp[i] = min(dp[i], dp[idx]) 更新 dp[i]。
     * 于是，有了解法一
     *
     * @param dictionary
     * @param sentence
     * @return
     */
    public int respace(String[] dictionary, String sentence) {
        int length = sentence.length();
        int[] dp = new int[length + 1];
        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));

        for (int i = 1; i <= sentence.length(); i++) {
            // 首先认定当前下标的第i个字符为匹配，则dp[i+1]=dp[i]+1;
            dp[i] = dp[i - 1] + 1;
            // 遍历 0~i-1；若以其中某一个下标idx为开头，以i为结尾的字符串在词典中，则dp[i]=min(dp[i], dp[index])
            for (int j = 0; j < i; j++) {
                if (dict.contains(sentence.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }

        return dp[length];
    }
}
