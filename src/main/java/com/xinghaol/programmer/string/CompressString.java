package com.xinghaol.programmer.string;

/**
 * @author: lixinghao
 * @date: 2020/3/16 10:02 上午
 * @Description: 字符串压缩
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。
 * 若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 * <p>
 * 示例1:
 * <p>
 * 输入："aabcccccaaa"
 * 输出："a2b1c5a3"
 * 示例2:
 * <p>
 * 输入："abbccd"
 * 输出："abbccd"
 * 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
 * 提示：
 * <p>
 * 字符串长度在[0, 50000]范围内。
 * <p>
 * 比较当前元素与前一个元素是否相同，不相同则重新计数。
 */
public class CompressString {
    public static void main(String[] args) {
        compressString("");
    }

    public static String compressString(String s) {
        if ("".equals(s)) {
            return "";
        }

        char[] charArray = s.toCharArray();
        char[] array = new char[charArray.length];
        int[] frequency = new int[charArray.length];

        char c = charArray[0];
        int index = 0;
        int currentFrequency = 1;
        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] != c) {
                array[index] = c;
                frequency[index] = currentFrequency;
                index++;
                c = charArray[i];
                currentFrequency = 1;
            } else {
                currentFrequency++;
            }
        }
        array[index] = c;
        frequency[index] = currentFrequency;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] == 0) {
                break;
            }
            stringBuilder.append(array[i]).append(frequency[i]);
        }
        if (stringBuilder.toString().length() >= s.length()) {
            return s;
        } else {
            return stringBuilder.toString();
        }
    }
}
