package com.leetcode.train.heap;

/**
 * @author Batman on 2018/12/19.
 */
public class MaxHeapSortTestDemo {
    public static void main(String[] args){
        int [] array = {1,3,2,48,70,18,999,101,124,216};
        MaxHeapTest heap = new MaxHeapTest(array);
        heap.buildHeap();
        heap.heapSort();
        printHeap(heap.heap);

    }

    /**
     * 打印数组中的元素
     * @param array 待打印的数组array
     */
    private static void printHeap(int[] array) {
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+ " ");
        }
        System.out.println();
    }
}

