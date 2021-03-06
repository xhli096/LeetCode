package com.xinghaol.programmer.binary;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/3/23 10:42 上午
 * @Description: 寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class FindMedianSortedArrays {
    /**
     * 这道题让我们求两个有序数组的中位数，而且限制了时间复杂度为O(log (m+n))，
     * 看到这个时间复杂度，自然而然的想到了应该使用二分查找法来求解。那么回顾一下中位数的定义，
     * 如果某个有序数组长度是奇数，那么其中位数就是最中间那个，如果是偶数，那么就是最中间两个数字的平均值。
     * 这里对于两个有序数组也是一样的，假设两个有序数组的长度分别为m和n，由于两个数组长度之和 m+n 的奇偶不确定，
     * 因此需要分情况来讨论，对于奇数的情况，直接找到最中间的数即可，偶数的话需要求最中间两个数的平均值。
     * 为了简化代码，不分情况讨论，我们使用一个小trick，我们分别找第 (m+n+1) / 2 个，和 (m+n+2) / 2 个，
     * 然后求其平均值即可，这对奇偶数均适用。加入 m+n 为奇数的话，那么其实 (m+n+1) / 2 和 (m+n+2) / 2 的值相等，
     * 相当于两个相同的数字相加再除以2，还是其本身。
     * <p>
     * 这里我们需要定义一个函数来在两个有序数组中找到第K个元素，下面重点来看如何实现找到第K个元素。
     * 首先，为了避免产生新的数组从而增加时间复杂度，我们使用两个变量i和j分别来标记数组nums1和nums2的起始位置。
     * 然后来处理一些边界问题，比如当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已经被淘汰了，相当于一个空数组了，
     * 那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。
     * 还有就是如果K=1的话，那么我们只要比较nums1和nums2的起始位置i和j上的数字就可以了。
     * 难点就在于一般的情况怎么处理？
     * 因为我们需要在两个有序数组中找到第K个元素，为了加快搜索的速度，我们要使用二分法，
     * 对K二分，意思是我们需要分别在nums1和nums2中查找第K/2个元素，注意这里由于两个数组的长度不定，所以有可能某个数组没有第K/2个数字，
     * 所以我们需要先检查一下，数组中到底存不存在第K/2个数字，如果存在就取出来，否则就赋值上一个整型最大值。
     * 如果某个数组没有第K/2个数字，那么我们就淘汰另一个数字的前K/2个数字即可。
     * 有没有可能两个数组都不存在第K/2个数字呢，这道题里是不可能的，因为我们的K不是任意给的，而是给的m+n的中间值，
     * 所以必定至少会有一个数组是存在第K/2个数字的。
     * 最后就是二分法的核心啦，比较这两个数组的第K/2小的数字midVal1和midVal2的大小，
     * 如果第一个数组的第K/2个数字小的话，那么说明我们要找的数字肯定不在nums1中的前K/2个数字，所以我们可以将其淘汰，
     * 将nums1的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归。
     * 反之，我们淘汰nums2中的前K/2个数字，并将nums2的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归即可。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        // 如果length1+length2为奇数，则left和right的值相等；反之则为中间的两个数字
        int left = (length1 + length2 + 1) / 2;
        int right = (length1 + length2 + 2) / 2;

        // 分别找left和right的值，加起来/2则就是中位数
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    private int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i > nums1.length) {
            // nums1为空数组
            return nums2[j + k - 1];
        }
        if (j > nums2.length) {
            // nums2为空数组
            return nums1[i + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }

        int midValue1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midValue2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;

        if (midValue1 < midValue2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }

    /**
     * 下面是自己写的错误的代码
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        // 去中间的那个数即可
        double mid1 = 0.0;
        if (length1 % 2 == 1) {
            int left = 0;
            int right = length1 - 1;
            mid1 = nums1[left + (right - left) / 2];
        } else {
            int left = 0;
            int right = length1 - 1;
            int mid = left + (right - left) / 2;
            mid1 = (nums1[mid] + nums1[mid + 1]) / 2.0;
        }
        double mid2 = 0.0;
        if (length2 % 2 == 1) {
            int left = 0;
            int right = length2 - 1;
            mid2 = nums2[left + (right - left) / 2];
        } else {
            int left = 0;
            int right = length2 - 1;
            int mid = left + (right - left) / 2;
            mid2 = (nums2[mid] + nums2[mid + 1]) / 2.0;
        }

        return (mid1 + mid2) / 2;
    }

    /**
     * 归并排序后找到中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        int[] nums = new int[length1 + length2];
        for (int i = 0; i < nums1.length; i++) {
            nums[i] = nums1[i];
        }

        for (int i = length1; i < length1 + length2; i++) {
            nums[i] = nums2[i - length1];
        }
        Arrays.sort(nums);

        double result = 0.0;
        int left = 0;
        int right = nums.length - 1;
        if (nums.length % 2 == 0) {
            int mid = left + (right - left) / 2;
            result = (nums[mid] + nums[mid + 1]) / 2.0;
        } else {
            result = nums[left + (right - left) / 2];
        }
        return result;
    }

    public static void main(String[] args) {
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        double result = findMedianSortedArrays.findMedianSortedArrays3(new int[]{1, 2}, new int[]{2});
        System.out.println(result);
    }
}
