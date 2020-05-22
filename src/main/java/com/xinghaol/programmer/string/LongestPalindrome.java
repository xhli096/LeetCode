package com.xinghaol.programmer.string;

/**
 * @author: lixinghao
 * @date: 2020/5/19 10:51 下午
 * @Description: 5.最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class LongestPalindrome {
    /**
     * 扩展中心法
     * 从每一个位置出发向两边扩散，遇到不是回文的时候结束。
     * 每个位置向两边扩散都会出现一个窗口大小-len，如果len > maxLen (用来表示最长回文串的长度)，则更新maxLen的值。
     * 因为最后还需要具体的子串，而不是长度，所以还需要记录maxLen的起始位置startIndex
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        if (s == null || length == 0) {
            return "";
        }

        int left = 0;
        int right = 0;
        int len = 1;
        int maxStartIndex = 0;
        int maxLen = 0;

        for (int i = 0; i < length; i++) {
            left = i - 1;
            right = i + 1;
            // 从当前中心向左侧扩展，判断是否left位置的字符与当前位置的字符相同
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                left--;
                len++;
            }
            // 从当前中心向右侧扩展，判断是否right位置的字符与当前位置的字符相同
            while (right < length && s.charAt(right) == s.charAt(i)) {
                right++;
                len++;
            }
            // 上面两个循环处理掉了与当前位置字符相同的字符，然后处理left与right位置上字符相同的情况
            while (left >= 0 && right < length && s.charAt(right) == s.charAt(left)) {
                left--;
                right++;
                len = len + 2;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStartIndex = left;
            }
            len = 1;
        }

        return s.substring(maxStartIndex + 1, maxStartIndex + maxLen + 1);
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String babad = longestPalindrome.longestPalindrome("babad");
        System.out.println("结果为：" + babad);
    }
}
