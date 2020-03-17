package com.xinghaol.programmer;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/3/17 4:19 下午
 * @Description: 拼写单词
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * <p>
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * <p>
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 * <p>
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 * <p>
 *  
 * <p>
 * 示例 1：
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 * 示例 2：
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * 所有字符串中都仅包含小写英文字母
 */
public class CountCharacters {
    /**
     * 某个字母可以在不同的单词里面使用，但是每个字母只可以使用一次
     *
     * @param words
     * @param chars
     * @return
     */
    public int countCharacters(String[] words, String chars) {
        int[] charArray = new int[26];
        char[] array = chars.toCharArray();

        for (int i = 0; i < array.length; i++) {
            charArray[array[i] - 'a']++;
        }
        int result = 0;
        int[] tmp = new int[26];
        for (String s : words) {
            Arrays.fill(tmp, 0);
            boolean flag = true;
            for (char c : s.toCharArray()) {
                tmp[c - 'a']++;
                if (tmp[c - 'a'] > charArray[c - 'a']) {
                    flag = false;
                }
            }
            if (flag) {
                result += s.length();
            }
        }

        return result;
    }
}
