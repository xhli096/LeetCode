package com.xinghaol.programmer.array;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/2 11:30 下午
 * @Description:
 */
public class RomanToInt {
    public int romanToInt(String s) {
        int length = s.length();
        if (length == 0) {
            return 0;
        }
        int[] resultArray = new int[length];
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int res = getBit(c);
            if (i != 0) {
                if (res > 1 && resultArray[i - 1] == 1) {
                    resultArray[i - 1] = -1;
                } else if (res > 10 && resultArray[i - 1] == 10) {
                    resultArray[i - 1] = -10;
                } else if (res > 100 && resultArray[i - 1] == 100) {
                    resultArray[i - 1] = -100;
                }
            }
            resultArray[i] = res;
        }
        System.out.println(Arrays.toString(resultArray));
        int result = 0;
        for (int i = 0; i < resultArray.length; i++) {
            result += resultArray[i];
        }

        return result;
    }

    public int getBit(Character c) {
        int result = 0;
        switch (c) {
            case 'I':
                result = 1;
                break;
            case 'V':
                result = 5;
                break;
            case 'X':
                result = 10;
                break;
            case 'L':
                result = 50;
                break;
            case 'C':
                result = 100;
                break;
            case 'D':
                result = 500;
                break;
            case 'M':
                result = 1000;
                break;
            default:
                result = 0;
                break;
        }

        return result;
    }

    public static void main(String[] args) {
        RomanToInt romanToInt = new RomanToInt();
        int toInt = romanToInt.romanToInt("MCMXCIV");
        System.out.println(toInt);
    }
}
