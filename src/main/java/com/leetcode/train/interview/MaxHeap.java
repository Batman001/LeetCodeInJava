package com.leetcode.train.interview;

/**
 * @author Batman create on 2019-12-13 6:26 下午
 */
public class MaxHeap {
    private int[] nums;
    private int heapSize;

    private MaxHeap(int[] nums) {
        this.nums = nums;
        this.heapSize = nums.length;
    }


    private void buildHeap() {
        for(int i=heapSize/2 - 1; i>=0; i--){
            maxChange(i);
        }
    }

    private void maxChange(int index) {
        int leftIndex = index * 2 + 1;
        int rightIndex = index * 2 + 2;
        int maxIndex = index;

        if(leftIndex < heapSize && nums[leftIndex] > nums[index]) {
            maxIndex = leftIndex;
        }

        if(rightIndex < heapSize && nums[rightIndex] > nums[maxIndex]) {
            maxIndex = rightIndex;
        }


        if(maxIndex != index) {
            int temp = nums[index];
            nums[index] = nums[maxIndex];
            nums[maxIndex] = temp;
            maxChange(maxIndex);
        }

    }


    private void heapSort() {
        for(int i=0; i<nums.length; i++) {
            int temp = nums[0];
            nums[0] = nums[heapSize - 1];
            nums[heapSize - 1] = temp;

            heapSize --;
            maxChange(0);

        }
    }

    private void printNum(){
        for (int item: nums) {
            System.out.print(item + ",");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        int[] nums = {1,3,2,48,70,18,999,101,124,216};
        MaxHeap heap = new MaxHeap(nums);

        System.out.println("未建立堆之前的数组:");
        heap.printNum();

        heap.buildHeap();
        System.out.println("建立堆之后的数组为:");
        heap.printNum();

        heap.heapSort();
        System.out.println("经过堆排序之后的数组为:");
        heap.printNum();

    }

}
