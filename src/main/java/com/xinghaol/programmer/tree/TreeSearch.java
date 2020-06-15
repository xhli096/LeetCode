package com.xinghaol.programmer.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/6/7 5:50 下午
 * @Description: 树的递归与非递归遍历
 * 打通树的遍历的任通二脉
 */
public class TreeSearch {
    private static Queue<TreeNode> preTraceQueue;
    private static Queue<TreeNode> preStackTraceQueue;
    private static Queue<TreeNode> midTraceQueue;
    private static Queue<TreeNode> midStackTraceQueue;
    private static Queue<TreeNode> postTraceQueue;
    private static Queue<TreeNode> postStackTraceQueue;

    static {
        preTraceQueue = new LinkedList<>();
        preStackTraceQueue = new LinkedList<>();
        midTraceQueue = new LinkedList<>();
        midStackTraceQueue = new LinkedList<>();
        postTraceQueue = new LinkedList<>();
        postStackTraceQueue = new LinkedList<>();
    }

    /**
     * 先序递归遍历：根节点、左子树、右子树
     *
     * @param root
     */
    public void preTrace(TreeNode root) {
        if (root == null) {
            return;
        }
        preTraceQueue.offer(root);
        preTrace(root.left);
        preTrace(root.right);
    }

    public void preStackTrace(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            preStackTraceQueue.offer(currentNode);
            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
    }

    /**
     * 中序递归遍历 左、中、右
     *
     * @param root
     */
    public void midTrace(TreeNode root) {
        if (root == null) {
            return;
        }
        midTrace(root.left);
        midTraceQueue.offer(root);
        midTrace(root.right);
    }

    public void midStackTrace(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (!stack.isEmpty() || current != null) {
            // 先将左子树节点全部加入stack
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            TreeNode node = stack.pop();
            midStackTraceQueue.offer(node);
            if (node.right != null) {
                current = node.right;
            }
        }
    }

    /**
     * 后续递归遍历
     *
     * @param root
     */
    public void postTrace(TreeNode root) {
        if (root == null) {
            return;
        }
        postTrace(root.left);
        postTrace(root.right);
        postTraceQueue.offer(root);
    }

    /**
     * 非递归后续遍历，左、右、中
     * 前序遍历：中、左、右 =》 中、右、左；
     * 最后利用另外一个栈来完成逆序 =》 左、右、中
     *
     * @param root
     */
    public void postStackTrace(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode current = stack1.pop();
            stack2.push(current);
            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        while (!stack2.isEmpty()) {
            postStackTraceQueue.offer(stack2.pop());
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rl = new TreeNode(2);
        root.left = rl;
        TreeNode rr = new TreeNode(3);
        root.right = rr;

        TreeNode rll = new TreeNode(4);
        rl.left = rll;
        TreeNode rlr = new TreeNode(5);
        rl.right = rlr;

        TreeNode rrl = new TreeNode(6);
        rr.left = rrl;
        TreeNode rrr = new TreeNode(7);
        rr.right = rrr;

        TreeSearch treeSearch = new TreeSearch();
        // 先序遍历
        treeSearch.preTrace(root);
        treeSearch.preStackTrace(root);
        while (!preTraceQueue.isEmpty()) {
            System.out.print(preTraceQueue.poll().val + "  ");
        }
        System.out.println();
        while (!preStackTraceQueue.isEmpty()) {
            System.out.print(preStackTraceQueue.poll().val + "  ");
        }
        System.out.println();

        // 中序遍历
        treeSearch.midTrace(root);
        treeSearch.midStackTrace(root);
        while (!midTraceQueue.isEmpty()) {
            System.out.print(midTraceQueue.poll().val + "  ");
        }
        System.out.println("");
        while (!midStackTraceQueue.isEmpty()) {
            System.out.print(midStackTraceQueue.poll().val + "  ");
        }
        System.out.println();

        // 后续遍历
        treeSearch.postTrace(root);
        treeSearch.postStackTrace(root);
        while (!postTraceQueue.isEmpty()) {
            System.out.print(postTraceQueue.poll().val + "  ");
        }
        System.out.println();
        while (!postStackTraceQueue.isEmpty()) {
            System.out.print(postStackTraceQueue.poll().val + "  ");
        }
    }
}
