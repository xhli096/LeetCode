package com.xinghaol.programmer.binary;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/6/14 9:45 下午
 * @Description: 1300. 转变数组后最接近目标值的数组和
 * https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target/
 */
public class FindBestValue {
    /**
     * 通过数学计算方法解决
     *
     * @param arr
     * @param target
     * @return
     */
    public int findBestValue(int[] arr, int target) {
        int length = arr.length;
        int result = 0;
        int max = 0;
        for (int i = 0; i < length; i++) {
            result += arr[i];
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 如果数组的和恰好等于target，则不需要进行调整
        if (result <= target) {
            return max;
        }
        // 查找找临界值的过程
        // 只需要在 arr[target / length] ~ arr[length]之间选择；
        // 如果存在比arr[target / length]小的值，那么整个数组的值一定比target小；如果全部比arr[target / length]大，因为是整除
        // 所以也会是小于的

        // 这个start是当前值，而非下标
        int start = target / length;
        int currentSum = getNumber(arr, start);
        if (currentSum == target) {
            return start;
        }
        int firstSum = currentSum;
        int secondSum = currentSum;

        result = start;
        // secondSum记录的是result+1下标的数组和
        while (secondSum < target) {
            result++;
            firstSum = secondSum;
            secondSum = getNumber(arr, result);
            // 如果secondSum恰好就是，则直接返回即可
            if (secondSum == target) {
                return result;
            }
        }
        System.out.println(result);
        // 比较firstSum和secondSum的值那个离target更新，决定选择那个下标值
        if (target - firstSum <= secondSum - target) {
            return result - 1;
        } else {
            return result;
        }
    }

    /**
     * 计算当前value值替换后，数组的值
     *
     * @param arr
     * @param value
     * @return
     */
    private int getNumber(int[] arr, int value) {
        int sum = 0;

        for (int num : arr) {
            if (num <= value) {
                sum += num;
            } else {
                sum += value;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        FindBestValue findBestValue = new FindBestValue();
        int bestValue = findBestValue.findBestValue2(new int[]{15, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 50);
        System.out.println(bestValue);
    }

    /**
     * 前缀和+O(1)函数解法
     * 对arr升序排序。复杂度O(N∙log(N))
     * 求arr的前缀和的过程中，遇到超过答案ans的arr[i]，就可以马上得出答案了（利用单调性，见分析）。此步复杂度：最坏情况O(N)，最优O(1)
     * 总的时间复杂度 O(N∙log(N)) 。
     * 因为系统***是原地算法，而前缀和不需要记录下来，故空间复杂度 O(1)O(1)。
     *
     * @param array
     * @param target
     * @return
     */
    public int findBestValue2(int[] array, int target) {
        Arrays.sort(array);
        int pre = 0;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int k = length - i;
            // 后面还有k项，将后面k向值均替换为现在的当前值，并加上pre前缀，和target做插
            int current = pre + array[i] * k - target;
            if (current >= 0) {
                // 等价于 a - 四舍五入(current÷k)
                return array[i] - (current + k / 2) / k;
            }
            pre += array[i];
        }
        // 无解，则取最后一个最大的元素
        return array[length - 1];
    }
}
