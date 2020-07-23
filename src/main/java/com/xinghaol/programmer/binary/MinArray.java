package com.xinghaol.programmer.binary;

/**
 * @author: lixinghao
 * @date: 2020/7/22 5:29 下午
 * @Description:
 */
public class MinArray {
    private Integer min = Integer.MAX_VALUE;

    public int minArray(int[] numbers) {
        int length = numbers.length;
        if (length == 0) {
            return 0;
        }
        min(numbers, 0, numbers.length - 1);
        return min;
    }

    public void min(int[] numbers, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return;
        }

        int mid = startIndex + (endIndex - startIndex) / 2;
        if (numbers[mid] <= min) {
            min = numbers[mid];
        }
        min(numbers, startIndex, mid - 1);
        min(numbers, mid + 1, endIndex);
    }

    public static void main(String[] args) {
        MinArray minArray = new MinArray();
        int min = minArray.minArray(new int[]{1,3,5});
        System.out.println(min);
    }
}
