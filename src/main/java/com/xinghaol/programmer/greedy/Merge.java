package com.xinghaol.programmer.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/4/16 4:12 下午
 * @Description: 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Merge {
    public int[][] merage2(int[][] intervals) {
        if (intervals.length == 0) {
            return intervals;
        }
        List<int[]> list = new ArrayList<>();
        int[] startArray = new int[intervals.length];
        int[] endArray = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            startArray[i] = intervals[i][0];
            endArray[i] = intervals[i][1];
        }
        Arrays.sort(startArray);
        Arrays.sort(endArray);

        int[] current = {startArray[0], endArray[0]};
        for (int i = 1; i < startArray.length; i++) {
            if (current[1] < startArray[i]) {
                list.add(current);
                current = new int[]{startArray[i], endArray[i]};
            } else {
                current[1] = endArray[i];
            }
        }
        list.add(current);
        intervals = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            intervals[i] = list.get(i);
        }
        return intervals;
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 0) {
            return new int[][]{};
        }
        // 先按第一个元素的升序排个序
        Arrays.sort(intervals, Comparator.comparing(o -> o[0]));

        int[] begin = new int[intervals.length];
        int[] end = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            int[] current = intervals[i];
            begin[i] = current[0];
            end[i] = current[1];
        }
        // 记录需要和前面区间合并的index
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{begin[0], end[0]});
        for (int i = 1; i < begin.length; i++) {
            // 此时两个区间应该合并
            // [1,2,8,15]
            // [3,6,10,18]
            int[] current = stack.pop();
            // 此时应该合并
            if (begin[i] <= current[1]) {
                current[1] = Math.max(end[i], current[1]);
                stack.push(current);
            } else {
                stack.push(current);
                stack.push(new int[]{begin[i], end[i]});
            }
        }

        List<int[]> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        int[][] result = new int[list.size()][2];
        int index = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            result[index] = list.get(i);
            index++;
        }

        return result;
    }
}
