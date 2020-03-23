package com.xinghaol.programmer;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/3/22 12:50 下午
 * @Description: 使数组唯一的最小增量
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * <p>
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 * <p>
 * 示例 1:
 * <p>
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 * 示例 2:
 * <p>
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 * 提示：
 * <p>
 * 0 <= A.length <= 40000
 * 0 <= A[i] < 40000
 */
public class MinIncrementForUnique {
    /**
     * 排序
     * https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/solution/shi-shu-zu-wei-yi-de-zui-xiao-zeng-liang-by-leet-2/
     *
     * @param A
     * @return
     */
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        // 重复的次数
        int taken = 0;
        // 操作需要减去的次数。  0 -> 7 需要7次，但是从3 -> 7 只需要四次，所以 =》 -3 + 7
        int ans = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] == A[i]) {
                taken++;
                ans -= A[i];
            } else {
                // 如果A[i-1] < A[i]，则在(A[i-1], A[i])之间的值都没有出现过。
                // 当前的重复个数为taken，最多可以改变Math.min(taken, A[i] - A[i-1] - 1)个
                int give = Math.min(taken, A[i] - A[i - 1] - 1);
                taken -= give;
                ans += give * (give + 1) / 2 + give * A[i - 1];
            }
        }

        if (A.length > 0) {
            ans += taken * (taken + 1) / 2 + taken * A[A.length - 1];
        }

        return ans;
    }
}
