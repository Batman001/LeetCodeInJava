package com.leetcode.train.heap;


/**
 * @author Batman on 2018/12/19.
 * 大根堆的类 即父节点的值大于等于任何子节点的值
 *
 * 第一步：先n个元素的无序序列，构建成大顶堆
 *
 * 第二步：将根节点与最后一个元素交换位置，（将最大元素"沉"到数组末端）
 *
 * 第三步：交换过后可能不再满足大顶堆的条件，所以需要将剩下的n-1个元素重新构建成大顶堆
 *
 * 第四步：重复第二步、第三步直到整个数组排序完成
 *
 */
class MaxHeap {
    int[] heap;
    private int heapSize;
    MaxHeap(int[] array) {
        this.heap=array;
        this.heapSize=heap.length;
    }

    /**
     * 根据提供的列表进行建堆的函数
     * 从最后一个非叶子节点开始进行建堆 及从 heapSize/2 - 1的位置开始进行调整堆
     * 建堆只是保证了大根堆的父节点大于左右子节点
     */
    void buildMaxHeap() {
        for(int i=heapSize / 2 - 1; i>=0; i--) {
            //依次向上将当前子树最大堆化
            maximize(i);
        }
    }

    /**
     * 堆排列方法,动态调整堆的过程
     * 不断将堆顶的元素与堆尾的元素进行互换 然后动态调整堆顶元素实现堆排序过程
     */
    void heapSort() {
        for(int i=0;i<heap.length;i++) {
            //执行n次，将每个当前最大的值放到堆末尾
            int tmp=heap[0];
            heap[0]=heap[heapSize-1];
            heap[heapSize-1]=tmp;
            heapSize--;
            maximize(0);
        }
    }

    /**
     * 调整索引位置为i的堆得分布情况
     * @param i 需要调整位置的索引i 即父节点的位置
     */
    private void maximize(int i) {
        int l = left(i);
        int r = right(i);
        // 使用largest记录最大值的位置索引
        int largest;

        // 如果左孩子节点的大于父节点的值 则进行互换
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

        //交换i与largest对应的元素位置，在largest位置递归调用 maxmize
        int tmp=heap[i];
        heap[i]=heap[largest];
        heap[largest]=tmp;
        maximize(largest);
    }
    private void increaseValue(int i,int val) {
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
     * i位置的索引从零开始计数 返回索引为i的时候 父节点的索引
     * @param i 位置索引
     * @return 返回父节点的索引
     */
    private int parent(int i) {
        return (i-1)/2;
    }

    /**
     * 索引为i位置的左子树的索引
     * @param i 位置索引i
     * @return 返回左子树的索引
     */
    private int left(int i) {
        return 2*(i+1)-1;
    }

    /**
     * 索引为i位置的右子树的索引
     * @param i 位置索引i
     * @return 返回右子树的索引
     */
    private int right(int i) {
        return 2*(i+1);
    }
}
