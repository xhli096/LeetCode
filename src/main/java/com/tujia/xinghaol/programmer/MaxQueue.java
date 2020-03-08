package com.tujia.xinghaol.programmer;

import sun.security.timestamp.TSRequest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: lixinghao
 * @date: 2020/3/7 11:28 下午
 * @Description: 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 */
/*
准备两个队列，一个队列存放最大值，一个队列存放队列中的值。
queue中存放对应的队列值。help中存放最大值，每次入队列后，逐个比较，将最大值放到对手。pop时，需要判断是否弹出为当前最大值。
 */
public class MaxQueue {

    private Deque<Integer> queue;
    private Deque<Integer> help;

    public MaxQueue() {
        queue = new ArrayDeque<Integer>();
        help = new ArrayDeque<Integer>();
    }

    public int max_value() {
        return queue.isEmpty() ? -1 : help.peek();
    }

    public void push_back(int value) {
        queue.offer(value);
        while(!help.isEmpty() && value > help.peekLast()) {
            help.pollLast();
        }
        help.offer(value);
    }

    public int pop_front() {
        if(queue.isEmpty()) {
            return -1;
        }
        int val = queue.pop();
        if(help.peek() == val) {
            help.pop();
        }
        return val;
    }
}
