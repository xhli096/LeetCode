package com.xinghaol.programmer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/4/1 1:18 下午
 * @Description: 有效括号的嵌套深度
 * 有效括号字符串 仅由 "(" 和 ")" 构成，并符合下述几个条件之一：
 * <p>
 * 空字符串
 * 连接，可以记作 AB（A 与 B 连接），其中 A 和 B 都是有效括号字符串
 * 嵌套，可以记作 (A)，其中 A 是有效括号字符串
 * 类似地，我们可以定义任意有效括号字符串 s 的 嵌套深度 depth(S)：
 * <p>
 * s 为空时，depth("") = 0
 * s 为 A 与 B 连接时，depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是有效括号字符串
 * s 为嵌套情况，depth("(" + A + ")") = 1 + depth(A)，其中 A 是有效括号字符串
 * 例如：""，"()()"，和 "()(()())" 都是有效括号字符串，嵌套深度分别为 0，1，2，而 ")(" 和 "(()" 都不是有效括号字符串。
 * <p>
 * 给你一个有效括号字符串 seq，将其分成两个不相交的子序列 A 和 B，且 A 和 B 满足有效括号字符串的定义（注意：A.length + B.length = seq.length）。
 * 现在，你需要从中选出 任意 一组有效括号字符串 A 和 B，使 max(depth(A), depth(B)) 的可能取值最小。
 * 返回长度为 seq.length 答案数组 answer ，选择 A 还是 B 的编码规则是：如果 seq[i] 是 A 的一部分，那么 answer[i] = 0。否则，answer[i] = 1。即便有多个满足要求的答案存在，你也只需返回 一个。
 * <p>
 * 示例 1：
 * 输入：seq = "(()())"
 * 输出：[0,1,1,1,1,0]
 * 示例 2：
 * 输入：seq = "()(())()"
 * 输出：[0,0,0,1,1,0,1,1]
 * <p>
 * 提示：
 * 1 <= text.size <= 10000
 */
public class MaxDepthAfterSplit {
    /**
     * 题目要求得到Max(depth(A), depth(B))的可能取值最小，则需要depth(A), depth(B)中的最大值最小。'
     * 那么当且仅当depth(A) = depth(B)时，有两者最大值最小，此时最大值与最小值相等。即括号匹配，一半为A一半为B
     * 奇数层的(给到A，偶数层的(给到B，遇到)则减小深度
     *
     * @param seq
     * @return
     */
    public int[] maxDepthAfterSplit(String seq) {
        int depth = 0;
        List<Integer> list = new ArrayList<>();
        for (char c : seq.toCharArray()) {
            if (c == '(') {
                depth++;
                list.add(depth % 2);
            } else {
                list.add(depth % 2);
                depth--;
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
