package com.xinghaol.programmer.tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: lixinghao
 * @date: 2020/6/18 8:03 下午
 * @Description: 1028. 从先序遍历还原二叉树
 * https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal/
 */
public class RecoverFromPreorder {
    public TreeNode recoverFromPreorder(String s) {
        int length = s.length();
        if (length == 0) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = null;
        int index = 0;
        // 前一个的depth
        int preDepth = 0;
        // 当前的depth
        int currentDepth = 0;

        while (index < length) {
            int val = 0;
            // 根节点还没有初始化
            if (root == null) {
                while (index < length && Character.isDigit(s.charAt(index))) {
                    val = val * 10 + (s.charAt(index) - '0');
                    index++;
                }
                root = new TreeNode(val);
                stack.push(root);
            } else if (s.charAt(index) == '-') {
                // 遇到了-，使用current记录层数
                currentDepth++;
                index++;
            } else {
                // 计算当前层数的值
                while (index < length && Character.isDigit(s.charAt(index))) {
                    val = val * 10 + (s.charAt(index) - '0');
                    index++;
                }
                // 说明是下一层
                if (currentDepth > preDepth) {
                    TreeNode treeNode = stack.pop();
                    treeNode.left = new TreeNode(val);
                    // 将之前弹出来的node在压回去
                    stack.push(treeNode);
                    stack.push(treeNode.left);
                } else {
                    // 因为上面一个if将preDepth = currentDepth，所以当二者相等时，位于同一层。
                    // 当currentDepth < preDepth的时候，就回溯到上一层的右子树去，说明这个分支的左节点已经结束，所以就出栈、出栈。
                    while (stack.size() > currentDepth) {
                        stack.pop();
                    }
                    TreeNode node = stack.pop();
                    node.right = new TreeNode(val);
                    stack.push(node);
                    stack.push(node.right);
                }
                preDepth = currentDepth;
                currentDepth = 0;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        RecoverFromPreorder recoverFromPreorder = new RecoverFromPreorder();
        TreeNode treeNode = recoverFromPreorder.recoverFromPreorder("1-2--3--4-5--6--7");
        System.out.println(JSON.toJSONString(treeNode));
    }
}
