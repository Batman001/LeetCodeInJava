package linkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Batman on 2018/12/19.
 */
public class MergedKSortedLists {

    /**
     * time:O(nlogk) where k is the number of linked lists
     * space:O(n)
     *
     * @param lists
     * @return
     */
    public ListNode mergeKSortedList(ListNode[] lists){

        if(lists == null || lists.length ==0 ) return null;
        return sort(lists, 0, lists.length-1);
    }

    public ListNode sort(ListNode[] lists, int low, int high) {
        if(low>=high)
            return lists[low];
        int mid = (high-low)/2 + low;
        ListNode l1 = sort(lists, low, mid);
        ListNode l2 = sort(lists, mid+1, high);
        return merge(l1,l2);
    }

    public ListNode merge(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = merge(l1.next, l2);
            return l1;
        }
        l2.next = merge(l1, l2.next);
        return l2;
    }

    public ListNode mergeNoRecursion(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l2;

        ListNode dummy = new ListNode(0);
        dummy.next = l1;
        ListNode cur = dummy;

        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                l1 = l1.next;
            }else{
                ListNode next = l2.next;
                cur.next = l2;
                l2.next = l1;
                l2 = next;
            }
            cur = cur.next;

        }
        if(l2 != null){
            cur.next = l2;
        }

        return dummy.next;
    }


    /**
     * 使用PriorityQueue 进行实现
     * @param lists
     * @return
     */
    public ListNode mergeKSortedList2(ListNode[] lists){


        if(lists == null || lists.length ==0 ) return null;
        PriorityQueue queue = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode l1, ListNode l2){
                return l1.val - l2.val;

            }

        });

        ListNode head = new ListNode(0);
        ListNode p = head;

        for(ListNode list:lists){
            if(list!=null){
                queue.offer(list);
            }
        }

        while(!queue.isEmpty()){
            ListNode n = (ListNode) queue.poll();
            p.next = n;
            p = p.next;
            if(n.next != null){
                queue.offer(n.next);
            }
        }
        return head.next;

    }


}
