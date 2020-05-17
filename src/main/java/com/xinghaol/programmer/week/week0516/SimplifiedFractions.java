package com.xinghaol.programmer.week.week0516;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/5/16 10:39 下午
 * @Description:
 */
public class SimplifiedFractions {
    public List<String> simplifiedFractions(int n) {
        if (n == 1 || n == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();

        // 分母
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n - 1; j++) {
                if ((j % 2 == 0 && i % 2 == 0) || j >= i) {
                    continue;
                }
                if (j > 1 && i % j == 0) {
                    continue;
                }
                // 有最大公约数
                int gcd = gcd(i, j);
                if (gcd != 1) {
                    continue;
                }
                result.add(j + "/" + i);
            }
        }

        return result;
    }

    public int gcd(int a, int b) {
        if (a < b) {
            gcd(b, a);
        }
        if (a % b == 0) {
            return b;
        } else {
            // 辗转相除法，那除数与余数进行运算
            return gcd(b, a % b);
        }
    }

    public static void main(String[] args) {
        SimplifiedFractions simplifiedFractions = new SimplifiedFractions();
        List<String> list = simplifiedFractions.simplifiedFractions(9);

        System.out.println(JSON.toJSONString(list));
    }
}
