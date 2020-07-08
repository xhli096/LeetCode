package com.xinghaol.programmer.dp;

/**
 * @author: lixinghao
 * @date: 2020/7/6 6:15 下午
 * @Description:
 */
public class UniquePathsWithObstacles {
    private int count = 0;

    /**
     * dfs解法超时
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        if (row == 0) {
            return 0;
        }
        dfs(0, 0, row, col, obstacleGrid);

        return count;
    }

    private void dfs(int x, int y, int row, int col, int[][] obstacleGrid) {
        if (x >= row || y >= col || obstacleGrid[x][y] == 1) {
            return;
        }
        if (x == row - 1 && y == col - 1) {
            count++;
        }
        dfs(x + 1, y, row, col, obstacleGrid);
        dfs(x, y + 1, row, col, obstacleGrid);
    }

    public static void main(String[] args) {
        UniquePathsWithObstacles uniquePathsWithObstacles = new UniquePathsWithObstacles();
        int paths = uniquePathsWithObstacles.uniquePathsWithObstacles(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        });
        System.out.println("paths: " + paths);
    }

    /**
     * 动态规划解法
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < col && obstacleGrid[0][i] == 0; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[row - 1][col - 1];
    }
}
