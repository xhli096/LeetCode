package com.xinghaol.programmer.dfs;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/8/31 3:58 下午
 * @Description: 841. 钥匙和房间
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * 你可以自由地在房间之间来回走动。
 * 如果能进入每个房间返回 true，否则返回 false。
 * 示例 1：
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * 示例 2：
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * 提示：
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * 所有房间中的钥匙数量总计不超过 3000。
 */
public class CanVisitAllRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean result = false;
        if (rooms == null || rooms.isEmpty()) {
            return false;
        }
        boolean[] flag = new boolean[rooms.size()];
        flag[0] = true;
        boolean[] hasDeal = new boolean[rooms.size()];

        Queue<Integer> queue = new LinkedList<>();
        // 先把第0个房间加入进去
        queue.addAll(rooms.get(0));
        hasDeal[0] = true;

        while (!queue.isEmpty()) {
            Integer currentRoom = queue.poll();
            flag[currentRoom] = true;
            if (!hasDeal[currentRoom]) {
                List<Integer> list = rooms.get(currentRoom);
                queue.addAll(list);
                hasDeal[currentRoom] = true;
            }
            System.out.println("flag:" + Arrays.toString(flag));
            System.out.println("hasDeal:" + Arrays.toString(hasDeal));
            if (isTue(flag)) {
                result = true;
                break;
            }
        }

        if (isTue(flag)) {
            result = true;
        }

        return result;
    }

    private boolean isTue(boolean[] flag) {
        for (boolean current : flag) {
            if (!current) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> params = new ArrayList<>();
        List<Integer> list1 = Lists.newArrayList(1,3);
        List<Integer> list2 = Lists.newArrayList(3,0,1);
        List<Integer> list3 = Lists.newArrayList(2);
        List<Integer> list4 = Lists.newArrayList(0);
        params.add(list1);
        params.add(list2);
        params.add(list3);
        params.add(list4);

        CanVisitAllRooms canVisitAllRooms = new CanVisitAllRooms();
        boolean allRooms = canVisitAllRooms.canVisitAllRooms(params);
        System.out.println(allRooms);
    }

    private CanVisitAllRooms() {

    }
}
