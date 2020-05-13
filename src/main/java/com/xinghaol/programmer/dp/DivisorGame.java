package com.xinghaol.programmer.dp;

/**
 * @author: lixinghao
 * @date: 2020/5/11 11:07 上午
 * @Description: 1025. 除数博弈
 */
public class DivisorGame {
    /**
     * dp[]代表一个长度为n+1的数组，如果 dp[n-x]为false,则Alice会减去x,即 Bob==dp[n-x]==false, Alice胜。
     * 否则Alice输，因为不管x是多少，dp[n-x]为true, 则dp[n]==Alice==false.
     *
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        if (N == 1) {
            return false;
        }
        if (N == 2) {
            return true;
        }
        boolean[] dp = new boolean[N + 1];
        for (int i = 3; i <= N; i++) {
            dp[i] = false;
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[N];
    }

    /**
     * 归纳法基本思路：
     * <p>
     * 最终结果应该是占到 2 的赢，占到 1 的输；
     * <p>
     * 若当前为奇数，奇数的约数只能是奇数或者 1，因此下一个一定是偶数；
     * <p>
     * 若当前为偶数， 偶数的约数可以是奇数可以是偶数也可以是 1，因此直接减 1，则下一个是奇数；
     * <p>
     * 因此，奇则输，偶则赢。直接:
     *
     * @param N
     * @return
     */
    public boolean divisorGame2(int N) {
        return N % 2 == 0;
    }
}
