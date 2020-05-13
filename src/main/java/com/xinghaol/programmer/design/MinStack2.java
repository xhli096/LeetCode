package com.xinghaol.programmer.design;

/**
 * @author: lixinghao
 * @date: 2020/5/12 10:45 上午
 * @Description:
 */
public class MinStack2 {
    int[] value;
    int[] minValue;
    int capacity = 10;
    int size = 0;

    /**
     * initialize your data structure here.
     */
    public MinStack2() {
        value = new int[capacity];
        minValue = new int[capacity];
    }

    public void push(int x) {
        if (size >= capacity) {
            resize();
        }

        value[size] = x;

        if (size == 0) {
            minValue[0] = x;
        } else {
            minValue[size] = x < minValue[size - 1] ? x : minValue[size - 1];
        }

        size++;
    }

    public void pop() {
        size--;
    }

    public int top() {
        return value[size - 1];
    }

    public int getMin() {
        return minValue[size - 1];
    }

    private void resize() {
        capacity *= 1.5;

        int[] copyValue = new int[capacity];
        int[] copyMinValue = new int[capacity];

        System.arraycopy(value, 0, copyValue, 0, size);
        System.arraycopy(minValue, 0, copyMinValue, 0, size);

        value = copyValue;
        minValue = copyMinValue;
    }
}
