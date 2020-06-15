package com.xinghaol.programmer.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/6/10 10:25 下午
 * @Description: 386. 字典序排数
 */
public class LexicalOrder {
    /**
     * 先序遍历1/0 树
     * 先序遍历，借助「堆栈」来看就是先加入后面的子树元素。在处理当前节点的子树时，原则是一样的，向处理后面的元素。
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        // 小于10只有1位，不需要考虑10 11的这种情况
        if (n < 10) {
            for (int i = n; i > 0; i--) {
                stack.push(i);
            }
        } else {
            for (int i = 9; i > 0; i--) {
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            int current = stack.pop();
            result.add(current);
            if (current * 10 > n) {
                continue;
            } else {
                int tmp = n - current * 10;
                // 这里只多处理一位，如果到10，则变成了2位。
                if (tmp > 9) {
                    tmp = 9;
                }
                for (int i = tmp; i >= 0; i--) {
                    stack.push(current * 10 + i);
                }
            }
        }

        return result;
    }
}
