package com.leetcode.train.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Batman create on 2019-05-29 10:01
 * 1019. 链表中的下一个更大节点（请注意是下一个更大节点 而不是全部节点中最大节点)
 * 示例1：
 * 输入：[2,1,5]
 * 输出：[5,5,0]
 * 示例2：
 * 输入：[2,7,4,3,5]
 * 输出：[7,0,5,5,0]
 * 示例3：
 * 输入：[1,7,5,1,9,2,5,1]
 * 输出：[7,9,9,9,0,5,0,0]
 */
public class NextLargerNode {
    private int[] nextLargerNodes(ListNode head){

        List<Integer> nums = new ArrayList<>();

        /* 遍历列表 得到全部node的val值 */
        while(head != null){
            nums.add(head.val);
            head = head.next;

        }

        /* 使用数组 存储 每个Node后最大的val值 */
        int[] res = new int[nums.size()];

        /* 递减栈 */
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<nums.size(); i++){
            while(!stack.isEmpty() && nums.get(stack.peek()) < nums.get(i)){
                res[stack.pop()] = nums.get(i);
            }
            stack.add(i);
        }

        return res;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode secNode = new ListNode(7);
        ListNode thirdNode = new ListNode(8);
        ListNode fourthNode = new ListNode(3);
        ListNode fifthNode = new ListNode(5);
        head.next = secNode;
        secNode.next = thirdNode;
        thirdNode.next = fourthNode;
        fourthNode.next = fifthNode;

        NextLargerNode solution = new NextLargerNode();

        int[] res =  solution.nextLargerNodes(head);
        for(int item:res){
            System.out.printf(item + " ");
        }
    }

}
