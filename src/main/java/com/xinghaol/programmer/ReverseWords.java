package com.xinghaol.programmer;

import java.util.regex.Pattern;

/**
 * @author: lixinghao
 * @date: 2020/3/10 4:07 下午
 * @Description: 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 */
public class ReverseWords {
    public String reverseWords(String s) {
        if (s.isEmpty()) {
            return "";
        }
        String[] strings = s.split(" ");

        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        for (int i = strings.length - 1; i >= 0; i--) {
            if (!strings[i].isEmpty()) {
                stringBuilder.append(first ? "" : " ").append(strings[i]);
                first = false;
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        reverseWords2("the sky    is blue");
    }

    /**
     * 正则表达式
     *
     * @param s
     * @return
     */
    public static String reverseWords2(String s) {
        String[] split = Pattern.compile("\\s+").matcher(s).replaceAll(" ").split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        for (int i = split.length - 1; i >= 0; i--) {
            if (!split[i].isEmpty()) {
                stringBuilder.append(first ? "" : " ").append(split[i]);
                first = false;
            }
        }
        return stringBuilder.toString();
    }
}
