package com.leetcode.train.sort;

/**
 * @author Batman create on 2019-12-08 10:40 上午
 * 快速排序算法
 * step1: 指定一个中轴
 * step2: 从右往左找，右边赋值给左边
 * step3: 从左往右找，左边赋值给右边
 */
public class QuickSort {

    /**
     * 注意：所有的while循环都要有 left < right
     * step1:找中轴
     * step2: 递归中轴左边快排
     * step3: 递归中轴右边快排
     * @param arr 待排序数组
     * @param left 排序左边界
     * @param right 右边有边界
     */
    private void quickSort(int[] arr, int left, int right) {
        if (left>=right) {
            return ;
        }

        int middleIndex = getMiddleIndex(arr, left, right);
        quickSort(arr, left, middleIndex-1);
        quickSort(arr, middleIndex+1, right);
    }


    private int getMiddleIndex(int[] arr, int left, int right) {
        int pivot = arr[left];
        while(left < right) {
            while(left < right && arr[right] > pivot) {
                right--;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] < pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        // 此时截止条件为 left=right 将pivot的值赋值给array[left] 或者array[right]
        arr[right] = pivot;
        return right;
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 3, 0, -5, -123, 90, 345, 234, 62, 8};
        QuickSort qs = new QuickSort();
        qs.quickSort(arr, 0, arr.length-1);
        for (int item:arr) {
            System.out.println(item + " ");
        }

    }


}
