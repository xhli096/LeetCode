package com.xinghaol.programmer.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/5/31 7:18 下午
 * @Description: 674. 最长连续递增序列
 */
public class FindLengthOfLCIS {
    /**
     * 使用滑动窗口解决最长连续递增序列
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int max = 0;

        Set<Integer> valueSet = new HashSet<>();
        while (right < length) {
            int current = nums[right];
            if (valueSet.isEmpty()) {
                valueSet.add(current);
                right++;
                max = Math.max(max, valueSet.size());
            } else {
                if (current > nums[right - 1]) {
                    valueSet.add(current);
                    right++;
                } else {
                    valueSet = new HashSet<>();
                    valueSet.add(current);
                    left = right;
                    right++;
                }
                max = Math.max(max, right - left);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        FindLengthOfLCIS findLengthOfLCIS = new FindLengthOfLCIS();
        int lengthOfLCIS = findLengthOfLCIS.findLengthOfLCIS(new int[]{1, 3, 5, 4, 7});
        System.out.println(lengthOfLCIS);
    }

    /**
     * 其实不需要记录路径上的数字
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS2(int[] nums) {
        int max = 0;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] >= nums[i]) {
                start = i;
            }
            max = max > (i - start + 1) ? max : (i - start + 1);
        }
        return max;
    }
}
