package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/25 6:01 下午
 * @Description: 三维形体的表面积
 * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
 * <p>
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 * <p>
 * 请你返回最终形体的表面积。
 * <p>
 * 示例 1：
 * 输入：[[2]]
 * 输出：10
 * 示例 2：
 * 输入：[[1,2],[3,4]]
 * 输出：34
 * 示例 3：
 * 输入：[[1,0],[0,2]]
 * 输出：16
 * 示例 4：
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 * 示例 5：
 * 输入：[[2,2,2],[2,1,2],[2,2,2]]
 * 输出：46
 * 提示：
 * 1 <= N <= 50
 * 0 <= grid[i][j] <= 50
 */
public class SurfaceArea {
    public int surfaceArea(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        //统计所有的立方体数量
        int blocks = 0;
        //统计有多少个面被其他面盖住，那么就在所有的立方体的表面积上减去被盖住的面数×2（因为盖住一个面需要另一个面来盖，所以会损失2个面）；
        int cover = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                blocks += grid[i][j];
                //这个是统计当前格子中因为堆叠而盖住了几个面
                // 计算被挡住的上面和下面
                cover += grid[i][j] > 1 ? grid[i][j] - 1 : 0;
                if (i > 0) {
                    //看看上一行同一列盖住了多少个面，此处计算的是被挡住的前面和后面
                    cover += Math.min(grid[i - 1][j], grid[i][j]);
                }
                if (j > 0) {
                    //看看同一行前一列盖住了几个面，此处计算的是被挡住的左面和后面
                    cover += Math.min(grid[i][j - 1], grid[i][j]);
                }
            }
        }
        return blocks * 6 - cover * 2;
    }
}
