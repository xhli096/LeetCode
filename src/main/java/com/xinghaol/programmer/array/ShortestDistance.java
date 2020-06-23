package com.xinghaol.programmer.array;

/**
 * @author: lixinghao
 * @date: 2020/6/22 6:17 下午
 * @Description: 505. 迷宫 II
 */
public class ShortestDistance {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        int cols = maze[0].length;
        int[] distanceX = {1, -1, 0, 0};
        int[] distanceY = {0, 0, 1, -1};
        int min = Integer.MAX_VALUE;

        int[][] distance = new int[rows][cols];

        return 0;
    }

}
