package com.xinghaol.programmer.array;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/4 11:03 上午
 * @Description: 238. 除自身以外数组的乘积
 */
public class ProductExceptSelf {
    /**
     * 前缀积和后缀积解法
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] prefixArray = new int[length];
        int[] suffixArray = new int[length];

        int prefixSum = nums[0];
        prefixArray[0] = prefixSum;
        for (int i = 1; i < length; i++) {
            prefixSum = prefixSum * nums[i];
            prefixArray[i] = prefixSum;
        }
        int suffixSum = nums[length - 1];
        suffixArray[length - 1] = suffixSum;
        for (int i = length - 2; i >= 0; i--) {
            suffixSum = suffixSum * nums[i];
            suffixArray[i] = suffixSum;
        }
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                result[i] = suffixArray[i + 1];
            } else if (i == length - 1) {
                result[i] = prefixArray[i - 1];
            } else {
                result[i] = prefixArray[i - 1] * suffixArray[i + 1];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ProductExceptSelf productExceptSelf = new ProductExceptSelf();
        int[] result = productExceptSelf.productExceptSelf2(new int[]{1, 2, 3, 4});
        System.out.println(Arrays.toString(result));
    }

    /**
     * 空间复杂度为O(1)d的方法
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        // 第一个元素左前缀积为1
        result[0] = 1;
        for (int i = 1; i < length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        // 使用一个变量来计算后缀积
        int r = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = result[i] * r;
            r *= nums[i];
        }

        return result;
    }
}
