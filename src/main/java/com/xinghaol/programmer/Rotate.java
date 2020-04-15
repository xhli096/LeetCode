package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/4/7 11:50 上午
 * @Description:
 */
public class Rotate {
    /**
     * 第一行将放在最后一列，第二行放在倒数第二列。按行和按列来进行处理
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N^2)
     * N为matrix的边长
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int[][] copyMatrix = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                copyMatrix[i][j] = matrix[i][j];
            }
        }

        // 第一行经过运算后，称为最右面的一列。以此类推
        for (int i = 0; i < copyMatrix.length; i++) {
            for (int j = 0; j < copyMatrix[0].length; j++) {
                matrix[j][copyMatrix[0].length - 1 - i] = copyMatrix[i][j];
            }
        }
    }

    /**
     * 原地旋转
     * 先由对角线进行翻转，再按每一行的中点进行翻转
     *
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int length = matrix.length;

        // 先以对角线（左上-右下）为轴进行翻转。最后一行不需要处理，最后一行对角线元素右侧没有其他元素。
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        // 再对每一行以中点进行翻转
        int middle = length >> 1;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < middle; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][length - 1 - j];
                matrix[i][length - 1 - j] = tmp;
            }
        }
    }
}
