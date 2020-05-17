package com.xinghaol.programmer.bit;

/**
 * @author: lixinghao
 * @date: 2020/5/14 1:09 下午
 * @Description: 136. 只出现一次的数字
 * https://leetcode-cn.com/problems/single-number/
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class SingleNumber {
    /**
     * 因为只有一个元素出现一次，其余每个元素均出现两次，考虑异或运算。
     * 相同的元素异或结果为0，最后的结果即为出现一次的元素。
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }

        return ans;
    }
}
