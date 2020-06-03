package com.xinghaol.programmer.traceback;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/4/25 1:30 下午
 * @Description:
 */
public class Permute {
    /**
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        int length = nums.length;
        // 保存所有可能全排列
        List<List<Integer>> result = new ArrayList<>();
        if (length == 0) {
            return result;
        }

        boolean[] used = new boolean[length];
        List<Integer> path = new ArrayList<>();
        dfs(nums, length, 0, path, used, result);

        return result;
    }

    /**
     * 按树形结构来遍历
     *
     * @param nums
     * @param length
     * @param depth  当前遍历到的属性的层次数
     * @param path
     * @param used
     * @param result
     */
    private void dfs(int[] nums, int length, int depth, List<Integer> path, boolean[] used, List<List<Integer>> result) {
        if (depth == length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;

                dfs(nums, length, depth + 1, path, used, result);
                // 注意这里是回溯过程，从深层次节点到浅层次节点的过程，代码是和递归形式是对称的。
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Permute permute = new Permute();
        List<List<Integer>> result = permute.permute(new int[]{1, 2, 3});
        System.out.println(JSON.toJSONString(result));
    }
}
