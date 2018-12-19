package linkedList;

/**
 * Created by sunchao on 2018/12/19.
 */

import java.util.Hashtable;

/**
 * Created by sunchao on 2018/11/21.
 * @author Batman
 * 单链表常见算法
 */
public class MyLinkedList {
    /**
     * 链表的头结点
     */
    Node head = null;

    /**
     * 链表添加结点
     * 找到链表的末尾结点,吧新添加的数据作为末尾结点的后续结点
     */
    public void addNode(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = newNode;
    }

    /**
     * 链表删除节点:
     * 把要删除的节点的前节点指向要删除节点的后节点,即直接跳过待删除的节点
     * @param index
     * @return
     */
    public boolean deleteNode(int index){
        if(index<1 || index>length()){
            return false;
        }
        // 删除头结点
        if(index == 1){
            head = head.next;
            return true;
        }

        Node preNode = head;
        Node curNode = preNode.next;
        int i = 1;
        while(curNode!=null){
            // 寻找待删除节点
            if(index == i ){
                // 待删除节点的前节点指向待删除节点的后节点
                preNode.next = curNode.next;
                return true;
            }
            preNode = preNode.next;
            curNode = curNode.next;
            i++;
        }
        return true;

    }

    /**
     * 求链表的长度
     * @return
     */
    public int length(){
        int length=0;
        Node curNode = head;
        while(curNode != null){
            length ++;
            curNode = curNode.next;
        }
        return length;
    }

    /**
     * 打印节点
     */
    public void printLink(){
        Node curNode = head;
        while(curNode != null){
            System.out.printf(curNode.data + "->");
            curNode = curNode.next;
        }
        System.out.println();
    }




    /**
     * 链表节点排序,返回排序后的头结点
     * 选择排序算法,及每次都选出未排序节点中最小的节点,与第一个未排序节点交换
     * @return
     */
    public Node linkSort(){
        Node curNode = head;
        while(curNode!=null){
            Node nextNode = curNode.next;
            while(nextNode!=null){
                if(curNode.data > nextNode.data){
                    int temp = curNode.data;
                    curNode.data = nextNode.data;
                    nextNode.data = temp;
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
    public void distinctLink(){
        Node curNode = head;
        Node pre = null;
        Hashtable<Integer, Integer> hb = new Hashtable<>();
        while(curNode!=null){
            if(hb.containsKey(curNode.data)){
                // 如果hashtable中存在该节点,则跳过该节点
                pre.next = curNode.next;
            }else{
                hb.put(curNode.data, 1);
                pre = curNode;
            }
            curNode = curNode.next;
        }
    }

    /**
     * 返回倒数第K个节点
     * pre指针先找到整数第K个节点
     * 然后pre指针和last指针一起移动,直至pre指针为空,此时last指针指向节点即为倒数第K个节点
     * @param k
     * @return
     */
    public Node findReverNode(int k){
        if(k<1 || k>length()){
            return null;
        }
        Node pre = head;
        Node last = head;
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
     * @param k
     * @return
     */
    public Node findNode(int k){
        if(k<1 || k>length()){
            return null;
        }
        Node curNode = head;
        for(int i=0;i<k-1;i++){
            curNode = curNode.next;
        }
        return curNode;
    }

    /**
     * 反向链表,在反转指针前一定要保存好下个节点的指针
     */
    public void reverseLink(){
        Node curNode = head;
        Node preNode = null;
        while(curNode!=null){
            // 先保存下一个节点
            Node nextNode = curNode.next;
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
     * (1)先反转链表,在输出链表,需要遍历链表两次
     * (2)把链表中元素放入栈中存储,需要维护栈的空间
     * (3)通过递归实现(2)方法,递归起始就是将先执行的数据压入栈中,再一次出栈
     */
    public void reversePrt(Node node){
        if(node != null){
            reversePrt(node.next);
            System.out.printf(node.data + "->");
        }
    }

    /**
     * 寻找单链表的中间节点
     * (1)先求出链表长度,在遍历1/2链表长度,寻找出链表的中间节点
     * (2)用两个指针遍历链表,一个快指针,一个慢指针
     * 快指针每次移动两个节点,慢指针每次移动一个节点
     * 当快指针移动至尾节点时, 慢指针指向即为链表中间节点
     */
    public Node findMiddleNode(){
        Node slowPoint = head;
        Node fastPoint = head;

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
    public boolean isRinged(){
        Node slowPoint = head;
        Node fastPoint = head;
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
     * 返回链表的最后一个节点
     */
    public Node getLastNode(){
        Node curNode = head;
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
    public boolean deleteSpecialNode(Node n){
        if(n.next == null){
            System.out.println("删除节点为尾节点,单链表不知道其前节点,没有办法删除");
            return false;
        }
        int temp = n.data;
        n.data = n.next.data;
        n.next.data = temp;
        n.next = n.next.next;
        return true;
    }


    /**
     * 判断两个链表是否相交(链表无环情形)
     * 两个链表相交,则它们的尾节点一定相同,比较两个链表的尾节点即可
     */
    public static boolean isCross(Node head1, Node head2){
        Node temp1 = head1;
        Node temp2 = head2;
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
     * 1. 首先判断链表是否相交,如果两个链表不想交,返回null
     * 2. 求出两个链表的长度之差 len = length1-length2
     * 3. 让较长链表先走len步
     * 4. 然后两个链表同时移动,每移动一次就比较他们节点是否相等,第一个相等的节点即为所求
     */
    public static Node findFirstCrossPoint(MyLinkedList m1, MyLinkedList m2){
        // 链表不想交
        if(!isCross(m1.head, m2.head)){
            return null;
        }
        int length1 = m1.length();
        int length2 = m2.length();
        Node head1 = m1.head;
        Node head2 = m2.head;

        int len = length1-length2;
        if(len>0){
            for(int i=0;i<len;i++){
                head1 = head1.next;
            }
        }
        else{
            for(int i=0;i<len;i++){
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
