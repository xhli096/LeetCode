package com.xinghaol.programmer.dp;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/3 10:17 下午
 * @Description:
 */
public class New21Game {
    /**
     * 在[1,W]之间取到每一个数字的概率都是1/W；
     * 当其手上的点数严格小于K的时候，就继续抽；否则则停止抽牌；
     * 停止抽牌后，如果手上的点数<=N，就获胜了，否则就失败；
     * <p>
     * 假设dp[x]为她手上牌面为x时，能获胜的概率，那么这个概率应该是：
     * dp[x]=1/w * (dp[x+1]+dp[x+2]+dp[x+3]...+dp[x+w])
     * <p>
     * 小于等于N时，获胜的概率为1；大于N的时候，获胜的概率是0。可以直接初始化
     *
     * @param N
     * @param K
     * @param W
     * @return
     */
    public double new21Game(int N, int K, int W) {
        double[] dp = new double[ K + W];
        // 用于记录当前 k~k+w中小于N的概率值。
        double s = 0;
        for (int i = K; i < K + W; i++) {
            if (i <= N) {
                dp[i] = 1.0;
            } else {
                dp[i] = 0.0;
            }
            s += dp[i];
        }
        // 当手中牌为K-1时，最后一次可以抽牌的机会
        for (int i = K - 1; i >= 0; i--) {
            dp[i] = (s + 0.0) / W;
            s = s - dp[i + W] + dp[i];
        }

        return dp[0];
    }

    public static void main(String[] args) {
        New21Game new21Game = new New21Game();
        double result = new21Game.new21Game(28, 17, 10);
        System.out.println(result);
    }
}
