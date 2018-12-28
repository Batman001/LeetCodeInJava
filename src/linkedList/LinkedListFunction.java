package linkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunchao on 2018/12/28.
 * 关于链表的各种函数方法
 * @author Batman
 *
 */
public class LinkedListFunction {

    /**
     * 判断链表是否出现环 LeetCode 141
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head){
        if(head == null){
            return false;
        }

        // 设置快慢指针,慢指针每次移动一个位置,快指针每次移动两个位置
        ListNode slowPoint = head;
        ListNode fastPoint = head;
        while(fastPoint.next != null && fastPoint.next.next != null){
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next.next;
            if(slowPoint == fastPoint){
                return true;
            }
        }
        return false;

    }

    /**
     * 返回有环链表的环入口节点,如果没有 返回null
     * LeetCode 142
     * @param head
     * @return
     */

    public ListNode detectCycle(ListNode head){
        if(head == null){
            return null;
        }
        // 设置快慢指针
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            // 判断是否有环 如果出现环, 则寻找环的入口节点

            /**
             * a:从head到环入口节点路程
             * x:为从环入口到第一次快慢指针相遇的路程
             * c:环的长度
             * s:为第一次快慢指针相遇时 慢指针走过的路程
             * 2s:为第一次快慢指针相遇时 快指针走过的路程
             * 因此 2s = s + nc 则 s = a + x
             * 所以可以到 a+x = nc ==> a = nc-x ==> a = (n-1)c + c-x ==> a = kc + c-x
             * c-x为从相遇点到环入口的距离
             * 设置如果此时有个指针Pointer1 从head节点出发,slow继续从相遇点出发,绕过k圈之后,
             * slow指针和Pointer1将会在环入口位置相遇
             */
            if(slow == fast){
                fast = head;
                while(fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;

    }


    /**
     * 判断链表是不是为回文链表
     * LeetCode 234
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> result = new ArrayList<>();
        while(head != null){
            result.add(head.val);
            head = head.next;
        }
        if(result.size() == 1){
            return true;
        }
        int mid = result.size() / 2;
        List<Integer> r1 = new ArrayList<>();
        List<Integer> r2 = new ArrayList<>();
        if(result.size() % 2 == 0){

            for (int i=0;i<mid;i++){
                r1.add(result.get(i));
            }
            for(int i=mid; i<result.size(); i++){
                r2.add(0,result.get(i));
            }
        }
        else{
            for (int i=0;i<mid;i++){
                r1.add(result.get(i));
            }
            for(int i=mid+1; i<result.size(); i++){
                r2.add(0,result.get(i));
            }
        }
        // 判断r1与r2的元素是否相同
        if(r1.equals(r2)){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 判断链表是不是为回文链表
     * @param head
     * LeetCode 234
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        if(head == null || head.next==null){
            return true;
        }
        ListNode root = head;
        List<ListNode> nodes = new ArrayList<>();
        while(root != null){
            nodes.add(root);
            root = root.next;
        }

        int i=0, j=nodes.size()-1;
        while(i<j){
            if(nodes.get(i).val != nodes.get(j).val){
                return false;
            }
            i++;
            j--;
        }
        return true;

    }

}
