package com.xinghaol.programmer.array;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2021/4/18 11:53 下午
 * @Description: 581. 最短无序连续子数组
 */
public class ShortestUnsortedContinuousSubarray {

    /**
     * 时间复杂度O(nlogn)
     * 空间复杂度O(n)
     *
     * @param nums
     *
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int length = nums.length;
        if (length == 0 || length == 1) {
            return 0;
        }
        int[] tmp = new int[length];

        for (int i = 0; i < length; i++) {
            tmp[i] = nums[i];
        }
        Arrays.sort(tmp);

        int start = 0;
        for (int i = 0; i < length; i++) {
            if (tmp[i] != nums[i]) {
                start = i;
                i = length;
            }
        }

        if (start == length - 1) {
            return 0;
        }
        int end = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (tmp[i] != nums[i]) {
                end = i;
                i = -1;
            }
        }

        if (start == 0 && end == 0) {
            return 0;
        }

        return end - start + 1;
    }

    public static void main(String[] args) {
        ShortestUnsortedContinuousSubarray shortestUnsortedContinuousSubarray = new ShortestUnsortedContinuousSubarray();
        int unsortedSubarray = shortestUnsortedContinuousSubarray.findUnsortedSubarray2(new int[] { 1, 2, 3, 4 });
        System.out.println(unsortedSubarray);
    }

    /**
     * 将整个数组分为三段，递增有序的左段，乱序的中段和递增有序的右段
     * 中段数据无需，当时满足最小值大于左段中的最大值，最大值小于右段中的最小值；
     * 找到中段的左右边界，定义为left+right
     *  从左向右维护一个最大值max，在进入右段之前，num[i]均是小于max
     * @param nums
     *
     * @return
     */
    public int findUnsortedSubarray2(int[] nums) {
        int length = nums.length;
        int min = nums[length - 1];
        int max = nums[0];
        int left = 0;
        int right = -1;
        for (int i = 0; i < length; i++) {
            if (nums[i] < max) {
                right = i;
            } else {
                max = nums[i];
            }

            if (nums[length - i - 1] > min) {
                left = length - i - 1;
            } else {
                min = nums[length - i - 1];
            }
        }
        return right - left + 1;
    }
}
