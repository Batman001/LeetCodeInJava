package com.leetcode.train.linkedlist;

/**
 * @author Batman on 2018/12/19.
 */
public class RemoveNthFromEndSolution {

    /**
     * 移除从后面数第n的链表的元素
     * @param head 链表head
     * @param n 第n个元素
     * @return 返回链表的首
     */
    public ListNode removeNthFromEndFunction(ListNode head, int n){
        // 一次遍历实现

        // 需要采用两个指针实现
        // 第一个pre指针找到第N个节点,然后让后一个last指针指向头结点,然后pre和last指针一起走
        // 直至pre指针继续至末尾,此时last指针就是倒数第N+1个节点
        ListNode pre = head;
        ListNode last = head;

        // 找到第N个节点
        for(int i=0;i<n && pre != null; i++){
            pre = pre.next;
        }

        if(pre == null){
            head = head.next;
            return head;
        }

        // 此时pre指针和last指针相差n-1个节点
        // 当pre.next为null的时候 last在倒数第N+1个位置
        while(pre.next != null){
            pre = pre.next;
            last = last.next;
        }

        // last.next = last.next.next;

        return head;
    }


    public static void main(String[] args) {

    }


}
