package com.xinghaol.programmer.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: lixinghao
 * @date: 2020/6/22 3:23 下午
 * @Description: 面试题 16.18. 模式匹配
 * https://leetcode-cn.com/problems/pattern-matching-lcci/
 */
public class PatternMatching {
    /**
     * 正则表达式
     * (.*?)用于匹配
     *
     * @param pattern
     * @param value
     * @return
     */
    public boolean patternMatching(String pattern, String value) {
        StringBuilder stringBuilder = new StringBuilder("^");
        int indexA = 0;
        int indexB = 0;
        boolean aFlag = false;
        boolean bFlag = false;

        for (char c : pattern.toCharArray()) {
            if (c == 'a') {
                if (!aFlag) {
                    stringBuilder.append("(.*?)");
                    aFlag = true;
                    if (indexA == 0) {
                        if (indexB == 0) {
                            indexA = 1;
                        } else {
                            indexA = 2;
                        }
                    }
                } else {
                    stringBuilder.append("\\").append(indexA);
                }
            }
            if (c == 'b') {
                if (!bFlag) {
                    stringBuilder.append("(.*?)");
                    bFlag = true;
                    if (indexB == 0) {
                        if (indexA == 0) {
                            indexB = 1;
                        } else {
                            indexB = 2;
                        }
                    }
                } else {
                    stringBuilder.append("\\").append(indexB);
                }
            }
        }
        stringBuilder.append("$");
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(stringBuilder.toString());
        Matcher matcher = r.matcher(value);
        if (matcher.find()) {
            int count = matcher.groupCount();
            if (count >= 2 && matcher.group(1).equals(matcher.group(2))) {
                return false;
            }
        }

        return Pattern.matches(stringBuilder.toString(), value);
    }

    public static void main(String[] args) {
        PatternMatching patternMatching = new PatternMatching();
        boolean matching = patternMatching.patternMatching("abba", "dogcatcatdog");
        System.out.println(matching);
    }

    public boolean patternMatching2(String pattern, String value) {
        // 先判断一些特殊情况
        if (pattern.equals("a") || pattern.equals("b")) {
            return true;
        }
        if (pattern.length() == 0) {
            return value.length() == 0;
        }
        char[] pChars = pattern.toCharArray();
        char[] vChars = value.toCharArray();
        // value为空时，判断pattern是否只有a或只有b
        if (value.length() == 0) {
            boolean aExist = false;
            boolean bExist = false;
            for (char c : pChars) {
                if (c == 'a') {
                    aExist = true;
                } else {
                    bExist = true;
                }
                if (aExist && bExist) {
                    return false;
                }
            }
            return true;
        }
        // 计算pattern里a和b的个数
        int countA = 0;
        int countB = 0;
        for (char c : pChars) {
            if (c == 'a') {
                countA++;
            } else {
                countB++;
            }
        }
        int lenV = vChars.length;
        // a或b的数量为0，判断value能否被等分
        if (countA * countB == 0) {
            int count = countA + countB;
            if (lenV % count != 0) {
                return false;
            } else {
                int len = lenV / count;
                for (int i = len; i < lenV; i += len) {
                    if (!stringEquals(vChars, 0, i, len)) {
                        return false;
                    }
                }
                return true;
            }
        }
        // i代表a字符串的长度
        for (int i = 0; i <= lenV; i++) {
            // a字符串过长就break
            if (lenV - countA * i < 0) {
                break;
            }
            int lenB = (lenV - countA * i) / countB;
            // lenB满足条件才进行判断
            if (lenB * countB + i * countA == lenV) {
                int index = 0;
                int[] ab = new int[2];
                // 初始化a和b的初始索引之前设置为-1
                ab[0] = -1;
                ab[1] = -1;
                boolean notMatch = false;
                for (char c : pChars) {
                    if (c == 'a') {
                        if (ab[0] == -1) {
                            ab[0] = index;
                        } else {
                            // 每次为a都和初始的字符串比较
                            if (!stringEquals(vChars, ab[0], index, i)) {
                                notMatch = true;
                                break;
                            }
                        }
                        index += i;
                    } else {
                        if (ab[1] == -1) {
                            ab[1] = index;
                        } else {
                            if (!stringEquals(vChars, ab[1], index, lenB)) {
                                notMatch = true;
                                break;
                            }
                        }
                        index += lenB;
                    }
                    // 判断a和b是否相同
                    if (lenB == i) {
                        if (ab[0] != -1 && ab[1] != -1) {
                            if (stringEquals(vChars, ab[0], ab[1], lenB)) {
                                notMatch = true;
                                break;
                            }
                        }
                    }
                }
                // notMatch为false说明之前的几个判断里面都不是因为break跳出
                if (!notMatch) {
                    return true;
                }
            }
        }
        return false;
    }

    // 判断字符串是否相等
    private boolean stringEquals(char[] chars, int i, int j, int len) {
        for (int k = 0; k < len; k++) {
            if (chars[i + k] != chars[j + k]) {
                return false;
            }
        }
        return true;
    }
}
