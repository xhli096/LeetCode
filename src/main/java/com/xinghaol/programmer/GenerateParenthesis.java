package com.xinghaol.programmer;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: lixinghao
 * @date: 2020/4/9 10:39 上午
 * @Description: 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例：
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParenthesis {
    List<String> result = Lists.newArrayList();

    /**
     * 递归生成
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return result;
        }
        if (n == 1) {
            result.add("()");
            return result;
        }
        dfs(n, n, "");
        return result;
    }

    /**
     * 通过暴力搜索，就是通过dfs。但是并不是每一个结果都符合要求，所以通过if来进行剪枝
     *
     * @param left
     * @param right
     * @param currentString
     */
    private void dfs(int left, int right, String currentString) {
        // 左右括号都不剩余了，递归终止
        if (left == 0 && right == 0) {
            result.add(currentString);
            return;
        }
        // 如果左括号还剩余的话，可以拼接左括号，这里是一个剪枝的过程。
        if (left > 0) {
            dfs(left - 1, right, currentString + "(");
        }
        // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
        if (right > left) {
            dfs(left, right - 1, currentString + ")");
        }
    }

    /**
     * 广度优先遍历方法
     */
    class Node {
        /**
         * 当前得到的字符串
         */
        private String res;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        public Node(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }
}
