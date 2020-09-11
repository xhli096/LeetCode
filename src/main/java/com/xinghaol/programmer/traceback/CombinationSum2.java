package com.xinghaol.programmer.traceback;

import com.alibaba.fastjson.JSON;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/9/10 11:07 下午
 * @Description: 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (target == 0 || candidates.length <= 0) {
            return new ArrayList<>();
        }
        // 首先对数据进行大小的排序
        Arrays.sort(candidates);
        // [10,1,2,7,6,1,5] => [1,1,2,5,6,7,10]
        if (candidates[0] > target) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>(candidates.length);
        fun(candidates, target, 0, candidates.length, result, path);

        return result;
    }

    private void fun(int[] candidates, int target, int begin, int len, List<List<Integer>> result, Deque<Integer> path) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 由于对candidates已经排序，所以target - candidates[i] < 0后，那target减去candidates[i+1]、candidates[i+2]都是小于0
            // 直接break掉，属于大剪枝。直接舍弃后面
            if (target - candidates[i] < 0) {
                break;
            }
            // 不是第一项，但是和前面的数字相同，在本层不在选择，但是下一层可以继续选择，属于小剪枝
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.addLast(candidates[i]);
            // 调试语句 ①
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            fun(candidates, target - candidates[i], i + 1, len, result, path);

            path.removeLast();
            // 调试语句 ②
            System.out.println("递归之后 => " + path + "，剩余 = " + (target - candidates[i]));
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        CombinationSum2 combinationSum2 = new CombinationSum2();
        List<List<Integer>> list = combinationSum2.combinationSum2(candidates, target);
        System.out.println(JSON.toJSONString(list));
    }
}
