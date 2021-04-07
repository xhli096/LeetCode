package com.xinghaol.programmer.array;

import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2021/4/4 10:55 下午
 * @Description: 316/1081 ： 不同字符的最小子序列
 */
public class RemoveDuplicateLetters {
    public String smallestSubsequence(String s) {
        int[] count = new int[256];
        for(char c : s.toCharArray()) {
            count[c]++;
        }

        boolean[] flag = new boolean[256];
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            count[c]--;
            if (flag[c]) {
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > c) {
                // 若之后不存在栈顶元素了，则停止 pop
                if (count[stack.peek()] == 0) {
                    break;
                }
                flag[stack.peek()] = false;
                stack.pop();
            }
            stack.push(c);
            flag[c] = true;
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }

        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters removeDuplicateLetters = new RemoveDuplicateLetters();
        String bcabc = removeDuplicateLetters.smallestSubsequence("bcabc");

        System.out.println(bcabc);

    }
}
