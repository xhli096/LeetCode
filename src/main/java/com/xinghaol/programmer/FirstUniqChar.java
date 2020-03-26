package com.xinghaol.programmer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/3/25 7:09 下午
 * @Description: 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。
 * <p>
 * 示例:
 * s = "abaccdeff"
 * 返回 "b"
 * <p>
 * s = ""
 * 返回 " "
 *  
 * 限制：
 * 0 <= s 的长度 <= 50000
 */
public class FirstUniqChar {
    public char firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (map.get(c) == null) {
                map.put(c, 1);
            } else {
                int count = map.get(c);
                map.put(c, ++count);
            }
        }

        for (Character c : map.keySet()) {
            if (map.get(c) == 1) {
                return c;
            }
        }
        return ' ';
    }

    /**
     * 要比hashmap快一些
     *
     * @param s
     * @return
     */
    public char firstUniqChar2(String s) {
        char[] chars = new char[26];
        char[] chars1 = s.toCharArray();
        for (char c : chars1) {
            chars[c - 'a']++;
        }
        for (char c : chars1) {
            if (chars[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }
}
