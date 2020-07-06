package com.xinghaol.programmer.dp;

/**
 * @author: lixinghao
 * @date: 2020/7/3 12:54 下午
 * @Description: 276. 栅栏涂色
 * https://leetcode-cn.com/problems/paint-fence/
 * 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，每个栅栏柱可以用其中一种颜色进行上色。
 *
 * 你需要给所有栅栏柱上色，并且保证其中相邻的栅栏柱 最多连续两个 颜色相同。然后，返回所有有效涂色的方案数。
 *
 * 注意:
 * n 和 k 均为非负的整数。
 *
 * 示例:
 *
 * 输入: n = 3，k = 2
 * 输出: 6
 * 解析: 用 c1 表示颜色 1，c2 表示颜色 2，所有可能的涂色方案有:
 *
 *             柱 1    柱 2   柱 3
 *  -----      -----  -----  -----
 *    1         c1     c1     c2
 *    2         c1     c2     c1
 *    3         c1     c2     c2
 *    4         c2     c1     c1 
 *    5         c2     c1     c2
 *    6         c2     c2     c1
 */
public class NumWays {
    /**
     * 动态规划思想，手工推到
     * 当第i个与第i-1个相同时，第i-2个位置就有k-1中涂法；当第i个与第i-1个不同时，第i-1个位置就有k-1种涂法。
     * 第i个位置的即为两者的加和。
     *
     * dp[1] = k；可以选择k中涂法中的一种
     * dp[2] = k * k，当前也有k种涂法，加上和前面相同，即为k*k
     *
     * @param n
     * @param k
     * @return
     */
    public int numWays(int n, int k) {
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[i] = k;
            } else if (i == 2) {
                dp[i] = k * k;
            } else {
                dp[i] = dp[i-1] * (k-1) + dp[i-2] * (k-1);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        NumWays numWays = new NumWays();
        int ways = numWays.numWays(4, 2);
        System.out.println(ways);
    }
}
