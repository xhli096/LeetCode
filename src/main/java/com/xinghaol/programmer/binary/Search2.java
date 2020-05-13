package com.xinghaol.programmer.binary;

/**
 * @author: lixinghao
 * @date: 2020/5/7 10:35 下午
 * @Description: 81. 搜索旋转排序数组 II
 * 继续体会二分查找的模板了
 */
public class Search2 {
    public boolean search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return false;
        }
        int left = 0;
        int right = length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 左侧有序
            if (nums[mid] > nums[left]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left]) {
                // 右侧有序，注意nums[mid]<target;如果有等于号，则就陷入死循环了。
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                // nums[mid] == nums[left]，排除左侧边界不为target值，左侧边界向右靠近
                if (nums[left] == target) {
                    return true;
                } else {
                    left = left + 1;
                }
            }
        }

        if (nums[left] == target) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Search2 search2 = new Search2();
        boolean search = search2.search(new int[]{1, 3}, 3);
        System.out.println(search);
    }
}
