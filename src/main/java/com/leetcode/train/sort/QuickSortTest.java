package com.leetcode.train.sort;

import java.util.Arrays;

/**
 * <p>快排 Bug Free版本</p>
 *
 * @author : sunchao
 * date : 2020-11-19 14:04
 **/
public class QuickSortTest {

    /**
     * 递归对从0到middleIndex-1 进行排序
     * 首先找到中间位置，该位置的左边都要比该值小，该位置的右边都要比该值大
     * 递归排序（left， middleIndex-1)
     * 递归排序（middleIndex+1, right)
     * @param array 待排序数组
     * @param left 左指针
     * @param right 右指针
     */
    private void quickSort(int[] array, int left, int right) {
        if(left >= right) {
            return ;
        }
        int middleIndex = getMiddleIndex(array, left, right);

        quickSort(array, left, middleIndex - 1);
        quickSort(array, middleIndex + 1, right);


    }
    /**
     * 寻找数组中间位置的元素(找中轴)
     * @param array 待排序数组
     * @param left 左边开始位置索引
     * @param right 右边结束位置索引
     * @return 中间位置，该位置左边都要比该值小，右边都要比该值大
     */
    private int getMiddleIndex(int[] array, int left, int right) {

        int pivot = array[left];

        while(left < right) {

            // 从右向左进行比对
            while(left < right && array[right] > pivot) {
                right -=1;
            }

            // 如果右边比pivot大，则将右边值 赋给左边
            array[left] = array[right];

            // 然后从左向右开始比对
            while(left < right && array[left] < pivot) {
                left += 1;
            }
            // 如果左边比pivot大，将左边值赋给右边
            array[right] = array[left];
        }

        // 此时截止条件为 left = right 此时跳出循环， 将pivot 赋值为array[left] 或者 array[right] 相同
        array[left] = pivot;
        return left;
    }


    public static void main(String[] args) {

        int[] testArray = {1, 0, -3, -2, 3, 2, 5};
        QuickSortTest qs = new QuickSortTest();
        qs.quickSort(testArray, 0, testArray.length-1);
        for (int item: testArray) {
            System.out.println(item + " ");
        }

    }

}
