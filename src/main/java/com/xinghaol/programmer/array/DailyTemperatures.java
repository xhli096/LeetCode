package com.xinghaol.programmer.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: lixinghao
 * @date: 2020/6/11 5:08 下午
 * @Description: 739. 每日温度
 * https://leetcode-cn.com/problems/daily-temperatures/
 */
public class DailyTemperatures {
    /**
     * 暴力解法，居然可以通过。
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        if (length == 0) {
            return new int[]{};
        }
        int[] result = new int[length];
        result[length - 1] = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (T[j] > T[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 单调栈
     * 遍历每日温度，维护一个单调栈：
     * - 若栈为空或者当日温度小于等于栈顶温度，则直接入栈；从栈底到栈顶温度依次减小
     * - 反之，若当日温度大于栈顶温度，说明栈顶元素的升温日已经找到了，则将栈顶元素出栈，计算其与当日相差的天数即可。
     * 题目要求是升温的天数，需要计算下标的值，所以栈中应该存储下标，而非温度。
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures2(int[] T) {
        // 声明一个栈
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            // 栈中不为空，且当前栈顶元素小于当前元素，则说明找到了下一个升温日
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }

        return result;
    }
}
