package com.xinghaol.programmer.binary;

/**
 * @author: lixinghao
 * @date: 2020/4/25 10:04 下午
 * @Description: 33. 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class Search {
    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }

        int left = 0;
        int right = length - 1;

        /**
         * 因为数组元素不重复，mid的左右两边一定会有一部分是有序的。
         */
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            // 当num[mid] > nums[right] 说明，旋转数组的旋转区间位于mid的右侧，左侧的区间为有序
            if (nums[mid] > nums[right]) {
                if (nums[left] <= target && target <= nums[mid - 1]) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            } else {
                // 在else中，说明nums[mid] < nums[right]说明，右侧的区间是有序的
                if (nums[mid] <= target && target <= nums[target]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
        }

        if (nums[left] == target) {
            return left;
        }

        return -1;
    }


    public int search2(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }

        int left = 0;
        int right = length - 1;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (nums[mid] > nums[right]) {
                // 中点位置大于最右侧的值，mid左侧的内容为有序
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                // mid右侧为有序
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
        }

        if (nums[left] == target) {
            return left;
        }

        return -1;
    }

    public static void main(String[] args) {
        Search search = new Search();
        int i = search.search3(new int[]{1, 1, 2}, 1);
        System.out.println(i);
    }

    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     * https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
     *
     * @param nums
     * @param target
     * @return
     */
    public int search3(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int left = 0;
        int right = length - 1;
        int count = 0;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                count++;
                int index = middle - 1;
                while (index >= 0 && nums[index] == target) {
                    count++;
                    index--;
                }
                index = middle + 1;
                while (index < length && nums[index] == target) {
                    count++;
                    index++;
                }
                break;
            } else if (nums[middle] <= target) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }

        return count;
    }

}
