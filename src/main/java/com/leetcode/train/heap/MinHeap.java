package com.leetcode.train.heap;

/**
 * @author Batman create on 2019-12-07 2:21 下午
 * 小根堆
 * 第一步：先n个元素的无序序列，构建成小顶堆
 *
 * 第二步：将根节点与最后一个元素交换位置，（将最小元素"沉"到数组末端）
 *
 * 第三步：交换过后可能不再满足小顶堆的条件，所以需要将剩下的n-1个元素重新构建成小顶堆
 *
 * 第四步：重复第二步、第三步直到整个数组排序完成
 */
public class MinHeap {
    private int[] heap;
    private int heapSize;
    private MinHeap(int[] array) {
        this.heap = array;
        this.heapSize = array.length;
    }

    /**
     * 建立小根堆的过程
     * 建完小根堆的过程只是完成了 小根堆的父节点小于左右子节点的过程  并没有完成排序过程
     */
    private void buildMinHeap() {
        // 从最后一个非叶子节点开始进行堆的调整 start to build the min heap
        for(int i = heapSize / 2 - 1; i>=0; i--) {
            minAdjustHeap(i);
        }

    }


    /**
     * 动态调整固定索引位置的堆节点
     * @param index 索引位置
     */
    private void minAdjustHeap(int index) {
        int leftIndex = index * 2 + 1;
        int rightIndex = index * 2 + 2;
        int minIndex = index;
        if(leftIndex < heapSize && heap[leftIndex] < heap[index]) {
            minIndex = leftIndex;
        }
        if(rightIndex < heapSize && heap[rightIndex] < heap[minIndex]) {
            minIndex = rightIndex;
        }

        // 如果最小值不是父节点 需要调整heap 使index与minIndex交换位置
        if(minIndex != index) {
            int temp = heap[index];
            heap[index] = heap[minIndex];
            heap[minIndex] = temp;

            // 动态调整minIndex位置
            minAdjustHeap(minIndex);
        }

    }


    /**
     * 通过不断交换堆顶元素和堆底元素 实现对于小根堆的排序
     * 共执行n次
     */
    private void heapSort() {
        for(int i=0; i<heap.length; i++) {
            // 不断交换堆顶元素和堆尾元素 然后动态调增堆顶元素即可
            int temp = heap[0];
            heap[0] = heap[heapSize-1];
            heap[heapSize-1] = temp;

            heapSize--;
            minAdjustHeap(0);
        }
    }

    /**
     * 将数组按照建立完全二叉树的形式进行打印
     * @param array 输入数组
     */
    private static void printHeapTree(int[] array) {
        for (int i = 1; i < array.length; i = i * 2) {
            for (int k = i - 1; k < 2 * i - 1 && k < array.length; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int [] array = {1,3,2,4,8,10,9,18,89,100};

        MinHeap heap=new MinHeap(array);
        System.out.println("执行最小堆化前堆的结构：");
        printHeapTree(heap.heap);
        heap.buildMinHeap();
        System.out.println("执行最小堆化后堆的结构：");
        printHeapTree(heap.heap);
        heap.heapSort();
        System.out.println("执行堆排序后数组的内容");
        printHeapTree(heap.heap);
    }

}
