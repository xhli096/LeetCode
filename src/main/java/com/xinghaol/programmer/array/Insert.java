package com.xinghaol.programmer.array;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/6/28 4:14 下午
 * @Description: 57. 插入区间
 * https://leetcode-cn.com/problems/insert-interval/
 * <p>
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 示例 2:
 * <p>
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class Insert {
    /**
     * 遍历，如果插入区间的起始值 > 遍历到的区间的末位置，则直接添加
     * 退出上面的循环后，说明遍历到intervals末尾或者有合并的区间
     * 如果存在合并区间，则合并，如果newInterval[1] >= intervals[index][0]，存在合并可能
     * 就合并，直到没有合并的，将剩余区间加入结果集中
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int index = 0;
        // 如果新插入的区间起始 > 遍历到的区间的末尾，则直接添加到
        while (index < intervals.length && newInterval[0] > intervals[index][1]) {
            res.add(intervals[index]);
            index++;
        }
        // 退出循环后，说明遍历到intervals末尾或者有可以合并的区间了
        int[] tmp = new int[]{newInterval[0], newInterval[1]};
        // 一直合并至没有重合
        while (index < intervals.length && newInterval[1] >= intervals[index][0]) {
            tmp[0] = Math.min(tmp[0], intervals[index][0]);
            tmp[1] = Math.max(tmp[1], intervals[index][1]);
            index++;
        }
        res.add(tmp);
        while (index < intervals.length) {
            res.add(intervals[index]);
            index++;
        }

        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Insert insert = new Insert();
        int[][] result = insert.insert(new int[][]{
                {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}
        }, new int[]{4, 8});
        System.out.println(JSON.toJSONString(result));
    }
}
