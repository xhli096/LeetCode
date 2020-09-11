package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/9/4 11:51 下午
 * @Description: 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 输入:
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        constructPaths(root, "", paths);
        return paths;
    }

    public void constructPaths(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            // 每次生成一个新的
            StringBuffer pathSB = new StringBuffer(path);
            pathSB.append("" + root.val);
            // 当前节点是叶子节点
            if (root.left == null && root.right == null) {
                // 把路径加入到答案中
                paths.add(pathSB.toString());
            } else {
                // 当前节点不是叶子节点，继续递归遍历
                pathSB.append("->");
                constructPaths(root.left, pathSB.toString(), paths);
                constructPaths(root.right, pathSB.toString(), paths);
            }
        }
    }
}
