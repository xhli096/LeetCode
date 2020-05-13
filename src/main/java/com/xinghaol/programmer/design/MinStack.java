package com.xinghaol.programmer.design;

import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/5/12 10:30 上午
 * @Description: 155. 最小栈
 * 辅助栈和数据栈不同步的实现思想
 */
public class MinStack {
    /**
     * 用于存储当前的最小值，栈顶为当前的最小值
     */
    private Stack<Integer> minStack;

    private Stack<Integer> valueStack;

    public MinStack() {
        minStack = new Stack<>();
        valueStack = new Stack<>();
    }

    public void push(int x) {
        valueStack.push(x);
        if (!minStack.isEmpty()) {
            int min = minStack.peek();
            if (x <= min) {
                minStack.push(x);
            }
        } else {
            minStack.push(x);
        }
    }

    public void pop() {
        if (valueStack.isEmpty()) {
            return;
        }
        Integer pop = valueStack.pop();
        if (minStack.peek().equals(pop)) {
            minStack.pop();
        }
    }

    public int top() {
        return valueStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
