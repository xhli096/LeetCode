package com.xinghaol.programmer.array;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/4 10:29 下午
 * @Description:
 */
public class FindKthLargest {
    /**
     * 排序后返回下标length-K位置元素即可
     * 时间复杂度：O(N \log N)O(NlogN)，这里 NN 是数组的长度，算法的性能消耗主要在排序，JDK 默认使用快速排序，因此时间复杂度为 O(N \log N)O(NlogN)。
     * 空间复杂度：O(1)O(1)，这里是原地排序，没有借助额外的辅助空间。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int length = nums.length;
        Arrays.sort(nums);

        return nums[length - k];
    }

    public static void main(String[] args) {
        FindKthLargest findKthLargest = new FindKthLargest();
        int kthLargest = findKthLargest.findKthLargest(new int[]{99, 99}, 1);
        System.out.println(kthLargest);
    }
}
