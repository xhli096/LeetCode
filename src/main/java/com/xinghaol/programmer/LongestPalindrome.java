package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/19 10:15 下午
 * @Description: 最长回文串
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "abccccdd"
 * <p>
 * 输出:
 * 7
 * <p>
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        int result = 0;
        if (s == null || s.length() <= 0) {
            return 0;
        }
        int[] count = new int[58];
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            count[charArray[i] - 'A']++;
        }
        int max = 0;
        // 在最后的回文数序列中之多有一个字母的个数为奇数。则需要早最大的那个奇数的字母，其余的奇数都需要-1成为偶数
        // 偶数则直接加上就可以了。
        for (int i = 0; i < count.length; i++) {
            if (count[i] % 2 != 0) {
                if (count[i] > max) {
                    if (max > 0) {
                        result += (max - 1);
                    }
                    max = count[i];
                } else {
                    if (count[i] > 1) {
                        result += (count[i] - 1);
                    }
                }
            }
            if (count[i] % 2 == 0) {
                result += count[i];
            }
        }
        result += max;
        return result;
    }
}
