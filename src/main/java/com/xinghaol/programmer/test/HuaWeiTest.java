package com.xinghaol.programmer.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author huleikai
 * @create 2019-11-28 19:25
 */
public class HuaWeiTest {
    /**
     * 给定一个字符串数组，求字符串数组中所有字符串的全排列组合结果
     *
     * @param arr 需要排列组合的字符串数组
     * @param point 数组指针
     */
    private static StringBuffer fullPermutationResult = new StringBuffer();

    public static void fullPermutation(String arr[], int point) {
        if (point == arr.length) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
                fullPermutationResult.append(arr[i] + " ");
            }
            fullPermutationResult.append("|");
            System.out.println();
        }

        for (int i = point; i < arr.length; i++) {
            String temp1 = arr[point];
            arr[point] = arr[i];
            arr[i] = temp1;
            fullPermutation(arr, point + 1);
            String temp2 = arr[point];
            arr[point] = arr[i];
            arr[i] = temp2;
        }
    }

    private static ArrayList<Integer> tmpList = new ArrayList<>();
    //保存结果的字符串
    private static StringBuffer extractPermutationResult = new StringBuffer();

    /**
     * 给一个长度为n任意数组，取其中k个数，求所有的排列组合
     *
     * @param index 首个索引下标
     * @param count 每次取几个数,即数组指针
     * @param arr   待取的长度任意数组
     */
    public static void extractPermutation(int index, int count, int[] arr) {
        if (count > arr.length || arr.length <= 0) {
            System.out.println("随机取数个数不能大于数组的长度或者数组不能为空!");
            return;
        }

        if (count == 1) {
            for (int i = index; i < arr.length; i++) {
                tmpList.add(arr[i]);
                for (Integer integer : tmpList) {
                    extractPermutationResult.append(integer).append(",");
                }
                extractPermutationResult.delete(extractPermutationResult.length() - 1, extractPermutationResult.length()).append("|");
                System.out.println(tmpList);
                tmpList.remove((Object) arr[i]);
            }
        } else if (count > 1) {
            for (int i = index; i <= arr.length - count; i++) {
                tmpList.add(arr[i]);
                extractPermutation(i + 1, count - 1, arr);
                tmpList.remove((Object) arr[i]);
            }
        } else {
            return;
        }
    }

    /**
     * 返回随机输入的整数数组
     *
     * @return
     */
    public static int[] getArray() {
        int[] arr = new int[10];
        int i = 0;
        while (i < 10) {
            System.out.println("请随意输入十个整数,范围是[-512,512] :");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            if (num > 512 || num < -512) {
                System.out.println("您输入的数不合法，请输入一个范围在[-512,512]的整数!");
                continue;
            }
            arr[i] = num;
            i++;
        }
        return arr;
    }

    /**
     * 指定的数组中随机抽取三个数，然后带入公式:x的平方+xy-y的平方+z中，
     * 求出这十个数中随意随机出来的三个数带入这个公式的最小值
     *
     * @return
     */
    public static int getResult() {
        //存放每计算出来的三个随机数结果的集合
        ArrayList<Integer> resultList = new ArrayList<>();
        //从数组长度为10的数组中随机取出3个数，排列组合最终拿到的组合结果数组
        String[] combinationResult = extractPermutationResult.toString().split("\\|", -1);
        for (String c : combinationResult) {
            String[] strArr = c.split(",");
            //对每个随机抽取出来的三位数进行全排列组合
            fullPermutation(strArr, 0);
            //对每个随机抽取出来的三位数进行全排列组合之后的结果数组
            String[] fullPermutationResultArr = fullPermutationResult
                    //去掉fullPermutationResult字符串拼接的最后一个"|"
                    .delete(fullPermutationResult.length() - 1, fullPermutationResult.length())
                    .toString().split("\\|", -1);
            for (String s : fullPermutationResultArr) {
                String[] strNum = s.split(" ");
                int x = Integer.parseInt(strNum[0]);
                int y = Integer.parseInt(strNum[1]);
                int z = Integer.parseInt(strNum[2]);
                //带入华为题目中指定的公式对每个三位数组合进行结果值计算
                int result = (int) (Math.pow(x, 2) + x * y - Math.pow(y, 2) + z);
                //将每次计算的结果放入结果集合中进行保存
                resultList.add(result);
            }
        }
        //最终对结果集合进行升序排序
        Collections.sort(resultList);
        //返回所有排列组合计算出的最小结果值
        return resultList.get(0);
    }

    public static void main(String[] args) {
        int[] arr = getArray();
        extractPermutation(0, 3, arr);
        int result = getResult();
        System.out.println("结果是: " + result);
    }
}