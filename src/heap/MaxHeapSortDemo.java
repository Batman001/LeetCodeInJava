package heap;

/**
 * Created by sunchao on 2018/12/19.
 */
public class MaxHeapSortDemo {
    public static void main(String[] args){
        int [] array = {1,2,3,4,7,8,9,10,14,16};

        MaxHeap heap=new MaxHeap(array);
        System.out.println("执行最大堆化前堆的结构：");
        printHeapTree(heap.heap);
        heap.buildMaxHeap();
        System.out.println("执行最大堆化后堆的结构：");
        printHeapTree(heap.heap);
        heap.heapSort();
        System.out.println("执行堆排序后数组的内容");
        printHeap(heap.heap);

    }

    private static void printHeapTree(int[] array)
    {
        for(int i=1;i<array.length;i=i*2)
        {
            for(int k = i-1; k < 2 * i - 1 && k < array.length; k++)
            {
                System.out.print(array[k] + " ");
            }
            System.out.println();
        }
    }
    private static void printHeap(int[] array)
    {
        for(int i=0;i<array.length;i++)
        {
            System.out.print(array[i]+" ");
        }
    }

}
