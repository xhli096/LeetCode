package com.xinghaol.programmer.array;

import com.alibaba.fastjson.JSON;

/**
 * @author: lixinghao
 * @date: 2021/4/4 10:55 下午
 * @Description: 316/1081 ： 不同字符的最小子序列
 */
public class RemoveDuplicateLetters {
    public String smallestSubsequence(String s) {
        int[] alpha = new int[26];
        int length = s.length();
        for(int i = 0; i < length; i++) {
            if(alpha[s.charAt(i) - 'a'] == 0) {
                alpha[s.charAt(i) - 'a'] = 1;
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 26; i++) {
            if(alpha[i] == 1) {
                sb.append((char)(i+97));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters removeDuplicateLetters = new RemoveDuplicateLetters();
        String bcabc = removeDuplicateLetters.smallestSubsequence("bcabc");

        System.out.println(bcabc);

    }
}
