package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2021/4/13 7:37 下午
 * @Description: 783. 二叉搜索树节点最小距离
 */
public class MinDiffInBST {
    public int minDiffInBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode current = stack.pop();
            list.add(current.val);
            if(current.right != null) {
                stack.push(current.right);
            }
            if(current.left != null) {
                stack.push(current.left);
            }
        }

        Collections.sort(list);
        Integer delta = Integer.MAX_VALUE;
        for(int i = 1; i < list.size(); i++) {
            delta = Math.min(delta, list.get(i) - list.get(i-1));
        }

        return delta;
    }
}
