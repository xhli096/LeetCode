package com.xinghaol.programmer.bfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

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

    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (rows <= 0) {
            return 0;
        }
        // 记录是否某个元素已经访问过了
        boolean[][] flag = new boolean[rows][cols];
        // 记录岛屿的个数
        int count = 0;

        int[] distanceX = {1, -1, 0, 0};
        int[] distanceY = {0, 0, 1, -1};
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!flag[row][col] && grid[row][col] == '1') {
                    count++;
                    // 用于记录周围的是否被访问过的1，如果是1的话则加入队列中
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{row, col});
                    while (!queue.isEmpty()) {
                        int[] point = queue.poll();
                        for (int i = 0; i < 4; i++) {
                            int x = point[0] + distanceX[i];
                            int y = point[1] + distanceY[i];
                            // 防止下标越界
                            if (x >= 0 && y >= 0 && x < rows && y < cols && !flag[x][y] && grid[x][y] == '1') {
                                flag[x][y] = true;
                                queue.offer(new int[]{x, y});
                            }
                        }
                    }
                }
            }
        }

        return count;
    }
}
