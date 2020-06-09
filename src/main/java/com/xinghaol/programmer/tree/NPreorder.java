package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/6/8 11:50 上午
 * @Description: 589. N叉树的前序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 */
public class NPreorder {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            list.add(currentNode.val);
            List<Node> children = currentNode.children;
            if (children != null && children.size() > 0) {
                for (int i = children.size() - 1; i >= 0; i--) {
                    Node node = children.get(i);
                    if (node != null) {
                        stack.push(node);
                    }
                }
            }
        }
        return list;
    }
}
