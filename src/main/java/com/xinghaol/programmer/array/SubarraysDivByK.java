package com.xinghaol.programmer.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2020/5/27 12:11 上午
 * @Description:
 */
public class SubarraysDivByK {
    /**
     * 前缀和，双重for循环，时间复杂度为O(N^2)，会超时
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {
        int length = A.length;

        // 计算前缀和
        int[] nums = new int[length + 1];
        nums[0] = 0;
        for (int i = 0; i < length; i++) {
            nums[i + 1] = nums[i] + A[i];
        }

        // i到j之间的，nums[j]-nums[i] % 5 == 0，即是一个解。
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length + 1; j++) {
                if ((nums[j] - nums[i]) % K == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 使用前缀和+哈希表
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK2(int[] A, int K) {
        /*
        前提知识：同余定理。在数论中的重要概念：给定一个正整数m，如果两个整数a和b满足a-b能被m整除，即(a-b)/m得到一个整数。
        那么就称整数a和整数b对模m同余。
        (P[j]−P[i−1])modK==0  <=>  P[j]modK==P[i−1]modK
         */
        int length = A.length;
        // 某一个余数，该余数出现的次数。
        Map<Integer, Integer> map = new HashMap<>();
        // 前缀为 0 的情况出现了一次(也算是前缀和做法中的一个套路，要记住)
        map.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int num : A) {
            sum += num;
            int mod = sum % K;
            int same = map.getOrDefault(mod, 0);
            count += same;
            map.put(mod, same + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        SubarraysDivByK subarraysDivByK = new SubarraysDivByK();
        int count = subarraysDivByK.subarraysDivByK(new int[]{5}, 9);
        System.out.println(count);
    }
}
