package com.xinghaol.programmer.string;

/**
 * @author: lixinghao
 * @date: 2020/5/16 10:19 下午
 * @Description: 28. 实现 strStr()
 * https://leetcode-cn.com/problems/implement-strstr/
 */
public class StrStr {

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * 朴素写法
     * 在原串haystack的每一个字符作为「出发点」，每次从原串「出发点」和匹配串中的「首位」开始比较；
     * 匹配成功：返回本次匹配的原串「发起点」。匹配失败：枚举原串的下一个「发起点」，重新尝试匹配。
     * 时间复杂度O((n-m)*m)，空间复杂度O(1)。
     *
     * @param haystack
     * @param needle
     *
     * @return
     */
    public int strStr2(String haystack, String needle) {
        int length1 = haystack.length();
        int length2 = needle.length();
        char[] aa = haystack.toCharArray();
        char[] bb = needle.toCharArray();

        // 每一个原串中的位置均作为出发点
        for (int i = 0; i <= length1 - length2; i++) {
            int index1 = i;
            int index2 = 0;
            while (index2 < length2 && aa[index1] == bb[index2]) {
                index1++;
                index2++;
            }
            if (index2 == length2) {
                return i;
            }
        }

        // 到这里，说明并没有匹配到
        return -1;
    }

    /**
     * KMP 解法
     * KMP 算法是一个快速查找匹配串的算法，它的作用其实就是本题问题：如何快速在「原字符串」中找到「匹配字符串」。
     * 上述的朴素解法，不考虑剪枝的话复杂度是 O(m * n)O(m∗n) 的，而 KMP 算法的复杂度为 O(m + n)O(m+n)。
     *
     * @param haystack
     * @param needle
     *
     * @return
     */
    public int strStr3(String haystack, String needle) {
        return -1;
    }
}
