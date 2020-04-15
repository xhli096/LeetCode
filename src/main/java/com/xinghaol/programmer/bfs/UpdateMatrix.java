package com.xinghaol.programmer.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/4/15 4:24 下午
 * @Description: 01矩阵
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 * 示例 1:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 示例 2:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * 注意:
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 */
public class UpdateMatrix {
    /**
     * 还是参考甜姨的题解，在总结一下bfs类型题目的套路，见包路径下"bfs类型套路总结"
     * 从0开始向外扩散到1，计算距离1最近的0的距离
     */
    public int[][] updateMatrix(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];

        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return result;
        }
        Queue<int[]> queue = new LinkedList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
        // 标识左右方向值
        int[] fowardX = new int[]{-1, 1, 0, 0};
        int[] fowardY = new int[]{0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + fowardX[i];
                int newY = y + fowardY[i];
                // 如果四邻域的点是 -1，表示这个点是未被访问过的 1
                // 所以这个点到 0 的距离就可以更新成 matrix[x][y] + 1。
                if (newX >= 0 && newY >= 0 && newX < rows && newY < cols && matrix[newX][newY] == -1) {
                    matrix[newX][newY] = matrix[x][y] + 1;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }

        return matrix;
    }

    /**
     * 以下是失败的算法。有死循环。不晓得啥问题
     *
     * @param matrix
     * @return
     */
    public int[][] updateMatrix2(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];

        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return result;
        }
        // 赋值
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j];
            }
        }

        boolean[][] flag = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                } else {
                    int min = dfs(matrix, flag, result, i, j);
                    result[i][j] = min;
                }
            }
        }

        return result;
    }

    private int dfs(int[][] matrix, boolean[][] flag, int[][] result, int row, int col) {
        if (row >= matrix.length || col >= matrix[0].length || row < 0 || col < 0) {
            return Integer.MIN_VALUE;
        }
        if (matrix[row][col] == 0) {
            return 1;
        }
        if (flag[row][col]) {
            return result[row][col] + 1;
        }
        int left = dfs(matrix, flag, result, row, col - 1);
        System.out.println("left:" + left);
        int right = dfs(matrix, flag, result, row, col + 1);
        System.out.println("right:" + right);
        int up = dfs(matrix, flag, result, row - 1, col);
        System.out.println("up:" + up);
        int down = dfs(matrix, flag, result, row + 1, col);
        System.out.println("down:" + down);
        flag[row][col] = true;
        int min = Math.min(Math.min(Math.min(left, right), up), down);

        return min;
    }
}
