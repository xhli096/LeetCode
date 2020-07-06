package com.xinghaol.programmer.array;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/7/2 1:11 下午
 * @Description: 378. 有序矩阵中第K小的元素
 * 378. 有序矩阵中第K小的元素
 */
public class KthSmallest {
    /**
     * 排序的思路
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] array = new int[rows * cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[index++] = matrix[i][j];
            }
        }

        Arrays.sort(array);

        return array[k - 1];
    }

    /**
     * 二分查找法，矩阵内的元素从左上到右下是依次递增的，左上角为matrix[0][0]，且它为整个二维数组中的最小值。matrix[length-1][length-1]为最大值
     * 将其分别记作l和r，可以发现一个性质：任取一个数mid满足l <= mid <= r，那么矩阵中不大于mid的数，肯定全部分布在矩阵的左上角。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;

        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }

        return num >= k;
    }

    public static void main(String[] args) {
        KthSmallest kthSmallest = new KthSmallest();
        int smallest = kthSmallest.kthSmallest(new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        }, 8);
        System.out.println(smallest);
    }
}
