package com.xinghaol.programmer.week.week0516;


import com.alibaba.fastjson.JSON;
import com.xinghaol.programmer.tree.TreeNode;

import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/5/16 11:00 下午
 * @Description:
 */
public class GoodNodes {
    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int count = 0;

        TreeNode tmp = root;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> maxValueQueue = new Stack<>();
        maxValueQueue.push(tmp.val);
        while (tmp != null) {
            stack.push(tmp);
            if (maxValueQueue.isEmpty()) {
                maxValueQueue.push(tmp.val);
            } else {
                if (maxValueQueue.pop() <= tmp.val) {
                    maxValueQueue.push(tmp.val);
                }
            }
            tmp = tmp.left;
        }
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            if (treeNode.val == maxValueQueue.pop()) {
                count++;
                maxValueQueue.pop();

            }
            if (treeNode.right != null) {
                tmp = treeNode.right;
                while (tmp != null) {
                    stack.push(tmp);
                    if (maxValueQueue.isEmpty()) {
                        maxValueQueue.push(tmp.val);
                    } else {
                        if (maxValueQueue.peek() <= tmp.val) {
                            maxValueQueue.push(tmp.val);
                        }
                    }
                    tmp = tmp.left;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        GoodNodes goodNodes = new GoodNodes();

        TreeNode root = new TreeNode(3);
        TreeNode l = new TreeNode(1);
        TreeNode r = new TreeNode(4);
        root.left = l;
        root.right = r;
        TreeNode ll = new TreeNode(3);
        l.left = ll;

        TreeNode rrl = new TreeNode(1);
        r.left = rrl;
        TreeNode rrr = new TreeNode(5);
        r.right = rrr;
        System.out.println(JSON.toJSONString(root));
        int i = goodNodes.goodNodes(root);
        System.out.println(i);
    }
}
