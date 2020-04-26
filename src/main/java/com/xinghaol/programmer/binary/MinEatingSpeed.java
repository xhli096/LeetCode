package com.xinghaol.programmer.binary;

/**
 * @author: lixinghao
 * @date: 2020/4/22 4:16 下午
 * @Description: 二分查找-875. 爱吃香蕉的珂珂
 * https://leetcode-cn.com/problems/koko-eating-bananas/
 */
public class MinEatingSpeed {
    public int minEatingSpeed(int[] piles, int H) {
        // 查找数组中，香蕉最多的数量
        int max = 1;
        for (int num : piles) {
            max = Math.max(num, max);
        }

        int left = 1;
        int right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // 大于H，一定不符合题意。说明速度太慢，则需要加快速度，向后面的区间搜索
            if (calculateSum(piles, mid) > H) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int calculateSum(int[] piles, int speed) {
        int sum = 0;

        for (int num : piles) {
            // 向上取整可以这样写
            //sum += Math.ceil((double)num/(double)speed);
            sum += (num + speed - 1) / speed;
        }

        return sum;
    }
}
