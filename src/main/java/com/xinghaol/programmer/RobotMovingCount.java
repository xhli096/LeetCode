package com.xinghaol.programmer;

/**
 * @author: lixinghao
 * @date: 2020/4/8 9:01 下午
 * @Description: 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，
 * 因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 */
public class RobotMovingCount {
    public static int movingCount(int m, int n, int k) {
        if (k <= 0) {
            return 1;
        }
        boolean[][] flag = new boolean[m][n];
        return getCount(0, 0, m, n, k, flag);
    }

    public static int getCount(int x, int y, int m, int n, int k, boolean[][] flag) {
        int result = 0;
        int sum = 0;

        if (x >= m || y >= n) {
            return 0;
        }
        if (!flag[x][y]) {
            sum += getValue(x);
            sum += getValue(y);
            if (sum <= k) {
                result = result + 1;
                result = result + getCount(x + 1, y, m, n, k, flag);
                result = result + getCount(x, y + 1, m, n, k, flag);
                flag[x][y] = true;
            }
        }

        return result;
    }

    public static int getValue(int value) {
        int result = 0;
        while (value != 0) {
            result = result + (value % 10);
            value /= 10;
        }
        return result;
    }
}
