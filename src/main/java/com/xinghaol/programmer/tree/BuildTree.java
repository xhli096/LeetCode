package com.xinghaol.programmer.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/3/23 2:12 下午
 * @Description: 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 限制：
 * 0 <= 节点个数 <= 5000
 */
public class BuildTree {
    /**
     * 递归
     * 时间复杂度：O(n)。对于每个节点都有创建过程以及根据左右子树重建过程。
     * 空间复杂度：O(n)。存储整棵树的开销
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, length - 1, inorder, 0, length - 1, indexMap);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd, int[] inorder, int inorderStart, int inorderEnd, Map<Integer, Integer> indexMap) {
        if (preorderEnd > preorderStart) {
            return null;
        }
        int rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);
        if (preorderStart == preorderEnd) {
            return root;
        } else {
            int rootIndex = indexMap.get(rootVal);
            int leftNodes = rootIndex - inorderStart, rightNodes = inorderEnd - rootIndex;
            TreeNode leftSubtree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes, inorder, inorderStart, rootIndex - 1, indexMap);
            TreeNode rightSubtree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd, inorder, rootIndex + 1, inorderEnd, indexMap);
            root.left = leftSubtree;
            root.right = rightSubtree;
            return root;
        }
    }

    /**
     * 迭代
     * 使用前序遍历的第一个元素创建根节点。
     * 创建一个栈，将根节点压入栈内。
     * 初始化中序遍历下标为 0。
     * 遍历前序遍历的每个元素，判断其上一个元素（即栈顶元素）是否等于中序遍历下标指向的元素。
     * 若上一个元素不等于中序遍历下标指向的元素，则将当前元素作为其上一个元素的左子节点，并将当前元素压入栈内。
     * 若上一个元素等于中序遍历下标指向的元素，则从栈内弹出一个元素，同时令中序遍历下标指向下一个元素，之后继续判断栈顶元素是否等于中序遍历下标指向的元素，若相等则重复该操作，直至栈为空或者元素不相等。然后令当前元素为最后一个想等元素的右节点。
     * 遍历结束，返回根节点。
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        // 中序遍历下标
        int inorderIndex = 0;
        for (int i = 0; i < preorder.length; i++) {
            int preOrderValue = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preOrderValue);
                stack.push(node.left);
            } else {
                while (!stack.empty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preOrderValue);
                stack.push(node.right);
            }
        }

        return root;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * 递归解法
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        int length = preorder.length;
        // value值，下标值
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = build(preorder, inorder, 0, length - 1, 0, length - 1, map);

        return root;
    }

    private TreeNode build(int[] preorder, int[] inorder, int preStartIndex, int preEndIndex, int inStartIndex, int inEndIndex, Map<Integer, Integer> map) {
        // 如果开始index > endIndex，则递归结束
        if (preStartIndex > preEndIndex) {
            return null;
        }
        int rootValue = preorder[preStartIndex];
        TreeNode root = new TreeNode(rootValue);
        // 如果开始与结束index相同，则说明没有其他节点，直接返回即可。
        if (preEndIndex == preStartIndex) {
            return root;
        } else {
            int rootInDex = map.get(rootValue);
            int leftNodesCount = rootInDex - inStartIndex;
            root.left = build(preorder, inorder, preStartIndex + 1, preStartIndex + leftNodesCount, inStartIndex, rootInDex - 1, map);
            root.right = build(preorder, inorder, preStartIndex + leftNodesCount + 1, preEndIndex, rootInDex + 1, inEndIndex, map);
            return root;
        }
    }

    public static void main(String[] args) {
        BuildTree buildTree = new BuildTree();
        TreeNode treeNode = buildTree.buildTree3(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(treeNode);
    }
}
