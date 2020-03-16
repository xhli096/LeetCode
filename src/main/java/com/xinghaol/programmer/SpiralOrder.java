package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/16 1:07 下午
 * @Description: 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 *
 * 使用下标来控制，注意每次都是往内收一圈。不要下标越界
 */
public class SpiralOrder {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] result = new int[matrix.length * matrix[0].length];

        int left = 0;
        int right = matrix[0].length - 1;
        int up = 0;
        int down = matrix.length - 1;

        int index = 0;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                result[index++] = matrix[up][i];
            }
            for (int i = up + 1; i <= down; i++) {
                result[index++] = matrix[i][right];
            }
            if (up != down) {
                for (int i = right - 1; i >= left; i--) {
                    result[index++] = matrix[down][i];
                }
            }
            if (left != right) {
                for (int i = down - 1; i > up; i--) {
                    result[index++] = matrix[i][left];
                }
            }
            left++;
            up++;
            right--;
            down--;
        }

        return result;
    }
}
