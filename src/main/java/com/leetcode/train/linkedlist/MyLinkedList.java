package com.leetcode.train.linkedlist;
import java.util.Hashtable;

/**
 * @author Batman on 2018/12/19.
 * @author Batman
 * 单链表常见算法
 * 这里设置的linked list的头结点是有值
 * exp:    1(head)->2->3->4.....
 */
public class MyLinkedList {
    /**
     * 链表的头结点
     */
    ListNode head = null;

    /**
     * 链表的空间大小
     */
    int size=0;

    /**
     * 链表添加结点
     * 找到链表的末尾结点,把新添加的数据作为末尾结点的后续结点
     */
    void addNode(int val){
        ListNode newNode = new ListNode(val);
        if(head == null){
            head = newNode;
            return;
        }
        ListNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = newNode;
        size ++;
    }

    /**
     * 在固定的索引位置插入元素
     * 这里默认链表的头结点是含有值的  是链表的第一个element
     * @param index 待插入节点的位置索引
     * @param val 带插入节点的val
     */
    void addAtIndex(int index, int val){
        if(index<0 || index>size){
            return ;
        }
        else if(index == size){
            // 在尾部添加元素
            addAtTail(val);
        }else if(index==0){
            addAtHead(val);
        } else{
            ListNode cur = head;
            // 将链表移动到待添加位置前一个节点位置
            for(int i=0;i<index-1;i++){
                cur = cur.next;
            }
            ListNode addNode = new ListNode(val);

            addNode.next = cur.next;
            cur.next = addNode;
            size++;
        }
    }

    /**
     * Add a node of value val before the first element of the linked list
     * After the insertion, the new node will be the first node of the linked list
     * @param val 头部插入节点的val值
     */
    void addAtHead(int val){
        ListNode headNode = new ListNode(val);
        if(head == null){
            head = headNode;
        }else{
            headNode.next = head;
            head = headNode;
        }

        size++;
    }

    /**
     * 在链表尾部添加节点
     * @param val 待添加节点的val值
     */
    void addAtTail(int val) {
        ListNode tailNode = new ListNode(val);
        ListNode cur = head;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = tailNode;
        size ++;
    }


    /**
     * 链表删除节点:
     * 把要删除的节点的前节点指向要删除节点的后节点,即直接跳过待删除的节点
     * @param index 待删除节点的索引
     * @return boolean
     */
    public boolean deleteNode(int index){
        if(index<1 || index>length()){
            return false;
        }
        // 删除头结点
        if(index == 1){
            head = head.next;
            size--;
            return true;
        }

        ListNode preNode = head;
        ListNode curNode = preNode.next;
        int i = 1;
        while(curNode!=null){
            // 寻找待删除节点
            if(index == i ){
                // 待删除节点的前节点指向待删除节点的后节点
                preNode.next = curNode.next;
                size--;
                return true;
            }
            preNode = preNode.next;
            curNode = curNode.next;
            i++;
        }
        size--;
        return true;

    }

    /**
     * 删除链表上固定索引上的值
     * @param index 待删除链表位置索引
     */
    public void deleteAtIndex(int index){
        if(index<1 || index>=size){
            return;
        }else{
            ListNode cur = head;
            for(int i=0;i<index;i++){
                cur = cur.next;
            }

            // 跳过待删除索引位置的节点
            ListNode delNode = cur.next;
            cur.next = delNode.next;
            delNode.next = null;
        }
        size --;

    }


    /**
     * 求链表的长度
     * @return int 返回链表的长度
     */
    public int length(){
        int length=0;
        ListNode curNode = head;
        while(curNode != null){
            length ++;
            curNode = curNode.next;
        }
        return length;
    }

    /**
     * 打印节点
     */
    void printLink(){
        ListNode curNode = head;
        while(curNode != null){
            System.out.printf(curNode.val + "->");
            curNode = curNode.next;
        }
        System.out.println();
    }




