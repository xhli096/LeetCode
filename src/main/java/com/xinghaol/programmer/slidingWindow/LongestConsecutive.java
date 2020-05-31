package com.xinghaol.programmer.slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/5/23 12:06 下午
 * @Description: 128. 最长连续序列
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return length;
        }
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(nums[i]);
        }
        int maxLen = 0;
        int currentLen = 1;
        for (int i = 0; i < length; ) {
            int next = nums[i] + 1;
            if (list.contains(next)) {
                currentLen++;
                i = list.indexOf(next);
            } else {
                i++;
                maxLen = Math.max(maxLen, currentLen);
                currentLen = 1;
            }
        }
        maxLen = Math.max(maxLen, currentLen);

        return maxLen;
    }

    public int longestConsecutive2(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return length;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            set.add(nums[i]);
        }

        int maxLen = 0;
        int current = 1;

        for (int num : set) {
            int value = num;
            if (set.contains(num + 1)) {
                current++;
                value = value + 1;
                while (set.contains(value + 1)) {
                    current++;
                    value = value + 1;
                }
            }
            maxLen = Math.max(current, maxLen);
            current = 1;
        }

        return maxLen;
    }

    /**
     * 官方题解
     *
     * @param nums
     * @return
     */
    public int longestConsecutive3(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public static void main(String[] args) {
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        int consecutive = longestConsecutive.longestConsecutive2(new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6});
        System.out.println(consecutive);
    }
}
