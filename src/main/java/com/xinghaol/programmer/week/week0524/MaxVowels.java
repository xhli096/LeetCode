package com.xinghaol.programmer.week.week0524;

/**
 * @author: lixinghao
 * @date: 2020/5/24 10:47 上午
 * @Description:
 */
public class MaxVowels {
    public int maxVowels(String s, int k) {
        if (k == 0) {
            return 0;
        }
        int length = s.length();
        int maxCount = 0;
        for (int i = 0; i <= length - k; i++) {
            int currentCount = 0;
            int index = 0;
            for (int j = i; j < length && index < k; j++) {
                index++;
                if (s.charAt(j) == 'a' || s.charAt(j) == 'e' || s.charAt(j) == 'i' || s.charAt(j) == 'o' || s.charAt(j) == 'u') {
                    currentCount++;
                }
            }
            maxCount = Math.max(maxCount, currentCount);
        }

        return maxCount;
    }

    public int maxVowels2(String s, int k) {
        if (k == 0) {
            return 0;
        }
        int length = s.length();
        int maxCount = 0;
        int left = 0;
        int right = 0;

        int index = 0;
        int current = 0;
        // 前k个位置
        while (index < k) {
            if (s.charAt(right) == 'a' || s.charAt(right) == 'e' || s.charAt(right) == 'i' || s.charAt(right) == 'o' || s.charAt(right) == 'u') {
                current++;
            }
            right++;
            index++;
        }
        maxCount = Math.max(current, maxCount);

        right = right - 1;
        while (left < length - k) {
            if (s.charAt(left) == 'a' || s.charAt(left) == 'e' || s.charAt(left) == 'i' || s.charAt(left) == 'o' || s.charAt(left) == 'u') {
                current--;
            }
            left++;
            right++;
            if (right < length) {
                if (s.charAt(right) == 'a' || s.charAt(right) == 'e' || s.charAt(right) == 'i' || s.charAt(right) == 'o' || s.charAt(right) == 'u') {
                    current++;
                }
            }
            System.out.println(current);
            maxCount = Math.max(current, maxCount);
        }

        return maxCount;
    }

    boolean ok(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }

    public int maxVowels3(String s, int k) {
        int d = 0;
        int r = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (ok(c)) {
                d++;
            }
            if (i >= k) {
                if (ok(s.charAt(i - k))) {
                    d--;
                }
            }
            if (i >= k - 1) {
                r = Math.max(r, d);
            }

        }
        return r;
    }

    public static void main(String[] args) {
        MaxVowels maxVowels = new MaxVowels();
        int count = maxVowels.maxVowels2("abciiidef",
                3);
        System.out.println(count);
    }
}
