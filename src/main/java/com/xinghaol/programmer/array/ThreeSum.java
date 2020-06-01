package com.xinghaol.programmer.array;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/5/30 5:26 下午
 * @Description:
 */
public class ThreeSum {
    /**
     * 数组遍历
     * 1、对数组进行排序
     * 2、排序后固定一个数字nums[i]，再使用左、右指针指向nums[i]后面的左右两端，分为被nums[left]、nums[right]；
     * 计算三数之和是否为0，如果为0，则加入结果集。
     * 3、如果当前nums[i] >= 0，则必定不满足三数之和为0.=》因为为排序数组，后面的数字只会比当前元素大。
     * 4、如果 nums[i] == nums[i-1],应该跳过该元素，否则会导致结果重复
     * 5、当sum == 0时，若nums[left] == nums[left+1] 会导致结果重复，则left++；
     * 6、当sum == 0时，若nums[right] = nusm[right-1]，会导致结果重复，则right--；
     * <p>
     * 时间复杂度：O(n^2)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (length < 3) {
            return result;
        }
        // 对数组排序
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            int current = nums[i];
            // 如果大于0，找到了所有结果。
            if (current > 0) {
                break;
            }
            // nums[i] == nums[i - 1]，会导致重复结果的出现。
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> result = threeSum.threeSum2(new int[]{-1, 0, 1, 2, -1, -4}, 3);
        System.out.println(JSON.toJSONString(result));
    }

    public List<List<Integer>> threeSum2(int[] nums, int K) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (length < 3) {
            return result;
        }
        // 对数组排序
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            int current = nums[i];
            // 如果大于0，找到了所有结果。
            if (current > K) {
                break;
            }
            // nums[i] == nums[i - 1]，会导致重复结果的出现。
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == K) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < K) {
                    left++;
                } else if (sum > K) {
                    right--;
                }
            }
        }

        return result;
    }
}
