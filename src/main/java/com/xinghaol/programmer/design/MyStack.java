package com.xinghaol.programmer.design;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: lixinghao
 * @date: 2020/6/18 11:25 下午
 * @Description: 225. 用队列实现栈
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 */
public class MyStack {
    private int size;
    private Deque<Integer> stack;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        this.size = 0;
        stack = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        stack.offer(x);
        this.size++;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        this.size--;
        return stack.removeLast();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return stack.peekLast();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return this.size == 0;
    }
}
