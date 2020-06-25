package com.xinghaol.programmer.array;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/24 5:23 下午
 * @Description: 16. 最接近的三数之和
 * https://leetcode-cn.com/problems/3sum-closest/
 */
public class ThreeSumClosest {
    /**
     * 同15题，用双指针方法来解决
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int length = nums.length;
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < length; i++) {
            int start = i + 1;
            int end = length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                // 下面条件成立，说明当前和跟接近target值，进行交换
                if (Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }
                // 如果sum > target，end取得值太大了
                if (sum > target) {
                    end--;
                } else if (sum < target) {
                    // 如果sum < target，start值取得太小了
                    start++;
                } else {
                    // 如果恰好是target值，则直接返回即可。
                    return result;
                }
            }
        }

        return result;
    }
}
