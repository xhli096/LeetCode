package com.xinghaol.programmer.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: lixinghao
 * @date: 2020/6/16 11:13 下午
 * @Description: 349. 两个数组的交集
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 */
public class Intersection {
    /**
     * 两个set的解法
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        if (length1 == 0 || length2 == 0) {
            return new int[]{};
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Arrays.asList(nums1);
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        List<Integer> list = new ArrayList<>();
        for (Integer integer : set1) {
            if (set2.contains(integer)) {
                list.add(integer);
            }
        }
        int[] result = new int[list.size()];
        AtomicInteger index = new AtomicInteger();
        list.forEach(integer -> result[index.getAndIncrement()] = integer);
        return result;
    }

    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        int[] result = intersection.intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        System.out.println(Arrays.toString(result));
    }

    /**
     * 双指针解法
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                set.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            }
        }
        int[] res = new int[set.size()];
        int index = 0;
        for (int num : set) {
            res[index++] = num;
        }

        return res;
    }
}
