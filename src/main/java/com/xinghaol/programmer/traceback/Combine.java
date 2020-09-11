package com.xinghaol.programmer.traceback;

import com.alibaba.fastjson.JSON;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/9/8 10:45 下午
 * @Description: 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Combine {
    /**
     * 这一版代码有点潮湿，继续改一下
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine2(int n, int k) {
        if (k > n) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        if (k == n) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                list.add(i);
            }
            result.add(list);
            return result;
        }

        boolean[] used = new boolean[n + 1];

        dfs2(result, new ArrayList<>(), n, k, used);
        return result;
    }

    private void dfs2(List<List<Integer>> result, List<Integer> list, int n, int k, boolean[] used) {
        if (list.size() == k) {
            List<Integer> l = new ArrayList<>();
            l.addAll(list);
            Collections.sort(l);
            if (!result.contains(l)) {
                result.add(l);
            }
            return;
        }
        for (int i = 1; i <= n; i++) {
            //if (!used[i]) {
            list.add(i);
            //used[i] = true;

            dfs2(result, list, n, k, used);
            //used[i] = false;
            list.remove(list.size() - 1);
            //}
        }
    }

    public static void main(String[] args) {
        Combine combine = new Combine();
        List<List<Integer>> combine1 = combine.combine(13, 10);
        System.out.println(JSON.toJSONString(combine1));
    }

    /**
     * 参考liweiwei解法
     * 优化点：分析搜索起点的上界进行剪枝
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        if (k > n || k <= 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        // 从 1 开始是题目的设定
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, result);

        return result;
    }

    /**
     * 例如：n = 6 ，k = 4。
     * <p>
     * path.size() == 1 的时候，接下来要选择 33 个数，搜索起点最大是 44，最后一个被选的组合是 [4, 5, 6]；
     * path.size() == 2 的时候，接下来要选择 22 个数，搜索起点最大是 55，最后一个被选的组合是 [5, 6]；
     * path.size() == 3 的时候，接下来要选择 11 个数，搜索起点最大是 66，最后一个被选的组合是 [6]；
     * <p>
     * 再如：n = 15 ，k = 4。
     * path.size() == 1 的时候，接下来要选择 33 个数，搜索起点最大是 1313，最后一个被选的是 [13, 14, 15]；
     * path.size() == 2 的时候，接下来要选择 22 个数，搜索起点最大是 1414，最后一个被选的是 [14, 15]；
     * path.size() == 3 的时候，接下来要选择 11 个数，搜索起点最大是 1515，最后一个被选的是 [15]；
     * <p>
     * 可以归纳出：
     * 搜索起点的上界 + 接下来要选择的元素个数 - 1 = n
     * 其中，接下来要选择的元素个数 = k - path.size()，整理得到：
     * <p>
     * 搜索起点的上界 = n - (k - path.size()) + 1
     * 所以，我们的剪枝过程就是：把 i <= n 改成 i <= n - (k - path.size()) + 1 ：
     *
     * @param n
     * @param k
     * @param begin
     * @param path
     * @param res
     */
    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 只有这里 i <= n - (k - path.size()) + 1 与参考代码 1 不同
        // for (int i = begin; i <= n - (k - path.size()) + 1; i++) {

        for (int i = begin; i <= n; i++) {
            path.addLast(i);
            System.out.println("path：" + JSON.toJSONString(path));
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs(n, k, i + 1, path, res);
            path.removeLast();
        }
    }
}
