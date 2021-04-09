package com.xinghaol.programmer.binary;

import java.util.Random;

/**
 * @author: lixinghao
 * @date: 2021/4/8 5:35 下午
 * @Description: 528. 按权重随机选择
 *  就是找前缀和 大于等于生成的随机数的那个index下标
 */
public class RandomPickWithWeight {

    private int[] wsum;

    public RandomPickWithWeight(int[] w) {
        // wsum[i]为前i个位置的前缀和
        wsum = w;
        for (int i = 1; i < wsum.length; i++) {
            wsum[i] += wsum[i - 1];
        }
    }

    public int pickIndex() {
        Random random = new Random();
        // 每一个位置的值，在总和中的占比即为 nums[i] / wsum[wsum.length - 1]
        int target = random.nextInt(wsum[wsum.length - 1] + 1);

        int left = 0;
        int right = wsum.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left + 1) / 2;
            if (wsum[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (wsum[left] >= target) {
            return left;
        }

        return right;
    }
}
