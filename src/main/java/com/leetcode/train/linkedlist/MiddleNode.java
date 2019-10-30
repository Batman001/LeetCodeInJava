package com.leetcode.train.linkedlist;

/**
 * @author Batman create on 2019-05-27 09:57
 * 876. 链表的中间结点
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 */
public class MiddleNode {

    public ListNode findMiddleNode(ListNode head){

        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        // 通过最后判断fast指针位置判断 应该是返回slow 或者 返回slow.next

        if(fast.next == null){
            return slow;
        }else{
            return slow.next;
        }
    }
}
