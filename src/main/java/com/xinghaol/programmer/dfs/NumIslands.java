package com.xinghaol.programmer.dfs;

import org.junit.Test;

/**
 * @author: lixinghao
 * @date: 2020/4/20 2:24 下午
 * @Description: numIslands
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 */
public class NumIslands {
    @Test
    public void fun() {
        System.out.println(numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        }));
    }

    /**
     * 题目参考weiwei大佬的dfs完成
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        if (rows <= 0) {
            return 0;
        }
        int cols = grid[0].length;
        boolean[][] flag = new boolean[rows][cols];
        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!flag[row][col] && grid[row][col] == '1') {
                    // 此时应该去dfs
                    count++;
                    dfs(row, col, flag, rows, cols, grid);
                }
            }
        }

        return count;
    }

    private void dfs(int x, int y, boolean[][] flag, int rows, int cols, char[][] grid) {
        int[] distanceX = {1, -1, 0, 0};
        int[] distanceY = {0, 0, 1, -1};
        flag[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int pointX = distanceX[i] + x;
            int pointY = distanceY[i] + y;
            if (inArea(pointX, pointY, rows, cols) && !flag[pointX][pointY] && grid[pointX][pointY] == '1') {
                dfs(pointX, pointY, flag, rows, cols, grid);
            }
        }
    }

    private boolean inArea(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
