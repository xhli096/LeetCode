package com.xinghaol.programmer.dp;

/**
 * @author: lixinghao
 * @date: 2020/7/5 6:41 下午
 * @Description: 44. 通配符匹配
 * https://leetcode-cn.com/problems/wildcard-matching/
 */
public class IsMatch {
    public boolean isMatch(String s, String p) {
        int lengthS = s.length();
        int lengthP = p.length();
        boolean[][] dp = new boolean[lengthP + 1][lengthS + 1];
        // 表示空串是匹配的
        dp[0][0] = true;

        // 遍历p串，处理一下以*开头的串P，*是可以匹配空串的
        for (int i = 1; i <= lengthP; i++) {
            if (p.charAt(i - 1) != '*') {
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1; i <= lengthP; i++) {
            for (int j = 1; j <= lengthS; j++) {
                // 一下这种情况可以由dp[i-1][j-1]来转移过来，前面的都是匹配的
                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] | dp[i][j - 1];
                }
            }
        }

        return dp[lengthP][lengthS];
    }
}
