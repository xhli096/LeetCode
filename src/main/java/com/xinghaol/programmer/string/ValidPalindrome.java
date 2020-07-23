package com.xinghaol.programmer.string;

/**
 * @author: lixinghao
 * @date: 2020/5/19 9:03 下午
 * @Description: 680. 验证回文字符串 Ⅱ
 */
public class ValidPalindrome {
    /**
     * 贪心算法
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        int length = s.length();
        int left = 0;
        int right = length - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindromeRange(s, left + 1, right) || isPalindromeRange(s, left, right - 1);
            }
            left++;
            right--;
        }

        return true;
    }

    /**
     * 判断字符串s在范围i到j之间是否是回文串
     *
     * @param s
     * @param i
     * @param j
     * @return
     */
    private boolean isPalindromeRange(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        boolean result = validPalindrome.validPalindrome("aaabaaaa");
        System.out.println(result);
    }
}
