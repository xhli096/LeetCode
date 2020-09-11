package com.xinghaol.programmer.dfs;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/8/27 10:22 上午
 * @Description: 332. 重新安排行程
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 * 说明:
 * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 * 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。
 * 示例 1:
 * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * 示例 2:
 * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 */
public class FindItinerary {
    /**
     * 深度优先遍历，题目保证一定有一条正确的路径
     *
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(tickets)) {
            return result;
        }
        // 邻接表
        Map<String, List<String>> map = new HashMap<>();

        for (List<String> ticket : tickets) {
            List<String> edge = map.computeIfAbsent(ticket.get(0), k -> Lists.newArrayList());
            edge.add(ticket.get(1));
        }
        // 每个城市list都按字母顺序排一下序
        for (String city : map.keySet()) {
            Collections.sort(map.get(city));
        }
        System.out.println(JSON.toJSONString(map));
        dfs(map, result, "JFK");

        return result;
    }

    private void dfs(Map<String, List<String>> map, List<String> result, String start) {
        List<String> edge = map.get(start);

        while (edge != null && edge.size() > 0) {
            String p = edge.remove(0);
            dfs(map, result, p);
        }

        result.add(0, start);
    }

    public static void main(String[] args) {
        List<List<String>> params = Lists.newArrayList();
        params.add(Lists.newArrayList("MUC", "LHR"));
        params.add(Lists.newArrayList("JFK", "MUC"));
        params.add(Lists.newArrayList("SFO", "SJC"));
        params.add(Lists.newArrayList("LHR", "SFO"));

        FindItinerary findItinerary = new FindItinerary();
        List<String> itinerary = findItinerary.findItinerary(params);
        System.out.println(JSON.toJSONString(itinerary));
    }
}
