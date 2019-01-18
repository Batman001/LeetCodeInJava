package heap;

/**
 * Created by Batman on 2018/12/19.
 */
public class MaxHeapTest {
    int [] heap;
    int heapSize;

    public MaxHeapTest(int[] array){
        this.heap = array;
        this.heapSize = array.length;
    }

    public void buildHeap(){
        for(int i=heapSize/2 -1; i>=0; i--){
            maxify(i);
        }
    }

    public void heapSort(){
        for (int i=0;i<heap.length;i++){
            //执行n次,将堆尾元素与堆头元素交换,然后动态调整堆
            int temp = heap[0];
            heap[0] = heap[heapSize-1];
            heap[heapSize -1] = temp;
            heapSize --;
            maxify(0);
        }
    }

    public void maxify(int i){
        int left = Left(i);
        int right = Right(i);
        int largest;
        if(left < heapSize && heap[left]>heap[i])
            largest = left;
        else
            largest = i;
        if(right < heapSize && heap[right]>heap[largest])
            largest = right;
        if(largest == i || largest >= heapSize)
            return;
        int temp = heap[i];
        heap[i] = heap[largest];
        heap[largest] = temp;
        maxify(largest);
    }

    private int Left(int i) {
        return i*2+1;
    }

    private int Right(int i){
        return i*2+2;
    }
}

