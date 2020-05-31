package com.xinghaol.programmer.array;

import com.alibaba.fastjson.JSON;

/**
 * @author: lixinghao
 * @date: 2020/5/24 4:14 下午
 * @Description:
 */
public class Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums = new int[m];
        System.arraycopy(nums1, 0, nums, 0, m);
        int index1 = 0;
        int index2 = 0;
        int index = 0;

        while (index1 < m && index2 < n) {
            if (nums[index1] >= nums2[index2]) {
                nums1[index] = nums2[index2];
                index2++;
            } else {
                nums1[index] = nums[index1];
                index1++;
            }
            index++;
        }
        if (index1 < m) {
            System.arraycopy(nums, index1, nums1, index1+index2, m + n - index1-index2);
        }
        if (index2 < n) {
            System.arraycopy(nums2, index2, nums1, index1+index2, m + n - index1-index2);
        }
        System.out.println(JSON.toJSONString(nums1));
    }

    public static void main(String[] args) {
        Merge merge = new Merge();
        merge.merge(new int[]{0}, 0, new int[]{1}, 1);
    }
}
