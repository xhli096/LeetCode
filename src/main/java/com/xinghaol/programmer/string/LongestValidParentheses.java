package com.xinghaol.programmer.string;

import com.alibaba.fastjson.JSON;

import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/6/27 4:00 下午
 * @Description: 32. 最长有效括号
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {
    /**
     * 典型的栈
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int length = s.length();
        if (length < 2) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0;

        // 为了方便先推进一个-1
        stack.push(-1);
        System.out.println(JSON.toJSONString(stack));
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '(') {
                // 当遇到的是'('的时候，我们将其坐标入栈
                stack.push(i);
            } else {
                System.out.println(JSON.toJSONString(stack));
                // 当遇到的是'）'的时候，弹出栈顶元素。此时需要分两种情况。
                stack.pop();
                // 此时如果栈空了，其实相当于前面已经正好匹配了，然后再进来了一个'）',此时无需更新最大值max，
                // 只需将当期坐标入栈。其作用和上面栈初始化的时候放入一个-1相同。
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // peek 不改变栈的值(不删除栈顶的值)
                    System.out.println(stack.peek() + "  " + i + "  " + (i - stack.peek()));
                    // 如果此时栈非空，说明又多了一对匹配。需要更新max的值。
                    max = Math.max(max, i - stack.peek());
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        int validParentheses = longestValidParentheses.longestValidParentheses("(()())");
        System.out.println(validParentheses);
    }
}
