package com.xinghaol.programmer.array;


import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/6/1 8:22 下午
 * @Description: 1431. 拥有最多糖果的孩子
 */
public class KidsWithCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < candies.length; i++) {
            max = Math.max(max, candies[i]);
        }
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max) {
                result.add(true);
            } else {
                result.add(false);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        KidsWithCandies kidsWithCandies = new KidsWithCandies();
        List<Boolean> booleans = kidsWithCandies.kidsWithCandies(new int[]{4, 2, 1, 1, 2}, 1);
        System.out.println(booleans);
    }
}
