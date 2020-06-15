package com.xinghaol.programmer.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/5/25 1:13 下午
 * @Description: 14. 最长公共前缀
 * https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
    /**
     * 2ms
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        int length = strs.length;
        if (length == 0) {
            return "";
        }
        List<char[]> strsList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            strsList.add(strs[i].toCharArray());
            min = Math.min(strs[i].toCharArray().length, min);
        }

        int index = 0;
        boolean flag = true;
        for (int i = 0; i < min; i++) {
            char current = strsList.get(0)[i];
            for (char[] array : strsList) {
                if (array[i] != current) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            }
            index++;
        }

        return strs[0].substring(0, index);
    }

    /**
     * 0毫秒解法
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        if (prefix == null || prefix.trim().length() == 0) {
            return "";
        }
        // 如果有公共前缀，则一定会符合indexOf() == 0
        for (int i = 1; i < strs.length; i++) {
            // 如果是以prefix为前缀的，所以indexOf的值一定为0
            while (strs[i].indexOf(prefix) != 0) {
                // 每次缩小前缀串，当前缀串缩小为""时，返回空字符串。
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.length() == 0) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String prefix = longestCommonPrefix.longestCommonPrefix2(new String[]{"flower", "flow", "flight"});
        System.out.println(prefix);
    }
}
