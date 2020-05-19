package com.xinghaol.programmer.string;

/**
 * @author: lixinghao
 * @date: 2020/5/19 10:19 下午
 * @Description: 125. 验证回文串
 * https://leetcode-cn.com/problems/valid-palindrome/
 */
public class IsPalindrome {
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < s.length() && !isValid(s.charAt(left))) {
                left++;
            }
            if (left >= s.length()) {
                return true;
            }
            char cLeft = s.charAt(left);
            while (right >= 0 && !isValid(s.charAt(right))) {
                right--;
            }
            char cRight = s.charAt(right);
            if (Character.toLowerCase(cLeft) == Character.toLowerCase(cRight)) {
                left++;
                right--;
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean isValid(char c) {
        return Character.isDigit(c) || Character.isLetter(c);
    }

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        System.out.println(isPalindrome.isValid(' '));
        boolean palindrome = isPalindrome.isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(palindrome);
    }
}
