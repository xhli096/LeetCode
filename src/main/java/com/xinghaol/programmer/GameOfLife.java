package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/4/2 10:47 下午
 * @Description: 生命游戏
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * <p>
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * <p>
 * 示例：
 * 输入：
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出：
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * <p>
 * 进阶：
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 */
public class GameOfLife {
    /**
     * 初级解法，复制一个数组。修改后，会影响周围位置的元素更新，
     * 所以不能修改原数组，需要根据原数组去确定更新的状态。
     * 复杂度分析
     *
     * 时间复杂度：O(mn)O(mn)，其中 mm 和 nn 分别为 board 的行数和列数。
     *
     * 空间复杂度：O(mn)O(mn)，为复制数组占用的空间。
     *
     */
    public void gameOfLife(int[][] board) {
        // 复制一份数组
        int[][] copyBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        int rows = board.length;
        int cols = board[0].length;
        int[] neighbors = {0, 1, -1};

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // 用于统计相邻的位置存活的个数
                int liveNeighbors = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        // 不是0，则需要移动位置
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = row + neighbors[i];
                            int c = col + neighbors[j];

                            // 查看这个相邻的位置是否有存活的细胞
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && copyBoard[r][c] == 1) {
                                liveNeighbors++;
                            }
                        }
                    }
                }
                // 退出for循环后，已经完成周围存活细胞数的统计
                Object object = new Object();
                // 规则1 规则3
                if ((copyBoard[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = 0;
                }
                if (copyBoard[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 1;
                }
            }
        }
    }
}
