package com.xinghaol.programmer.array;

import com.alibaba.fastjson.JSON;

/**
 * @author: lixinghao
 * @date: 2020/6/23 11:26 下午
 * @Description: 48. 旋转图像
 * https://leetcode-cn.com/problems/rotate-image/
 */
public class Rotate {
    /**
     * 先转置，再翻转，即可达到最优O(n^2)
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (rows == 0) {
            return;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < cols; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        System.out.println(JSON.toJSONString(matrix));

        // 翻转，以中间为轴，进行左右翻转
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][rows - j - 1];
                matrix[i][rows - j - 1] = tmp;
            }
        }

        System.out.println(JSON.toJSONString(matrix));
    }

    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        rotate.rotate(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
    }

    /**
     * 第一行将放在最后一列，第二行放在倒数第二列。按行和按列来进行处理
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N^2)
     * N为matrix的边长
     *
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
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
    public void rotate3(int[][] matrix) {
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
