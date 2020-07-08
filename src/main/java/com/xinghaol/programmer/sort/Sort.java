package com.xinghaol.programmer.sort;

import java.util.Arrays;

/**
 * @author: lixinghao
 * @date: 2020/7/7 10:36 上午
 * @Description: 排序算法
 */
public class Sort {
    /**
     * 1、比较相邻的元素，如果第一个比第二个大，就交换他们两个；
     * 2、对每一个相邻元素同样的工作，从开始第一对到结尾的最后一对。这步骤完成后，最后的元素会是最大的数。
     * 3、针对所有的元素重复以上的步骤，除了最后一个；
     * 4、持续每次对越来越少的元素重复上述步骤，直到没有任何一对数组需要比较；
     * <p>
     * 平均时间复杂度：O(N^2)
     * 最坏时间复杂度：O(N^2)
     * 最好时间复杂度：O(1)
     * 空间复杂度：O(1)
     * 稳定排序
     *
     * @param sourceArray
     * @return
     */
    public int[] bubbleSort(int[] sourceArray) {
        // 对sourceArray进行拷贝，不改变参数内容
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 1; i < array.length; i++) {
            // 用于记录某一趟的过程是否没有交换，如果没有交换，则说明全部已经有序了
            boolean flag = true;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;

                    flag = false;
                }
            }

            if (flag) {
                break;
            }
        }

        return array;
    }

    /**
     * 选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。所以用到它的时候，数据规模越小越好。
     * 唯一的好处可能就是不占用额外的内存空间了吧。
     * 1、首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
     * 2、再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 3、重复第二步，直到所有元素均排序完毕。
     * <p>
     * 平均时间复杂度：O(N^2)
     * 最差时间复杂度：O(N^2)
     * 最好时间复杂度：O(N^2)
     * 空间复杂度O(1)
     * 不稳定的排序
     *
     * @param sourceArray
     * @return
     */
    public int[] selectSort(int[] sourceArray) {
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);

        // 总共经过N-1轮的比较
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    // 记录当前能找到的最小元素的下标
                    min = j;
                }
            }

            if (i != min) {
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
        }

        return array;
    }

    /**
     * 插入排序是一种最简单直观的排序算法，它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * <p>
     * 1、将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
     * 2、从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。
     * （如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
     * <p>
     * 平均时间复杂度：O(N^2)
     * 最差时间复杂度：O(N^2)
     * 最好时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * 不稳定的排序
     *
     * @param sourceArray
     * @return
     */
    public int[] insertSort(int[] sourceArray) {
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);

        // 认为下标为0的开始就是排好顺序的
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i;
            while (j > 0 && tmp < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            // 说明有数据的交换
            if (j != i) {
                array[j] = tmp;
            }
        }

        return array;
    }

    /**
     * 希尔排序，也称递减增量排序算法，是插入排序的一种更高效的改进版本。但希尔排序是非稳定排序算法。
     *
     * @param sourceArray
     * @return
     */
    public int[] shellSort(int[] sourceArray) {
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);

        return array;
    }

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] bubbleSort = sort.bubbleSort(new int[]{3, 5, 38, 15, 36, 26, 27, 2, 4, 19, 44, 46, 47, 50, 48});
        System.out.println(Arrays.toString(bubbleSort));

        int[] selectSort
                = sort.selectSort(new int[]{2, 4, 3, 5, 48, 46, 15, 19, 36, 38, 47, 44});
        System.out.println(Arrays.toString(selectSort));

        int[] insertSort = sort.insertSort(new int[]{10, 3, 2, 45, 44, 32, 556, 32, 445, 221, 55432, 4453, 22, 34, 332, 43});
        System.out.println(Arrays.toString(insertSort));
    }
}
