package heap;

import org.jetbrains.annotations.Contract;

/**
 * @author Batman
 * Created by Batman on 2018/12/19.
 * 大根堆的类
 */
public class MaxHeap {
    int[] heap;
    int heapSize;
    public MaxHeap(int[] array)
    {
        this.heap=array;
        this.heapSize=heap.length;
    }

    /**
     * 根据提供的列表进行建堆的函数
     */
    public void buildMaxHeap()
    {
        for(int i=heapSize / 2 - 1; i>=0; i--)
        {
            //依次向上将当前子树最大堆化
            maxify(i);
        }
    }

    /**
     * 堆排列方法,动态调整堆的过程
     */
    public void heapSort()
    {
        for(int i=0;i<heap.length;i++)
        {
            //执行n次，将每个当前最大的值放到堆末尾
            int tmp=heap[0];
            heap[0]=heap[heapSize-1];
            heap[heapSize-1]=tmp;
            heapSize--;
            maxify(0);
        }
    }

    /**
     * 调整索引位置为i的堆得分布情况
     * @param i
     */
    public void maxify(int i)
    {
        int l=left(i);
        int r=right(i);
        int largest;

        if(l<heapSize&&heap[l]>heap[i]){
            largest=l;
        }
        else{
            largest=i;
        }

        if(r<heapSize&&heap[r]>heap[largest]){
            largest=r;
        }

        //如果largest等于i说明i是最大元素 largest超出heap范围说明不存在比i节点大的子女
        if(largest==i||largest>=heapSize){
            return ;
        }

        //交换i与largest对应的元素位置，在largest位置递归调用maxify
        int tmp=heap[i];
        heap[i]=heap[largest];
        heap[largest]=tmp;
        maxify(largest);
    }
    public void increaseValue(int i,int val)
    {
        heap[i]=val;
        if(i>=heapSize||i<=0||heap[i]>=val){
            return;
        }

        int p=parent(i);
        if(heap[p]>=val){
            return;
        }

        heap[i]=heap[p];
        increaseValue(p, val);
    }

    /**
     * i位置的索引从零开始计数
     * @param i
     * @return
     */
    @Contract(pure = true)
    private int parent(int i)
    {
        return (i-1)/2;
    }

    @Contract(pure = true)
    private int left(int i)
    {
        return 2*(i+1)-1;
    }

    @Contract(pure = true)
    private int right(int i)
    {
        return 2*(i+1);
    }
}
