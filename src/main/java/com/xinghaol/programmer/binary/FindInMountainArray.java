package com.xinghaol.programmer.binary;

/**
 * @author: lixinghao
 * @date: 2020/4/29 7:01 下午
 * @Description: 1095. 山脉数组中查找目标值
 * https://leetcode-cn.com/problems/find-in-mountain-array/
 */
public class FindInMountainArray {
    /**
     * 顺序写的代码时间就比较长
     *
     * @param target
     * @param mountainArr
     * @return
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int length = mountainArr.length();
        int left = 0;
        int right = length - 1;

        int mountainTop = fun1(mountainArr, left, right);
        if (mountainArr.get(mountainTop) == target) {
            return mountainTop;
        }
        int res = fun2(mountainArr, left, mountainTop - 1, target);
        if (res != -1) {
            return res;
        }
        res = fun3(mountainArr, mountainTop + 1, right, target);
        if (res != -1) {
            return res;
        }

        return -1;
    }

    private int fun1(MountainArray mountainArray, int left, int right) {
        // 首先查找山脉数组的峰顶
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 说明右侧是有序的，处于递减的半边区间
            if (mountainArray.get(mid) > mountainArray.get(mid + 1)) {
                right = mid;
            } else {
                // 说明左侧是有序的，处于递增的半边区间
                left = mid + 1;
            }
        }

        return left;
    }

    private int fun2(MountainArray mountainArray, int left, int right, int target) {
        // 首先从左侧寻找是否存在target
        int ll = left;
        int lr = right;
        while (ll < lr) {
            int mid = ll + (lr - ll + 1) / 2;
            if (mountainArray.get(mid) > target) {
                lr = mid - 1;
            } else {
                ll = mid;
            }
        }
        if (mountainArray.get(ll) == target) {
            return ll;
        }

        return -1;
    }

    private int fun3(MountainArray mountainArray, int left, int right, int target) {
        // 左侧不存在target，则再在右面区间寻找
        int rl = left;
        int rr = right;
        while (rl < rr) {
            int mid = rl + (rr - rl) / 2;
            if (mountainArray.get(mid) > target) {
                rl = mid + 1;
            } else {
                rr = mid;
            }
        }
        if (mountainArray.get(rl) == target) {
            return rl;
        }

        return -1;
    }


    // 特别注意：3 个辅助方法的分支出奇地一样，因此选中位数均选左中位数，才不会发生死循环
    public int findInMountainArray2(int target, MountainArray mountainArr) {
        int size = mountainArr.length();
        // 步骤 1：先找到山顶元素所在的索引
        int mountaintop = findMountaintop(mountainArr, 0, size - 1);
        // 步骤 2：在前有序且升序数组中找 target 所在的索引
        int res = findFromSortedArr(mountainArr, 0, mountaintop, target);
        if (res != -1) {
            return res;
        }
        // 步骤 3：如果步骤 2 找不到，就在后有序且降序数组中找 target 所在的索引
        return findFromInversedArr(mountainArr, mountaintop + 1, size - 1, target);
    }

    private int findMountaintop(MountainArray mountainArr, int l, int r) {
        // 返回山顶元素
        while (l < r) {
            int mid = l + (r - l) / 2;
            // 取左中位数，因为进入循环，数组一定至少有 2 个元素
            // 因此，左中位数一定有右边元素，数组下标不会发生越界
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                // 如果当前的数比右边的数小，它一定不是山顶
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        // 根据题意，山顶元素一定存在，因此退出 while 循环的时候，不用再单独作判断
        return l;
    }

    private int findFromSortedArr(MountainArray mountainArr, int l, int r, int target) {
        // 在前有序且升序数组中找 target 所在的索引
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mountainArr.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }

        }
        // 因为不确定区间收缩成 1个数以后，这个数是不是要找的数，因此单独做一次判断
        if (mountainArr.get(l) == target) {
            return l;
        }
        return -1;
    }

    private int findFromInversedArr(MountainArray mountainArr, int l, int r, int target) {
        // 在后有序且降序数组中找 target 所在的索引
        while (l < r) {
            int mid = l + (r - l) / 2;
            // 与 findFromSortedArr 方法不同的地方仅仅在于由原来的小于号改成大于好
            if (mountainArr.get(mid) > target) {
                l = mid + 1;
            } else {
                r = mid;
            }

        }
        // 因为不确定区间收缩成 1个数以后，这个数是不是要找的数，因此单独做一次判断
        if (mountainArr.get(l) == target) {
            return l;
        }
        return -1;
    }

    interface MountainArray {
        public int get(int index);

        public int length();
    }
}
