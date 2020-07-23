package com.xinghaol.programmer.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/7/21 5:37 下午
 * @Description: 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * 示例：
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *  
 *
 * 提示：
 *
 * 0 <= n <= 8
 */
public class GenerateTrees {
    /**
     * 递归解法
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }

        return fun(1, n);
    }

    private List<TreeNode> fun(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }
        // 只有一个节点
        if (start == end) {
            result.add(new TreeNode(start));
            return result;
        }
        // 尝试把每一个节点都作为根节点
        for (int i = start; i <= end; i++) {
            // 得到所有可能的左子树节点
            List<TreeNode> left = fun(start, i-1);
            // 得到所有的右子树节点
            List<TreeNode> right = fun(i+1, end);

            // 两两组合
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    result.add(root);
                }
            }
        }

        return result;
    }
}
