package com.xinghaol.programmer.binary;

/**
 * @author: lixinghao
 * @date: 2020/4/4 10:17 下午
 * @Description: 接雨水
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class TrappingRainWater {
    /**
     * 双指针法，想象两个很高的bar把中间的东西给夹住。开始两个bar都为0，不断的去增高。
     * 存储的水会从较低的bar处流走。
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        // 左右指针
        int left = 0;
        int right = height.length - 1;
        // 左右bar
        int leftMax = 0;
        int rightMax = 0;
        int result = 0;

        while (left <= right) {
            if (leftMax < rightMax) {
                // 此时leftMax较小，可以存储leftMax-height[left]高度的水。
                // 如果height[left] > leftMax，则此处无法存储水
                result += Math.max(leftMax - height[left], 0);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                result += Math.max(rightMax - height[right], 0);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }

        return result;
    }
}
