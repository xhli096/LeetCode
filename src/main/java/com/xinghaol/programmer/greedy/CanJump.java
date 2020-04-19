package com.xinghaol.programmer.greedy;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/4/17 4:37 下午
 * @Description: 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class CanJump {
    @Test
    public void fun() {
        boolean a = canJump(new int[]{2, 4, 4, 2, 0, 3, 1, 4, 1, 3, 2, 0, 1, 1, 2, 1, 0, 1, 4});
        System.out.println(a);
        boolean b = canJump2(new int[]{2, 4, 4, 2, 0, 3, 1, 4, 1, 3, 2, 0, 1, 1, 2, 1, 0, 1, 4});
        System.out.println(b);
    }

    /**
     * 这是一个贪心算法的问题
     * 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
     * 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
     * 如果可以一直跳到最后，就成功了。
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i <= max) {
                // 当前位置i+nums[i]即为当前位置可以到达的最远的距离
                max = Math.max(max, i + nums[i]);
            }
            if (max >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 这样处理应该是对的，但是超时了
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int index = 0;
        int lastIndex = nums.length - 1;
        //System.out.println(lastIndex);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(index);
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            //System.out.println("current:" + current + " " + JSON.toJSONString(queue));
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int ii = queue.poll();
                int num = nums[ii];
                //System.out.println("num:" + num);
                for (int j = 0; j <= num; j++) {
                    int i1 = ii + j;
                    //System.out.println("i1:" + i1);
                    if (i1 == lastIndex) {
                        return true;
                    } else if (i1 < lastIndex) {
                        queue.offer(i1);
                    }
                }
            }
        }

        return false;
    }
}
