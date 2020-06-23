package com.xinghaol.programmer.design;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: lixinghao
 * @date: 2020/6/21 11:09 上午
 * @Description: 232. 用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 */
public class MyQueue {
    private int size;
    private Deque<Integer> stack1;
    private Deque<Integer> stack2;

    /** Initialize your data structure here. */
    public MyQueue() {
        this.size = 0;
        this.stack1 = new ArrayDeque<>();
        this.stack2 = new ArrayDeque<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        this.stack1.push(x);
        size++;
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (this.stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        size--;
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return this.size == 0;
    }
}
