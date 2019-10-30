package com.leetcode.train.linkedlist;

/**
 * @author Batman on 2018/12/19.
 */
public class ListReverse {
    public static void main(String[] args){
        LinkedListArray llr1 = new LinkedListArray();
        llr1.setValue("链");
        LinkedListArray llr2 = new LinkedListArray();
        llr2.setValue("表");
        LinkedListArray llr3 = new LinkedListArray();
        llr3.setValue("逆");
        LinkedListArray llr4 = new LinkedListArray();
        llr4.setValue("置");
        llr1.setNext(llr2);
        llr2.setNext(llr3);
        llr3.setNext(llr4);
        print(llr1);
        llr1 = reverse(llr1);
        print(llr1);
    }
    public static void print(LinkedListArray head) {
        LinkedListArray tmp = head;
        while (tmp != null) {
            System.out.print(tmp.getValue());
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static LinkedListArray reverse(LinkedListArray listHead) {
        LinkedListArray lastList,//剩余链表
                tmpList, //暂存链表
                result;//结果链表引用
        lastList = listHead;
        tmpList = result = null;//暂存链表与结果集初始化为空
        while (lastList != null) {
            tmpList = lastList.next;//暂存链表暂存除首节点外的剩余链表
            lastList.next = result;//将首节点放入剩余链表头
            result = lastList;//引用指向链表第一个位置
            lastList = tmpList;//剩余链表引用重新指向暂存的剩余链表
        }
        return result;
    }

}