    /**
     * 链表节点排序,返回排序后的头结点
     * 选择排序算法,及每次都选出未排序节点中最小的节点,与第一个未排序节点交换
     * @return ListNode
     */
    ListNode linkSort(){
        ListNode curNode = head;
        while(curNode!=null){
            ListNode nextNode = curNode.next;
            while(nextNode!=null){
                if(curNode.val > nextNode.val){
                    int temp = curNode.val;
                    curNode.val = nextNode.val;
                    nextNode.val = temp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }
        return head;
    }

    /**
     * 去掉单链表重复元素
     * 需要额外的存储空间hashtable,调用hashtable.containKey()判断重复节点
     */
    void distinctLink(){
        ListNode curNode = head;
        ListNode pre = null;
        Hashtable<Integer, Integer> hb = new Hashtable<>();
        while(curNode!=null){
            if(hb.containsKey(curNode.val)){
                // 如果hashtable中存在该节点,则跳过该节点
                pre.next = curNode.next;
            }else{
                hb.put(curNode.val, 1);
                pre = curNode;
            }
            curNode = curNode.next;
        }
    }

    /**
     * 返回倒数第K个节点
     * pre指针先找到整数第K个节点
     * 然后pre指针和last指针一起移动,直至pre指针为空,此时last指针指向节点即为倒数第K个节点
     * @param k 倒数第K个
     * @return ListNode
     */
    ListNode findRevertNode(int k){
        if(k<1 || k>length()){
            return null;
        }
        ListNode pre = head;
        ListNode last = head;
        for(int i=0;i<k-1;i++){
            pre = pre.next;
        }
        while(pre.next!=null){
            pre = pre.next;
            last = last.next;
        }
        return last;
    }

    /**
     * 查找第k个节点
     * @param k 数值 第个节点
     * @return ListNode
     */
    ListNode findNode(int k){
        if(k<1 || k>length()){
            return null;
        }
        ListNode curNode = head;
        for(int i=0;i<k-1;i++){
            curNode = curNode.next;
        }
        return curNode;
    }

    /**
     * 反向链表,在反转指针前一定要保存好下个节点的指针
     */
    void reverseLink(){
        ListNode curNode = head;
        ListNode preNode = null;
        while(curNode!=null){
            // 先保存下一个节点
            ListNode nextNode = curNode.next;
            // 断开curNode与下面的节点 使其指向preNode
            curNode.next = preNode;
            // 将当前节点赋给preNode
            preNode = curNode;
            // 当前节点继续后移
            curNode = nextNode;
        }
        head = preNode;
    }

    /**
     * 反向输出链表
     * (1)先反转链表, 再输出链表,需要遍历链表两次
     * (2)把链表中元素放入栈中存储,需要维护栈的空间
     * (3)通过递归实现(2)方法,递归起始就是将先执行的数据压入栈中,再一次出栈
     */
    void reversePrt(ListNode node){
        if(node != null){
            reversePrt(node.next);
            System.out.printf(node.val + "->");
        }
    }

    /**
     * 寻找单链表的中间节点
     * (1)先求出链表长度,在遍历1/2链表长度,寻找出链表的中间节点
     * (2)用两个指针遍历链表,一个快指针,一个慢指针
     * 快指针每次移动两个节点,慢指针每次移动一个节点
     * 当快指针移动至尾节点时, 慢指针指向即为链表中间节点
     */
    ListNode findMiddleNode(){
        ListNode slowPoint = head;
        ListNode fastPoint = head;

        // 长度为偶数时,返回中间节点第一个
        while(fastPoint.next != null && fastPoint.next.next != null){
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next.next;

        }
        return slowPoint;
    }


    /**
     * 判断链表是否有环
     * 通过快慢指针判断(如果快慢指针相等时,说明有环)
     */
    boolean isRinged(){
        ListNode slowPoint = head;
        ListNode fastPoint = head;
        while(fastPoint.next != null && fastPoint.next.next!=null){
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next.next;
            if(fastPoint == slowPoint){
                return true;
            }
        }
        return false;

    }


    /**
     * 得到有环链表的入环节点
     * @return 第一个入环的节点
     * leetcode 142
     *
     * 计算方案 设从头结点到入环节点长度为n 快慢指针相遇的点距离入环点为m k为快指针绕环的圈数,l是链表环的长度
     * 则慢指针行走距离为 m+n， 快指针行走距离为 m+n+kl
     * 于是 2(m+n)=m+n+kl 则 kl = m+n
     * 计算方法 慢指针从相遇点继续出发 快指针指向头结点 然后快慢指针一起移动 相遇的节点即为 第一个入环节点
     * 因为慢指针行走的距离为kl-m 而kl-m = n 正好是从头结点到第一个入环节点的距离
     */
    public ListNode getLoopNode(){
        // 判断链表的长度 如果为0、1、2的话 则返回null
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        // 判断是否有环
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                break;
            }
        }

        // 如果没有环 直接返回null
        if(fast != slow){
            return null;
        }

        // 令快指针指向头结点
        fast = head;

        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }


    /**
     * 返回链表的最后一个节点
     */
    ListNode getLastNode(){
        ListNode curNode = head;
        while(curNode.next!=null){
            curNode = curNode.next;
        }
        return curNode;
    }


    /**
     * 在不知道头结点的情况下删除指定节点
     * 删除节点的重点在于找出其前节点,使其前节点的指针指向其后节点,跳过待删除节点
     * 1. 如果待删除节点是尾节点,由于单链表不知道其前节点,没有办法删除
     * 2. 如果删除的节点不是尾节点,则将其该节点的值与下一节点交换,然后该节点的指针指向下一节点的后续节点
     */
    void deleteSpecialNode(ListNode n){
        if(n.next == null){
            System.out.println("删除节点为尾节点,单链表不知道其前节点,没有办法删除");
            return;
        }
        int temp = n.val;
        n.val = n.next.val;
        n.next.val = temp;
        n.next = n.next.next;
    }


    /**
     * 判断两个链表是否相交(链表无环情形)
     * 两个链表相交,则它们的尾节点一定相同,比较两个链表的尾节点即可
     */
    static boolean isCross(ListNode head1, ListNode head2){
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        while(temp1.next != null){
            temp1 = temp1.next;
        }
        while(temp2.next!=null){
            temp2 = temp2.next;
        }
        return temp1 == temp2;
    }


    /**
     * 如果链表相交,求链表相交的起始点
     * 1. 首先判断链表是否相交,如果两个链表不相交,返回null
     * 2. 求出两个链表的长度之差 len = length1-length2
     * 3. 让较长链表先走len步
     * 4. 然后两个链表同时移动,每移动一次就比较他们节点是否相等,第一个相等的节点即为所求
     * leetcode 160 相交链表
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
     */
    static ListNode getIntersectionNode(MyLinkedList m1, MyLinkedList m2){
        // 链表不相交
        if(!isCross(m1.head, m2.head)){
            return null;
        }
        int length1 = m1.length();
        int length2 = m2.length();
        ListNode head1 = m1.head;
        ListNode head2 = m2.head;

        int len = length1 - length2;
        // 先让headA先走len步
        if(len>0){
            for(int i=0;i<len;i++){
                head1 = head1.next;
            }
        }
        // 先让headB先走len步
        else{
            for(int i=0;i<-len;i++){
                head2 = head2.next;
            }
        }
        // 两个链表同时移动,直至找到两个链表的相交的节点
        while(head1 != head2){
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1;

    }











}
