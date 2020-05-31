package com.xinghaol.programmer.slidingWindow;

/**
 * @author: lixinghao
 * @date: 2020/5/23 10:18 下午
 * @Description:
 */
public class MinWindow {
    public String minWindow(String s, String t) {
        int lengthS = s.length();
        int lengthT = t.length();
        if (lengthT == 0) {
            return "";
        }
        char[] charArrayT = t.toCharArray();
        char[] charArrayS = s.toCharArray();

        // ascii('z') = 122,但是一般声明为2的n次幂
        int[] countValue = new int[128];
        int[] tCountValue = new int[128];
        for (char c : charArrayT) {
            tCountValue[c]++;
        }

        int left = 0;
        int right = 0;
        // 表示现在子串中已经出现t中的字符的个数。
        int distance = 0;
        int minIndex = lengthS + 1;
        int begin = 0;

        // 滑动窗口，先滑动右边界，直到当前区间内已经存在了t串中的所有字符。
        // 此时已经无需判断当前以左边界为起始的子串，其他满足条件的子串的长度一定比当前的长。
        // 所以就可以移动left，将一些不需要的字符排除在子区间里面。
        while (right < lengthS) {
            // 如果当前right
            if (tCountValue[charArrayS[right]] == 0) {
                right++;
                continue;
            }
            if (countValue[charArrayS[right]] < tCountValue[charArrayS[right]]) {
                distance++;
            }
            countValue[charArrayS[right]]++;
            right++;

            // 当distance == lengthT时，可以逐渐移动left，将不是t串中的字符排除出去，缩短串的长度。
            while (distance == lengthT) {
                if (right - left < minIndex) {
                    minIndex = right - left;
                    begin = left;
                }
                if (tCountValue[charArrayS[left]] == 0) {
                    left++;
                    continue;
                }
                if (countValue[charArrayS[left]] == tCountValue[charArrayS[left]]) {
                    distance--;
                }
                // 左边界的字符频度--，左边界向右移动
                countValue[charArrayS[left]]--;
                left++;
            }
        }

        if (minIndex == lengthS + 1) {
            return "";
        } else {
            return s.substring(begin, begin + minIndex);
        }
    }
}
