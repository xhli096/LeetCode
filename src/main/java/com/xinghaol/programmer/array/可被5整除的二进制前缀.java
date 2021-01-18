package com.xinghaol.programmer.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/1/14 1:54 下午
 * @description description
 */
public class 可被5整除的二进制前缀 {

    /**
     * 能被5整除，则最后一位是0或者5；每次进保存最后一位，读取新的一位则乘以2，相当于进位。个位数与5取余，如果是0或者5就是true
     *
     * @param A
     *
     * @return
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        int length = A.length;
        if (A == null || length == 0) {
            return new ArrayList<>();
        }
        List<Boolean> result = new ArrayList<>(A.length);
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum = sum * 2 + A[i];
            sum %= 10;
            result.add((sum %= 5) == 0);
        }

        return result;
    }

    /**
     * 转化为数字，会数据溢出
     *
     * @param A
     *
     * @return
     */
    public List<Boolean> prefixesDivBy52(int[] A) {
        int length = A.length;
        if (A == null || length == 0) {
            return new ArrayList<>();
        }
        List<Boolean> result = new ArrayList<>(A.length);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(A[i]);
            if (Long.parseUnsignedLong(stringBuilder.toString(), 2) % 5 == 0) {
                result.add(true);
            } else {
                result.add(false);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        可被5整除的二进制前缀 a = new 可被5整除的二进制前缀();
        List<Boolean> booleans = a.prefixesDivBy5(
                new int[] { 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1 });
        System.out.println(booleans);
    }
}
