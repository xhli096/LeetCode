package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/12 11:51 上午
 * @Description: 字符串的最大公因子
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 * <p>
 * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * 示例 2：
 * <p>
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * 示例 3：
 * <p>
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] 和 str2[i] 为大写英文字母
 */
public class GcdOfStrings {
    /*
        如果 str1 和 str2 存在最大公约数 str，那么就相当于 str1 和 str2 都是由 str 组成的，那么 str1 + str2 和 str2 + str1 应该是相等的
        如果不满足，那么不存在最大公约数

        我们可以通过 两个字符串的长度来求得最大公约数的长度
        比如 str1 = "ABABAB", str2 = "ABAB"
            len1 = 6         len2 = 4
            那么最大公约数 str = "AB"
                         len = 2
        */
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        return str2.substring(0, gcd(str1.length(), str2.length()));
    }

    public int gcd(int a, int b) {
        if (a < b) {
            gcd(b, a);
        }
        if (a % b == 0) {
            return b;
        } else {
            // 辗转相除法，那除数与余数进行运算
            return gcd(b, a % b);
        }
    }
}
