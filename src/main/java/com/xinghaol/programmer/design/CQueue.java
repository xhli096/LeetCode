package com.xinghaol.programmer.design;

import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/6/30 2:12 下午
 * @Description: 剑指 Offer 09. 用两个栈实现队列
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * <p>
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 */
public class CQueue {
    private Stack<Integer> fStack;
    private Stack<Integer> sStack;

    public CQueue() {
        fStack = new Stack<>();
        sStack = new Stack<>();
    }

    public void appendTail(int value) {
        fStack.push(value);
    }

    public int deleteHead() {
        if (sStack.isEmpty()) {
            while (!fStack.isEmpty()) {
                sStack.push(fStack.pop());
            }
        }
        if (sStack.isEmpty()) {
            return -1;
        }

        return sStack.pop();
    }
}
