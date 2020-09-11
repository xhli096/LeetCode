package com.xinghaol.programmer.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/9/5 11:14 下午
 * @Description: 60. 第k个排列
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class GetPermutation {
    /**
     * 回溯算法，全排列 -> 超时了
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation2(int n, int k) {
        if (n <= 0) {
            return "";
        }
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        boolean[] used = new boolean[n];
        List<String> result = new ArrayList<>();
        dfs2(nums, nums.length, 0, used, "", result);

        return result.get(k - 1);
    }

    private void dfs2(int[] nums, int length, int depth, boolean[] used, String path, List<String> result) {
        if (depth == length) {
            result.add(path);
            return;
        }

        for (int i = 0; i < length; i++) {
            if (!used[i]) {
                path += nums[i];
                used[i] = true;
                dfs2(nums, length, depth + 1, used, path, result);

                used[i] = false;
                path = path.substring(0, path.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        GetPermutation getPermutation = new GetPermutation();
        String permutation = getPermutation.getPermutation(3, 3);
        System.out.println(permutation);
    }

    /**
     * 全排列 + 剪枝
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        boolean[] used = new boolean[n + 1];
        /**
         * 阶乘数组，题目说明n在1~9之间，则可以提前计算好相应的阶乘
         */
        int[] factorial = calculateFactorial(n);
        StringBuilder path = new StringBuilder();
        dfs(0, path, n, factorial, used, k);
        return path.toString();
    }

    /**
     * @param i    index 在这一步之前已经选择了几个数字，其值恰好等于这一步需要确定的下标位置
     * @param path
     */
    private void dfs(int index, StringBuilder path, int n, int[] factorial, boolean[] used, int k) {
        if (index == n) {
            return;
        }
        // 计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1
        int cnt = factorial[n - 1 - index];

        for (int i = 1; i <= n; i++) {
            // 当前数字使用过了
            if (used[i]) {
                continue;
            }
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            path.append(i);
            used[i] = true;
            dfs(index + 1, path, n, factorial, used, k);

            return;
        }
    }


    /**
     * 计算阶乘数组，注意0的阶乘为1
     *
     * @param n
     */
    private int[] calculateFactorial(int n) {
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        return factorial;
    }
}
