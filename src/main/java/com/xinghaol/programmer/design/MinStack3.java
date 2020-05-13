package com.xinghaol.programmer.design;

import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/5/12 11:20 上午
 * @Description: 辅助栈和数据栈同步；@liweiwei
 * https://leetcode-cn.com/problems/min-stack/solution/shi-yong-fu-zhu-zhan-tong-bu-he-bu-tong-bu-python-/
 */
public class MinStack3 {
    /**
     * 在代码实现的时候有两种方式：
     * 1、辅助栈和数据栈同步
     * 特点：编码简单，不用考虑一些边界情况，就有一点不好：辅助栈可能会存一些“不必要”的元素。
     * <p>
     * 2、辅助栈和数据栈不同步
     * 特点：由“辅助栈和数据栈同步”的思想，我们知道，当数据栈进来的数越来越大的时候，我们要在辅助栈顶放置和当前辅助栈顶一样的元素，这样做有点“浪费”。基于这一点，我们做一些“优化”，但是在编码上就要注意一些边界条件。
     * （1）辅助栈为空的时候，必须放入新进来的数；
     * （2）新来的数小于或者等于辅助栈栈顶元素的时候，才放入，特别注意这里“等于”要考虑进去，因为出栈的时候，连续的、相等的并且是最小值的元素要同步出栈；
     * （3）出栈的时候，辅助栈的栈顶元素等于数据栈的栈顶元素，才出栈。
     * 总结一下：出栈时，最小值出栈才同步；入栈时，最小值入栈才同步。
     * 对比：个人觉得“同步栈”的方式更好一些，因为思路清楚，因为所有操作都同步进行，所以调试代码、定位问题也简单。
     * “不同步栈”，虽然减少了一些空间，但是在“出栈”、“入栈”的时候还要做判断，也有性能上的消耗。
     */
    // 数据栈
    private Stack<Integer> data;
    // 辅助栈
    private Stack<Integer> helper;

    /**
     * initialize your data structure here.
     */
    public MinStack3() {
        data = new Stack<>();
        helper = new Stack<>();
    }

    // 思路 1：数据栈和辅助栈在任何时候都同步

    public void push(int x) {
        // 数据栈和辅助栈一定会增加元素
        data.add(x);
        if (helper.isEmpty() || helper.peek() >= x) {
            helper.add(x);
        } else {
            helper.add(helper.peek());
        }
    }

    public void pop() {
        // 两个栈都得 pop
        if (!data.isEmpty()) {
            helper.pop();
            data.pop();
        }
    }

    public int top() {
        if (!data.isEmpty()) {
            return data.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }

    public int getMin() {
        if (!helper.isEmpty()) {
            return helper.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }
}
