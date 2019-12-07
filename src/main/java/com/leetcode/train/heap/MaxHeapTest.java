package com.leetcode.train.heap;

/**
 * @author Batman on 2018/12/19.
 */
class MaxHeapTest {
    int [] heap;
    private int heapSize;

    MaxHeapTest(int[] array){
        this.heap = array;
        this.heapSize = array.length;
    }

    void buildHeap(){
        for(int i=heapSize/2 -1; i>=0; i--){
            magnify(i);
        }
    }

    void heapSort(){
        for (int i=0;i<heap.length;i++){
            //执行n次,将堆尾元素与堆头元素交换,然后动态调整堆
            int temp = heap[0];
            heap[0] = heap[heapSize-1];
            heap[heapSize -1] = temp;
            heapSize --;
            magnify(0);
        }
    }

    private void magnify(int i){
        int left = left(i);
        int right = right(i);
        int largest;
        if(left < heapSize && heap[left]>heap[i]) {
            largest = left;
        }
        else{
            largest = i;
        }
        if(right < heapSize && heap[right]>heap[largest]) {
            largest = right;
        }
        if(largest == i || largest >= heapSize) {
            return;
        }
        int temp = heap[i];
        heap[i] = heap[largest];
        heap[largest] = temp;
        magnify(largest);
    }

    private int left(int i) {
        return i*2+1;
    }

    private int right(int i){
        return i*2+2;
    }
}

