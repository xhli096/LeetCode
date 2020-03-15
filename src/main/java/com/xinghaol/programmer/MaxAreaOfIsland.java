package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/15 1:47 下午
 * @Description: 岛屿的最大面积
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 * <p>
 * 示例 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 * <p>
 * 示例 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * <p>
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 * <p>
 * 深度优先遍历，在一个为1的位置，向四周去遍历，找到其相邻的最大值
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        boolean[][] flag = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j, flag));
                }
            }
        }

        return max;
    }

    /**
     * 需要设置flag变量组，处理过一个位置的元素后，不在处理他
     *
     * @param grid
     * @param row
     * @param col
     * @param flag
     * @return
     */
    private int dfs(int[][] grid, int row, int col, boolean[][] flag) {
        // 递归的结束条件，行、列边界值，当前元素为0
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0 || flag[row][col]) {
            return 0;
        }
        int current = 1;
        // 其周围递归是不在处理他
        flag[row][col] = true;
        current += dfs(grid, row + 1, col, flag);
        current += dfs(grid, row - 1, col, flag);
        current += dfs(grid, row, col + 1, flag);
        current += dfs(grid, row, col - 1, flag);

        return current;
    }

}
