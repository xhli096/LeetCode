package com.xinghaol.programmer.array;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/7/14 3:04 下午
 * @Description: 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 例如，给定三角形：
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 */
public class MinimumTotal {
    /**
     * 若定义 f(i, j)f(i,j) 为 (i, j)(i,j) 点到底边的最小路径和，则易知递归求解式为:
     * f(i, j) = min(f(i + 1, j), f(i + 1, j + 1)) + triangle[i][j]f(i,j)=min(f(i+1,j),f(i+1,j+1))+triangle[i][j]
     * <p>
     * 从最后一行开始向前递推
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];

        // n-1为最后一行的下标值
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        System.out.println(JSON.toJSONString(dp));
        return dp[0][0];
    }

    /**
     * 错误的解法，一维数组无法得到所有的情况，上一层的最小值不一定是在全局的最小值
     *
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int length = triangle.size();
        if (length <= 0) {
            return 0;
        }
        int[] dp = new int[length + 1];
        dp[1] = triangle.get(0).get(0);

        int current = 0;
        for (int i = 1; i < length; i++) {
            int min = Math.min(triangle.get(i).get(current), triangle.get(i).get(current + 1));
            current = min == triangle.get(i).get(current) ? current : current + 1;
            dp[i + 1] = dp[i] + min;
        }

        return dp[length];
    }

    public static void main(String[] args) {
        MinimumTotal minimumTotal = new MinimumTotal();
        List<List<Integer>> list = new ArrayList<>();
        list.add(Lists.newArrayList(-1));
        list.add(Lists.newArrayList(2, 3));
        list.add(Lists.newArrayList(1, -1, -3));
        //list.add(Lists.newArrayList(4,1,8,3));
        int result = minimumTotal.minimumTotal(list);
        System.out.println(result);
    }
}
