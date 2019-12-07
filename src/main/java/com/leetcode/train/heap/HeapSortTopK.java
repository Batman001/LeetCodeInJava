package com.leetcode.train.heap;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Batman create on 2019-12-07 1:25 上午
 * 可以采用小顶堆实现
 */
public class HeapSortTopK {
    static class Record {
        private String searchKey;

        public String getSearchKey(){
            return searchKey;
        }

        public void setSearchKey(String searchKey) {
            this.searchKey = searchKey;
        }

        public Record(){}
    }


    /**
     * 统计record url出现的频率 用map进行维护
     * @param record 待统计记录
     * @param map 统计结果map
     */
    private static void insert(Record record, Map<String, Integer> map) {
        String url = record.getSearchKey();
        if(map.containsKey(url)) {
            map.put(url, map.get(url) + 1);
        } else{
            map.put(url, 1);
        }
    }

    /**
     * 获取Topk记录的函数
     * @param map 保存记录及其出现频次的HashMap
     * @param k 出现频次为前k
     * @return TopK的Entry的信息
     */
    public static Map.Entry[] getTopKRecord(Map<String, Integer> map, int k) {
        int i = 0;
        // 因为数据量比较大 所以选择使用迭代器进行处理
        Iterator iter = map.entrySet().iterator();
        Map.Entry[] elements = new Map.Entry[k];
        while(iter.hasNext()) {
            Map.Entry e = (Map.Entry)iter.next();
            if(i < k) {
                elements[i] = e;
                if(i == k-1) {
                    // 如果目前elements元素个数为k个 则建立数量为k的小根堆
                    buildMinHeap(elements);
                }
                i++;
            } else {
                insertHeap(e, elements);
            }
        }
        return elements;
    }

    private static void insertHeap(Map.Entry e, Map.Entry[] heap) {
        if((int)e.getValue() > (int)heap[0].getValue()) {
            heap[0] = e;
            minHeap(heap, 0, heap.length);
        }


    }

    /**
     * 建堆过程
     * @param heap
     */
    public static void buildMinHeap(Map.Entry[] heap) {
        int i = heap.length / 2 - 1;
        for(; i>0; i--) {
            minHeap(heap, i, heap.length);
        }
    }

    /**
     * 对堆的索引为i的位置进行调整
     * @param heap 堆
     * @param i 索引位置
     * @param length 堆的大小
     */
    private static void minHeap(Map.Entry[] heap, int i, int length) {
        int left, right, min;

        left = i * 2 + 1;
        right = i * 2 + 2;
        min = i;
        // 如果左子树的节点值大于根节点值 则min设置为left 左子树索引位置
        if(left<=length-1 && (int)heap[left].getValue() < (int) heap[i].getValue()) {
            min = left;
        }
        // 如果右子树的节点值小于父节点和左子树的最小值 则min设置为right 右子树的索引位置
        if(right<length-1 && (int)heap[right].getValue() < (int)heap[min].getValue()) {
            min = right;
        }

        // 如果最小值没有位于父节点位置上  则交换父节点与最小值位置的节点  递归调整min位置的值
        if(min != i) {
            Map.Entry temp = heap[i];
            heap[i] = heap[min];
            heap[min] = temp;
            minHeap(heap, min, length);
        }

    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(8);
        File f = new File("./data/TopKTest.txt");
        BufferedReader reader = null;
        int k = 3;
        Record record = new Record();
        try{
            reader = new BufferedReader(new FileReader(f));
            String temp = "";
            while((temp=reader.readLine()) != null) {
                record.setSearchKey(temp);
                insert(record, map);
            }
            Iterator iter = map.entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry e = (Map.Entry) iter.next();
                System.out.println(e.getKey() + " " + e.getValue());
            }

            Map.Entry[] result = getTopKRecord(map, k);
            System.out.println("文件中TopK的值为：");
            for(Map.Entry<String, Integer> item : result) {
                System.out.println(item.getKey() + " " + item.getValue());
            }

        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }



    }


}
