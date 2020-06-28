package com.xinghaol.programmer.array;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/26 3:05 下午
 * @Description: 986. 区间列表的交集
 * https://leetcode-cn.com/problems/interval-list-intersections/
 */
public class IntervalIntersection {
    private int[][] intervalIntersection(int[][] A, int[][] B) {
        int lengthA = A.length;
        int lengthB = B.length;
        if (lengthA == 0 || lengthB == 0) {
            return new int[0][0];
        }
        int[][] result = new int[lengthA + lengthB][];
        int indexA = 0;
        int indexB = 0;
        int index = 0;

        // 如果两个区间有交集，则左边界max(A[indexA][0], B[indexB][0]), 右边界min(A[indexA][1],B[indexB][1])
        // 所有交接互不相交
        while (indexA < lengthA && indexB < lengthB) {
            int left = Math.max(A[indexA][0], B[indexB][0]);
            int right = Math.min(A[indexA][1], B[indexB][1]);
            // 如果right <= left时，区间没有交集，但是在闭区间交点上会有交集
            // 如果left == right，则说明在左、右区间的闭区间有一个点的交集
            if (left <= right) {
                result[index++] = new int[]{left, right};
            }

            // A的右区间，小于B的右区间
            // A的区间已经被合并了，indexA++；
            if (A[indexA][1] < B[indexB][1]) {
                indexA++;
            } else {
                indexB++;
            }

        }

        result = Arrays.copyOf(result, index);

        return result;
    }

    public static void main(String[] args) {
        IntervalIntersection intervalIntersection = new IntervalIntersection();
        int[][] intersection = intervalIntersection.intervalIntersection(new int[][]{
                {0, 2},
                {5, 10},
                {13, 23},
                {24, 25}
        }, new int[][]{
                {1, 5},
                {8, 12},
                {15, 24},
                {25, 26}
        });
        System.out.println(JSON.toJSONString(intersection));
    }
}
