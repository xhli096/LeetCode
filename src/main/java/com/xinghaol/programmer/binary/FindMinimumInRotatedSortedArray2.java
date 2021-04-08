package com.xinghaol.programmer.binary;

/**
 * @author: lixinghao
 * @date: 2021/4/8 1:05 下午
 * @Description: 154. 寻找旋转排序数组中的最小值 II
 */
public class FindMinimumInRotatedSortedArray2 {

    public int findMin(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int result = nums[0];

        for (int i = 1; i < length; i++) {
            result = Math.min(result, nums[i]);
        }

        return result;
    }

    public int findMin2(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int left = 0;
        int right = length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // 右侧有序
                // 如果中间值大于最右边的值，说明旋转之后最小的
                // 数字肯定在mid的右边，比如[3, 4, 5, 6, 7, 1, 2]
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // 左侧有序
                //如果中间值小于最右边的值，说明旋转之后最小的
                //数字肯定在mid的前面，比如[6, 7, 1, 2, 3, 4, 5],
                //注意这里mid是不能减1的，比如[3，1，3]，我们这里只是
                //证明了numbers[mid]比numbers[right]小，但有可能
                //numbers[mid]是最小的，所以我们不能把它给排除掉
                right = mid;
            } else {
                // nums[mid] == nums[right] 说明有相同的元素，缩小排查范围
                //如果中间值等于最后一个元素的值，我们是没法确定最小值是
                // 在mid的前面还是后面，但我们可以缩小查找范围，让right
                // 减1，因为即使right指向的是最小值，但因为他的值和mid
                // 指向的一样，我们这里并没有排除mid，所以结果是不会有影响的。
                //比如[3，1，3，3，3，3，3]和[3，3，3，3，3，1，3],中间的值
                //等于最右边的值，但我们没法确定最小值是在左边还是右边
                right--;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray2 findMinimumInRotatedSortedArray2 = new FindMinimumInRotatedSortedArray2();
        int min2 = findMinimumInRotatedSortedArray2.findMin2(new int[] { 1, 3, 3 });
        System.out.println(min2);
    }
}
