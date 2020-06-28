package com.xinghaol.programmer.math;

/**
 * @author: lixinghao
 * @date: 2020/3/11 2:48 下午
 * @Description: 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
 * <p>
 * 形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输出：[0,2,1,-6,6,-7,9,1,2,0,1]
 * 输出：true
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * 示例 2：
 * <p>
 * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：[3,3,6,5,-2,2,5,1,-9,4]
 * 输出：true
 * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 *  
 * <p>
 * 提示：
 * 3 <= A.length <= 50000
 * -10^4 <= A[i] <= 10^4
 * <p>
 * 首先判断数组元素加和对3取余是否为0，不为0，无法等分为3分，不存在三部分相等的数组，直接返回false。
 * 一次遍历查看是否有三部分为sum/3的子数组。
 */
public class CanThreePartsEqualSum {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int num : A) {
            sum += num;
        }
        // 如果对3取余不为0，则说明不能等分为3分，则不存在三部分相等的数组。
        if (sum % 3 != 0) {
            return false;
        }
        // 一次遍历
        int res = sum / 3;
        int currentSum = 0;
        int part = 0;
        for (int i = 0; i < A.length; i++) {
            currentSum += A[i];
            if (currentSum == res) {
                part++;
                currentSum = 0;
            }

        }
        return part >= 3;
    }
}
