package com.xinghaol.programmer.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/5/27 5:19 下午
 * @Description: 567. 字符串的排列
 * https://leetcode-cn.com/problems/permutation-in-string/
 * 滑动窗口
 */
public class CheckInclusion {
    /**
     * 滑动窗口
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < length1; i++) {
            sMap.put(s1.charAt(i), sMap.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int left = 0;
        int right = 0;
        int distance = 0;

        while (right < length2) {
            char c = s2.charAt(right);
            right++;

            if (sMap.get(c) != null && sMap.get(c) > 0) {
                tMap.put(c, tMap.getOrDefault(c, 0) + 1);
                // 如果已经相等，distance++；
                if (tMap.get(c).equals(sMap.get(c))) {
                    distance++;
                }
            }

            // 判断左侧窗口是否需要收缩
            if (right - left >= length1) {
                // 在这里判断是否有合法的子串
                if (distance == sMap.size()) {
                    return true;
                }
                char cc = s2.charAt(left);
                left++;
                if (sMap.get(c) != null && sMap.get(cc) > 0) {
                    if (tMap.get(cc) == sMap.get(cc)) {
                        distance--;
                    }
                    if (tMap.get(cc) > 0) {
                        tMap.put(cc, tMap.get(cc) - 1);
                    }
                }
            }
        }
        // 退出循环后就是没找到
        return false;
    }

    public static void main(String[] args) {
        CheckInclusion checkInclusion = new CheckInclusion();
        boolean inclusion = checkInclusion.checkInclusion("abc", "dcda");
        System.out.println(inclusion);
    }
}
