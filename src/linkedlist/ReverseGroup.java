package linkedlist;

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
public class ReverseGroup {
    public ListNode reverseGroup(ListNode head, int k){
        if(k<=1){
            return head;
        }
        ListNode header = new ListNode(0);
        return null;
    }
}
