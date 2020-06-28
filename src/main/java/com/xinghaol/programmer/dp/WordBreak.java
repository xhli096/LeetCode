package com.xinghaol.programmer.dp;

import java.util.HashMap;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/6/25 9:52 下午
 * @Description: 139. 单词拆分
 * https://leetcode-cn.com/problems/word-break/
 */
public class WordBreak {
    /*
        动态规划算法，dp[i]表示s前i个字符能否拆分
        转移方程：dp[j] = dp[i] && check(s[i+1, j]);
        check(s[i+1, j])就是判断i+1到j这一段字符是否能够拆分
        其实，调整遍历顺序，这等价于s[i+1, j]是否是wordDict中的元素
        这个举个例子就很容易理解。
        假如wordDict=["apple", "pen", "code"],s = "applepencode";
        dp[8] = dp[5] + check("pen")
        翻译一下：前八位能否拆分取决于前五位能否拆分，加上五到八位是否属于字典
        （注意：i的顺序是从j-1 -> 0哦~
    */
    public HashMap<String, Boolean> hash = new HashMap<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];

        //方便check，构建一个哈希表
        for (String word : wordDict) {
            hash.put(word, true);
        }
        //初始化
        dp[0] = true;

        //遍历
        for (int j = 1; j <= s.length(); j++) {
            for (int i = j - 1; i >= 0; i--) {
                dp[j] = dp[i] && check(s.substring(i, j));
                if (dp[j]) {
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public boolean check(String s) {
        return hash.getOrDefault(s, false);
    }
}
