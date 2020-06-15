package com.xinghaol.programmer.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: lixinghao
 * @date: 2020/6/10 10:42 下午
 * @Description: 440. 字典序的第K小数字
 * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
 */
public class FindKthNumber {
    /**
     * 同样先序遍历1-0树，1-0树的先序遍历结果就是字典序的遍历结果
     * eg：1 10 11 12 13 14 15 16 17 18 19 101 ...
     * 前驱例题：386. 字典序排数
     * ps : 超时算法
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber2(int n, int k) {
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        if (n < 10) {
            for (int i = n; i > 0; i--) {
                stack.push(i);
            }
        } else {
            // 1-0 树的第一层，九个节点
            for (int i = 9; i > 0; i--) {
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            int current = stack.pop();
            list.add(current);
            if (list.size() >= k) {
                result = current;
                break;
            }
            // 乘以10 > n，说明没有下一位数字了
            if (current * 10 > n) {
                continue;
            } else {
                // 计算本次处理节点子树中应该记录的元素个数
                int tmp = n - current * 10;
                if (tmp > 9) {
                    tmp = 9;
                }
                // 在下层节点中，就出现了10 100等这样包含0的元素
                for (int i = tmp; i >= 0; i--) {
                    stack.push(current * 10 + i);
                }
            }
        }
        return result;
    }

    /**
     * 参考题解：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/solution/yi-tu-sheng-qian-yan-by-pianpianboy/
     * 不需要将所有的字典树遍历结束就可以得到结果
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        int cur = 1;
        // 扣除掉第一个0节点
        k = k - 1;
        while (k > 0) {
            // 计算以current开头的数字，在<=n的区间内数字的个数
            int num = getCount(n, cur, cur + 1);
            // num < k，说明不在当前树的分支及其子树上，向后移动，进入后一个节点的子字典树
            if (num <= k) {
                cur += 1;
                k -= num;
            } else {
                // 进入本分支，说明第k个再当前的子树中,cur*=10 在1-0字典树向下移动
                cur *= 10;
                // 将根节点去除
                k -= 1;
            }
        }

        return cur;
    }

    /**
     * 注意这里first、long均使用了long类型，int类型会发生溢出
     *
     * @param n
     * @param first
     * @param last
     * @return
     */
    private int getCount(int n, long first, long last) {
        int count = 0;

        while (first <= n) {
            // 比如n是195的情况195到100有96个数
            // 当前位数中有的数字个数
            count += Math.min(n + 1, last) - first;
            first *= 10;
            last *= 10;
        }

        return count;
    }

    public static void main(String[] args) {
        FindKthNumber findKthNumber = new FindKthNumber();
        int kthNumber = findKthNumber.findKthNumber(9885387, 8786251);
        System.out.println(kthNumber);
    }
}
