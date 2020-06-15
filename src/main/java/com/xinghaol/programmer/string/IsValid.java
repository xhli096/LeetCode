package com.xinghaol.programmer.string;

import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/6/9 10:35 下午
 * @Description: 20. 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class IsValid {
    public boolean isValid(String s) {
        int length = s.length();
        if (length == 0) {
            return true;
        }
        if (length == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (c == ')' && pop != '(') {
                    return false;
                }
                if (c == ']' && pop != '[') {
                    return false;
                }
                if (c == '}' && pop != '{') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        IsValid isValid = new IsValid();
        boolean valid = isValid.isValid("()[]{}");
        System.out.println(valid);
    }
}
