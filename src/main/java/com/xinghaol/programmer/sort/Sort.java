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
     * <p>
     * 希尔排序时基于插入排序的一下两点性质提出的改进方法：
     * 1、插入排序在对几乎已经排好序的数据操作时，效率高，即可达到线性排序的效率。
     * 2、但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位。
     * <p>
     * 基本思想：先将整个待排序的记录序列分隔成若干个子序列分别进行直接插入排序，待整个序列中的记录"基本有序"时，在对全体记录一次进行插入排序
     * <p>
     * 1、选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
     * 2、按增量序列个数 k，对序列进行 k 趟排序；
     * 3、每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * <p>
     * 感觉应该不是特别重要
     *
     * @param sourceArray
     * @return
     */
    public int[] shellSort(int[] sourceArray) {
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);
        int gap = 1;

        while (gap < array.length / 3) {
            gap = gap * 3 + 1;
        }

        while (gap > 0) {
            for (int i = gap; i < array.length; i++) {
                int tmp = array[i];
                int j = i - gap;
                while (j >= 0 && array[j] > tmp) {
                    array[j + gap] = array[j];
                    j -= gap;
                }
                array[j + gap] = tmp;
            }

            gap = (int) Math.floor(gap / 3);
        }

        return array;
    }

    /**
     * 归并排序是建立在归并操作上的一种有效的排序算法，算法采用分而治之的一个非常典型的应用。始终都是O(nlogn)的时间复杂度
     * 需要额外的内存空间
     * <p>
     * 1、申请空间，使其大小为两个已经排序序列之和，该空间用来存在合并后的排序
     * 2、设定两个指针，最初位置分别为两个已经排序序列的起始位置；
     * 3、比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针指向下一个位置；
     * 4、重复步骤3知道某一个指针到达序列尾；
     * 5、将另一个序列剩下的所有元素直接复制到合并序列队尾
     *
     * @param sourceArray
     * @return
     */
    public int[] mergeSort(int[] sourceArray) {
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);

        if (array.length < 2) {
            return array;
        }
        int middle = (int) Math.floor(array.length / 2);
        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

    /**
     * 快速排序，Worst Case 的时间复杂度达到了 O(n²)，但是人家就是优秀，在大多数情况下都比平均时间复杂度为 O(n logn) 的排序算法表现要更好
     * <p>
     * 1、从数列中挑出一个元素，称为 “基准”（pivot）;
     * 2、重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
     * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 3、递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     * <p>
     * 递归的最底部情形，是数列的大小是零或一，也就是永远都已经被排序好了。
     * 虽然一直递归下去，但是这个算法总会退出，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。
     * <p>
     * 平均时间复杂度：O(nlogn)
     * 最差时间复杂度：O(N^2)  全部有序
     * 最好时间复杂度：O(nlogn)
     * 空间复杂度：O(nlogn)
     * 不稳定的排序算法
     *
     * @param sourceArray
     * @return
     */
    public int[] quickSort(int[] sourceArray) {
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);

        return quickSort(array, 0, array.length - 1);
    }

    private int[] quickSort(int[] array, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(array, left, right);
            quickSort(array, left, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, right);
        }

        return array;
    }

    /**
     * 从privot后面第一个元素开始比较，如果比它小，则交换到index的位置，比他大则继续向后遍历。极限状态下，后面全部比当前元素大，
     * 则不需要交换，index为pivot + 1，最后交换swap(array, pivot, index - 1);当前元素在原位置不变
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] array, int left, int right) {
        int pivot = left;
        int index = pivot + 1;

        for (int i = index; i <= right; i++) {
            if (array[i] < array[pivot]) {
                swap(array, i, index);
                index++;
            }
        }
        swap(array, pivot, index - 1);

        return index - 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 堆排序是利用对这种数据结构设计的一种排序算法。堆姐姐一个完全二叉树的结构。
     * 大顶堆/小根堆：每个节点的值都大于或等于其子节点的值，在堆排序算法中用于升序排列；
     * 小顶堆/大根堆：每个节点的值都小于或等于其子节点的值，在堆排序算法中用于降序排列；
     * <p>
     * 1、将待排序序列构建成一个堆 H[0……n-1]，根据（升序降序需求）选择大顶堆或小顶堆；
     * 2、把堆首（最大值）和堆尾互换；
     * 3、把堆的尺寸缩小 1，并调用 shift_down(0)，目的是把新的数组顶端数据调整到相应位置；
     * 4、重复步骤 2，直到堆的尺寸为 1。
     *
     * @param sourceArray
     * @return
     */
    public int[] heapsort(int[] sourceArray) {
        int[] array = Arrays.copyOf(sourceArray, sourceArray.length);
        int length = array.length;
        buildMaxHeap(array, length);

        for (int i = length - 1; i > 0; i--) {
            swap(array, 0, i);
            length--;
            heapfiy(array, 0, length);
        }

        return array;
    }

    /**
     * 建立一个大顶堆
     *
     * @param array
     * @param length
     */
    private void buildMaxHeap(int[] array, int length) {
        // 因为heapfiy做了乘以2操作，所以这里需要除以2
        for (int i = (int) Math.floor(length / 2); i >= 0; i--) {
            heapfiy(array, i, length);
        }
    }

    private void heapfiy(int[] array, int i, int length) {
        // 下标从0开始，左右孩子的下标值
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < length && array[left] > array[largest]) {
            largest = left;
        }
        if (right < length && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(array, i, largest);
            heapfiy(array, largest, length);
        }
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

        int[] mergeSort = sort.mergeSort(new int[]{10, 3, 2, 45, 44, 32, 556, 32, 445, 221, 55432, 4453, 22, 34, 332, 43});
        System.out.println(Arrays.toString(mergeSort));

        int[] quickSort = sort.quickSort(new int[]{2, 4, 3, 5, 48, 46, 15, 19, 36, 38, 47, 44});
        System.out.println(Arrays.toString(quickSort));

        int[] heapSort = sort.heapsort(new int[]{91, 60, 96, 13, 35, 65, 46, 64, 10, 30, 20, 31, 65, 46, 22});
        System.out.println(Arrays.toString(heapSort));
    }
}
