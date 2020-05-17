package com.xinghaol.programmer.map;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/5/17 2:27 下午
 * @Description: 210. 课程表 II
 * 一道典型的「拓扑排序」，属于图的问题。
 * <p>
 * 官方题解：https://leetcode-cn.com/problems/course-schedule-ii/solution/ke-cheng-biao-ii-by-leetcode-solution/
 */
public class FindOrder {
    /**
     * 错误的解法
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[]{};
        }
        // 空数组的情况
        if (prerequisites.length == 0) {
            int[] result = new int[numCourses];
            int index = 0;
            for (int i = numCourses - 1; i >= 0; i--) {
                result[index++] = i;
            }

            return result;
        }

        Map<Integer, List<Integer>> listMap = new HashMap<>();
        boolean[] flag = new boolean[numCourses];

        // 第二维数组中，下标为1的为先决课程。下标为0的为要学的课程。
        for (int i = 0; i < prerequisites.length; i++) {
            int target = prerequisites[i][0];
            int pre = prerequisites[i][1];
            List<Integer> list = listMap.get(target);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(pre);
            listMap.put(target, list);
        }

        int max = Integer.MIN_VALUE;
        for (Integer key : listMap.keySet()) {
            max = key > max ? key : max;
        }
        List<Integer> target = listMap.get(max);
        flag[max] = true;
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < target.size(); i++) {
            Integer courseNumber = target.get(i);
            deal(courseNumber, flag, listMap, l);
        }
        l.add(max);
        int[] result = new int[l.size()];
        for (int i = 0; i < l.size(); i++) {
            result[i] = l.get(i);
        }

        return result;
    }

    private void deal(int courseNumber, boolean[] flag, Map<Integer, List<Integer>> listMap, List<Integer> l) {
        if (flag[courseNumber]) {
            return;
        }
        List<Integer> list = listMap.get(courseNumber);
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            int subCourseNumber = list.get(i);
            if (!flag[subCourseNumber]) {
                flag[subCourseNumber] = true;
                l.add(subCourseNumber);
            }
            deal(subCourseNumber, flag, listMap, l);
        }
        l.add(courseNumber);
        flag[courseNumber] = true;
    }

    public static void main(String[] args) {
        FindOrder findOrder = new FindOrder();
        int[] order = findOrder.findOrder2(4, new int[][]{
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        });

        System.out.println(JSON.toJSONString(order));
    }

    /**
     * 标记每个节点的状态，0=未搜索，1=搜索中，2=已完成
     */
    private List<Integer> visited;
    /**
     * 判断图中是否有环
     */
    private boolean invalid;
    /**
     * 充当栈的角色
     */
    private int[] result;
    /**
     * 用于存储有向图
     */
    private List<List<Integer>> edges;

    private int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        // 构建有向图
        for (int i = 0; i < prerequisites.length; i++) {
            edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        result = new int[numCourses];
        index = numCourses - 1;
        visited = new ArrayList<>(numCourses);

        for (int i = 0; i < numCourses; i++) {
            if (visited.get(i) == 0) {
                dfs(i);
            }
        }
        if (invalid) {
            return new int[0];
        }

        return result;
    }

    public void dfs(int u) {
        visited.set(u, 1);
        // 处理从当前节点可以沿有向边访问到的节点
        for (int v : edges.get(u)) {
            if (visited.get(v) == 0) {
                dfs(v);
                if (invalid) {
                    return;
                }
            } else if (visited.get(v) == 1) {
                // 到这个分支来，就说明遍历到了其他节点也正在遍历的节点，就说明这个图中存在环。
                invalid = true;
            }
        }

        visited.set(u, 2);
        result[index--] = u;
    }
}
