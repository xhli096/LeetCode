package com.xinghaol.programmer.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: lixinghao
 * @date: 2020/7/8 3:16 下午
 * @Description:
 */
public class DivingBoard {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();

        int shortCount = 0;
        while (shortCount <= k) {
            set.add((shortCount * shorter) + (longer * (k - shortCount)));
            shortCount++;
        }

        int[] result = new int[set.size()];
        int index = 0;
        for (Integer integer : set) {
            result[index++] = integer;
        }
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        DivingBoard divingBoard = new DivingBoard();
        int[] board = divingBoard.divingBoard(1, 2, 3);
        System.out.println(Arrays.toString(board));
    }
}
