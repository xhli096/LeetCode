package com.xinghaol.programmer;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/3/20 2:36 下午
 * @Description: 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * <p>
 * 限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 */
public class GetLeastNumbers {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 1};
        int[] result = getLeastNumbers(arr, 1);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 快排
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 最后一个参数表示我们要找的是下标为k-1的数，前k个数转换为下标为k-1
        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }

    private static int[] quickSearch(int[] nums, int lo, int hi, int k) {
        // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k就返回j以及j左边所有的数；
        int j = partition(nums, lo, hi);
        System.out.println("j: " + j);
        if (j == k) {
            return Arrays.copyOf(nums, j + 1);
        }
        // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
        return j > k ? quickSearch(nums, lo, j - 1, k) : quickSearch(nums, j + 1, hi, k);
    }

    /**
     * 快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
     *
     * @param nums
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo;
        int j = hi + 1;
        // 快排，当i在j后面或在相同位置时不再移动。
        // 最后将 j停留的位置与开始位置交换
        // 对整个数组进行快排，则以当前j的元素为界限，分为左、右两个子数组，递归处理即可
        while (true) {
            while (++i <= hi && nums[i] < v) {
                ;
            }
            while (--j >= lo && nums[j] > v) {
                ;
            }
            if (i >= j) {
                break;
            }
            int t = nums[j];
            nums[j] = nums[i];
            nums[i] = t;
        }
        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }
}
