package com.xinghaol.programmer.array;

import java.util.Random;

/**
 * @author: lixinghao
 * @date: 2021/4/20 10:13 上午
 * @Description: 384. 打乱数组
 * 洗牌算法：https://leetcode-cn.com/problems/shuffle-an-array/solution/xi-pai-suan-fa-shen-du-xiang-jie-by-labuladong/
 */
public class ShuffleAnArray {

    private int[] array;

    private int[] original;

    private Random random = new Random();

    public ShuffleAnArray(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return original;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            swap(array, i, shuffle(i, length));
        }

        return array;
    }

    /**
     * 随机生成一个min~max的随机数，先生成一个0~max-min的随机数，再加上min，则是min~max的随机数
     *
     * @param min
     * @param max
     *
     * @return
     */
    private int shuffle(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
