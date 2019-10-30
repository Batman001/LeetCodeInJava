package com.leetcode.train.linkedlist;

/**
 * @author Batman create on 2019-05-30 14:18
 * leetcode 25 K 个一组翻转链表
 * 示例：
 * 给定这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 */
public class ReverseKGroup {
    public static ListNode reverseKGroup(ListNode head, int k){
        if(k<=1){
            return head;
        }

        // 获取链表的长度
        int length = 0;
        ListNode p = head;
        while(p != null){
            length += 1;
            p = p.next;
        }

        ListNode curNode = head;

        /* cnt 表示已经翻转过的节点个数 */
        int cnt = 0;

        // 设置链表的边界 curNode 不能越界
        while(curNode != null){

            // 判断如果 后面所剩节点数不足k个 则不需要翻转
            if(cnt + k <= length){
                // 分情况讨论 如果是第一次翻转
                if(cnt == 0){
                    head = reverseK(curNode, k);
                    cnt += k;
                }
                // 如果不是第一次翻转
                else{
                    // 获取上一次翻转节点后的下一个节点
                    ListNode nextNode = curNode.next;

                    // 调用reverseK函数 得到下一次翻转后的头结点newHead
                    // 同时更新nextNode节点到下一次翻转后的尾节点
                    ListNode newHead = reverseK(nextNode, k);

                    // 翻转了K个，数量增加K个
                    cnt += k;

                    // 设置上次翻转的尾指针指向本次翻转的头指针
                    curNode.next = newHead;

                    // curNode 向前移动k个位置 到达本次翻转后的尾指针
                    curNode = nextNode;

                }
            }else{
                break;
            }
        }
        return head;

    }

    /**
     * 对以head为头指针的链表的进行有条件的翻转
     * @param head 链表头指针
     * @param k 翻转节点个数
     * @return 翻转后链表的头结点
     */
    private static ListNode reverseK(ListNode head, int k) {
        ListNode curNode = head;
        ListNode pre = null;
        ListNode reverseTail = head;
        int count = 1;


        while(count <= k){
            // 首先保存当前节点的下一个节点
            ListNode nextNode = curNode.next;
            curNode.next = pre;
            pre = curNode;
            curNode = nextNode;
            reverseTail = nextNode;
            count += 1;
        }
        head.next = reverseTail;

        return pre;

    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1000);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(88);
        ListNode n9 = new ListNode(9);
        ListNode n10 = new ListNode(100);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n9.next = n10;

        ListNode res = reverseKGroup(head, 3);
        LinkedListFunction function = new LinkedListFunction();
        function.printNode(res);
    }
}
