package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/3/26 10:53 上午
 * @Description: 车的可用捕获量
 * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
 * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
 * 返回车能够在一次移动中捕获到的卒的数量。
 *
 * 提示：
 *
 * board.length == board[i].length == 8
 * board[i][j] 可以是 'R'，'.'，'B' 或 'p'
 * 只有一个格子上存在 board[i][j] == 'R'
 *
 * https://leetcode-cn.com/problems/available-captures-for-rook/
 */
public class NumRookCaptures {
    public int numRookCaptures(char[][] board) {
        int row = 0;
        int col = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ('R' == (board[i][j])) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        int result = 0;
        for (int i = row - 1; i >= 0; i--) {
            if ('B' == board[i][col]) {
                break;
            }
            if ('p' == board[i][col]) {
                result += 1;
                break;
            }
        }
        for (int i = row + 1; i < board.length; i++) {
            if ('B' == board[i][col]) {
                break;
            }
            if ('p' == board[i][col]) {
                result += 1;
                break;
            }
        }
        for (int i = col - 1; i >= 0; i--) {
            if ('B' == board[row][i]) {
                break;
            }
            if ('p' == board[row][i]) {
                result += 1;
                break;
            }
        }
        for (int i = col + 1; i < board[0].length; i++) {
            if ('B' == board[row][i]) {
                break;
            }
            if ('p' == board[row][i]) {
                result++;
                break;
            }
        }

        return result;
    }
}
