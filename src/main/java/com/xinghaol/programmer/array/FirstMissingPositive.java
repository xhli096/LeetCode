package com.xinghaol.programmer.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/6/27 8:54 下午
 * @Description: 41. 缺失的第一个正数
 * https://leetcode-cn.com/problems/first-missing-positive/
 */
public class FirstMissingPositive {
    /**
     * hash的方法，空间复杂度不满足
     * 时间复杂度：O(N)，这里 N 表示数组的长度。第 1 次遍历了数组，第 2 次遍历了区间 [1, len] 里的元素。
     * 空间复杂度：O(N)，把 N 个数存在哈希表里面，使用了 N 个空间。
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }

        for (int i = 1; i <= len; i++) {
            if (!hashSet.contains(i)) {
                return i;
            }
        }

        return len + 1;
    }

    /**
     * 二分查找思想
     * 时间复杂度：O(NlogN)，这里 N 表示数组的长度。时间复杂度主要消耗在排序上，排序使用 O(NlogN)。二分查找使用 O(logN)；
     * 空间复杂度：O(1)。
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);

        for (int i = 1; i <= len; i++) {
            int res = binarySearch(nums, i);
            if (res == -1) {
                return i;
            }
        }
        return len + 1;
    }

    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        int result = firstMissingPositive.firstMissingPositive3(new int[]{3, 4, -1, 1});
        System.out.println(result);
    }

    /**
     * 原地哈希（哈希函数为：f(nums[i]) = nums[i] - 1）
     *
     * @param nums
     * @return
     * @威威大佬 https://leetcode-cn.com/problems/first-missing-positive/solution/tong-pai-xu-python-dai-ma-by-liweiwei1419/
     * 由于题目要求我们「只能使用常数级别的空间」，而要找的数一定在 [1, N + 1] 左闭右闭（这里 N 是数组的长度）这个区间里。因此，我们可以就把原始的数组当做哈希表来使用。事实上，哈希表其实本身也是一个数组；
     * 我们要找的数就在 [1, N + 1] 里，最后 N + 1 这个元素我们不用找。因为在前面的 N 个元素都找不到的情况下，我们才返回 N + 1；
     * 那么，我们可以采取这样的思路：就把 11 这个数放到下标为 00 的位置， 22 这个数放到下标为 11 的位置，按照这种思路整理一遍数组。然后我们再遍历一次数组，第 11 个遇到的它的值不等于下标的那个数，就是我们要找的缺失的第一个正数。
     * 这个思想就相当于我们自己编写哈希函数，这个哈希函数的规则特别简单，那就是数值为 i 的数映射到下标为 i - 1 的位置。
     */
    public int firstMissingPositive3(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 保证当前位置元素在0~n之间，且在num[nums[i] - 1]的位置上的值不为nums[i]，进行交换
            // 交换过来的元素，需要继续交换，直到不满足条件位置
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);
            }
        }

        // [1, -1, 3, 4]
        // 查看那个位置的值不为i+1的规则
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 都正确则返回数组长度 + 1
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
