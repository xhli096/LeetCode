package com.xinghaol.programmer.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/5/27 6:22 下午
 * @Description: 滑动窗口模板
 */
public class SlidingWindowTemplate {
    /**
     * 滑动窗口适用于：
     *
     * @param s
     * @param t
     */
    public void slidingWindowTemplate(String s, String t) {
        int lengthS = s.length();
        int lengthT = t.length();

        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        // 统计s串中各个字符的
        for (Character c : charArrayS) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        // 符合题目要求
        int valid = 0;

        /*while (right < lengthT) {
            char c = t.charAt(right);
            right++;
            // TODO 进行窗口内的数据一系列的更新
            // ...

            while (窗口是否收缩) {
                if (符合题目要求) {
                    valid++;
                }
                char d = t.charAt(left);
                left++;

                // TODO 判断移除的元素，对窗口内的数据进行一些列的操作
                // ....
            }
        }*/
    }
}
