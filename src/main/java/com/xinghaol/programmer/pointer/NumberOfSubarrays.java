package com.xinghaol.programmer.pointer;

import org.junit.Test;

/**
 * @author: lixinghao
 * @date: 2020/4/21 4:00 下午
 * @Description: 1248. 统计「优美子数组」
 * 给你一个整数数组 nums 和一个整数 k。
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * 请返回这个数组中「优美子数组」的数目。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 */
public class NumberOfSubarrays {
    /**
     * 双指针，滑动窗口解法 @Sweetiee 甜姨的题解
     * 不断扩大right的值，直到区间中含有k个奇数
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int length = nums.length;

        if (length <= 0) {
            return 0;
        }
        // 左右指针
        int left = 0;
        int right = 0;
        // 奇数的个数
        int oddCount = 0;
        int result = 0;

        while (right < length) {
            // 使用位运算代替取余
            if ((nums[right++] & 1) == 1) {
                oddCount++;
            }

            // 说明当前的区间中已经含有了k个奇数，来确定优美子数组的个数
            if (oddCount == k) {
                // 向将区间的右边界继续向右扩展，直到出界或者遇到下一个奇数
                // 此时这个区间中的第k个元素的右边的偶数就计算出来了
                int tmp = right;
                while (right < length && (nums[right] & 1) == 0) {
                    right++;
                }
                // 此时退出循环，即right出界或者遇到下一个奇数，之间的就是偶数
                int rightEventCount = right - tmp;
                int leftEventCount = 0;
                // 找到区间内的第一个奇数，则在第一个奇数之前的均为区间内的整数，均可以选为 优美子数组
                while ((nums[left] & 1) == 0) {
                    leftEventCount++;
                    left++;
                }

                // 第 1 个奇数左边的 leftEventCount 个偶数都可以作为优美子数组的起点
                // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
                // 第 k 个奇数右边的 rightEventCount 个偶数都可以作为优美子数组的终点
                // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
                // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEventCount + 1) * (rightEventCount + 1)
                result += (leftEventCount + 1) * (rightEventCount + 1);

                // 该区间统计结束后，继续到下一个区间，此时left应该指向下一个元素。
                left++;
                oddCount--;
            }
        }

        return result;
    }

    /**
     * 前缀和的方式，通过debug方式查看了执行情况。还是有点不理解
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays2(int[] nums, int k) {
        // prefixCount的下标是前缀和（即当前奇数的个数），值是前缀和的个数
        int[] prefixCount = new int[nums.length + 1];
        // 下标0，表示当前为0个奇数；值为1，则表明当前有1个下标
        prefixCount[0] = 1;

        // 遍历原数组，计算当前的前缀和，统计到 prefixCnt 数组中，
        // 并且在 res 中累加上与当前前缀和差值为 k 的前缀和的个数。
        int sum = 0;
        int result = 0;
        for (int num : nums) {
            // 如果num为奇数，则sum的值加一；否则sum维持原值
            sum += (num & 1);
            prefixCount[sum]++;
            if (sum >= k) {
                result += prefixCount[sum - k];
            }
        }

        return result;
    }

    @Test
    public void fun() {
        int[] nums = new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        System.out.println(numberOfSubarrays2(nums, 2));

    }
}
