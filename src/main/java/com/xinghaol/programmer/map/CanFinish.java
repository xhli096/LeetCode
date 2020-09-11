package com.xinghaol.programmer.map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/8/4 5:28 下午
 * @Description: 207. 课程表
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * <p>
 * 提示：
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 */
public class CanFinish {
    /**
     * 入度表（广度优先遍历）
     * 算法流程：
     * 1、统计课程安排图中每个节点的入度，生成 入度表 indegrees。
     * 2、借助一个队列 queue，将所有入度为 0 的节点入队。
     * 3、当 queue 非空时，依次将队首节点出队，在课程安排图中删除此节点 pre：
     * 3.1、并不是真正从邻接表中删除此节点 pre，而是将此节点对应所有邻接节点 cur 的入度 −1，即 indegrees[cur] -= 1。
     * 3.2、当入度 −1后邻接节点 cur 的入度为 0，说明 cur 所有的前驱节点已经被 “删除”，此时将 cur 入队。
     * 4、在每次 pre 出队时，执行 numCourses--；
     * 4.1、若整个课程安排图是有向无环图（即可以安排），则所有节点一定都入队并出队过，即完成拓扑排序。换个角度说，若课程安排图中存在环，一定有节点的入度始终不为 00。
     * 4.2、因此，拓扑排序出队次数等于课程个数，返回 numCourses == 0 判断课程是否可以成功安排。
     * <p>
     * 复杂度分析：
     * 时间复杂度 O(N + M)O(N+M)： 遍历一个图需要访问所有节点和所有临边，NN 和 MM 分别为节点数量和临边数量；
     * 空间复杂度 O(N + M)O(N+M)： 为建立邻接表所需额外空间，adjacency 长度为 NN ，并存储 MM 条临边的数据。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        // 统计每个课程节点的入读，生成入度表。每个节点的下标为1的为先觉课程
        for (int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        // 如果入度为0，则为所有课程的先决课程
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            // 弹出每一个入度为0的先觉课程
            int pre = queue.poll();
            numCourses--;
            // 得到依赖该课程的所有课程
            for (int cur : adjacency.get(pre)) {
                // 如果减去这个依赖课程，不在依赖其他课程，就可以加入队列了
                if (--indegrees[cur] == 0) {
                    queue.add(cur);
                }
            }
        }

        return numCourses == 0;
    }

    /**
     * 深度优先遍历
     * 原理是通过 DFS 判断图中是否有环。
     * <p>
     * 算法流程：
     * 1、借助一个标志列表 flags，用于判断每个节点 i （课程）的状态：
     * 1、1、未被 DFS 访问：i == 0；
     * 1、2、已被其他节点启动的 DFS 访问：i == -1；
     * 1、3、已被当前节点启动的 DFS 访问：i == 1。
     * 2、对 numCourses 个节点依次执行 DFS，判断每个节点起步 DFS 是否存在环，若存在环直接返回 False。DFS 流程；
     * 2.1、终止条件：
     * 2.1.1、当 flag[i] == -1，说明当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，直接返回 True。
     * 2.1.2、当 flag[i] == 1，说明在本轮 DFS 搜索中节点 i 被第 22 次访问，即 课程安排图有环 ，直接返回 False。
     * 2.2、将当前访问节点 i 对应 flag[i] 置 11，即标记其被本轮 DFS 访问过；
     * 2.3、递归访问当前节点 i 的所有邻接节点 j，当发现环直接返回 FalseFalse；
     * 2.4、当前节点所有邻接节点已被遍历，并没有发现环，则将当前节点 flag 置为 -1 并返回 True。、
     * 3、若整个图 DFS 结束并未发现环，返回 True
     * <p>
     * 复杂度分析：
     * 时间复杂度 O(N + M)O(N+M)： 遍历一个图需要访问所有节点和所有临边，NN 和 MM 分别为节点数量和临边数量；
     * 空间复杂度 O(N + M)O(N+M)： 为建立邻接表所需额外空间，adjacency 长度为 NN ，并存储 MM 条临边的数据。
     * 。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        int[] flags = new int[numCourses];
        for (int[] cp : prerequisites) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        // 每一轮扫过
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adjacency, flags, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        // 在本轮中发现某个flag已经被选择过了，所以就出现了个环。就不行了
        if (flags[i] == 1) {
            return false;
        }
        if (flags[i] == -1) {
            return true;
        }
        flags[i] = 1;
        for (Integer j : adjacency.get(i)) {
            if (!dfs(adjacency, flags, j)) {
                return false;
            }
        }
        flags[i] = -1;
        return true;
    }
}
