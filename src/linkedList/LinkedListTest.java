package linkedList;

/**
 * Created by Batman on 2018/12/19.
 */
public class LinkedListTest {
    public static void main(String[] args) {
        MyLinkedList m1 = new MyLinkedList();
        // 添加链表节点
        m1.addAtHead(100);
        m1.addNode(9);
        m1.addNode(8);

        m1.addNode(6);
        m1.addNode(3);
        m1.addNode(5);
        m1.addNode(8);
        m1.addNode(10);
        m1.addNode(3);
        m1.addNode(99);

        m1.addAtHead(89);
        m1.addAtTail(1000);

        m1.addAtIndex(1, 88888);

        m1.addAtIndex(0, 9999);


        System.out.println("链表的空间大小是: "+m1.size);
        System.out.println("链表的头结点的值是:" + m1.head.val);
        // 打印链表
        System.out.println("原始链表为:");
        m1.printLink();

        // 测试链表节点个数
        System.out.println("链表中节点个数为:"+m1.length());

        // 链表排序
        ListNode head = m1.linkSort();
        System.out.println("排序后的链表头结点为:" + head.val);
        System.out.println("排序后的链表为:");
        m1.printLink();


        // 反转链表
        m1.reverseLink();

        System.out.println("翻转后的链表为:");
        m1.printLink();

        // 去掉重复节点
        m1.distinctLink();
        System.out.println("去掉重复节点后的链表为:");
        m1.printLink();

        //倒序输出链表
        System.out.println("倒序输出链表为:");
        m1.reversePrt(m1.head);
        System.out.println();

        //返回链表中间节点
        System.out.println("返回链表的中间节点的数值为:"+m1.findMiddleNode().val);

        // 判断链表是否有环
        System.out.println("判断链表是否有环: " + m1.isRinged());


        m1.printLink();
        System.out.println("返回链表中最后节点的数值为:" + m1.getLastNode().val);

        // 删除指定节点
        MyLinkedList m2 = new MyLinkedList();
        m2.addNode(1);
        m2.addNode(2);
        m2.addNode(3);
        m2.addNode(4);
        m2.addNode(5);
        m2.addNode(6);
        m2.addNode(7);
        m2.addNode(8);


        System.out.println("当前m2链表为:");
        m2.printLink();
        System.out.println("m2链表中倒数第2个节点的数值为: "+m2.findReverNode(2).val);
        System.out.println("m2链表删除倒数第二个节点后为:");
        m2.deleteSpecialNode(m2.findReverNode(2));
        m2.printLink();

        System.out.println("链表连接前为:");
        System.out.println("m1链表连接前:");
        m1.printLink();
        System.out.println("m2链表连接前:");
        m2.printLink();


        m2.findNode(2).next = m1.findNode(3);
        System.out.println("链表连接后的打印两个链表:");
        System.out.println("m1链表连接后:");
        m1.printLink();
        System.out.println("m2链表连接后:");
        m2.printLink();


        /**
         * 避免通过实例访问静态成员变量(包含函数),无谓增加编译器解析成本
         * 直接使用类名访问即可
         */
        System.out.println("m1和m2链表是否相交:" + MyLinkedList.isCross(m1.head, m2.head));


        System.out.println("-----********-------");
        // 如果两个链表相交,求其第一个交点值
        System.out.println(MyLinkedList.findFirstCrossPoint(m1,m2).val);

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        ListNode l8 = new ListNode(8);
        ListNode l9 = new ListNode(9);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;

        System.out.println("进行链表的奇偶连接");
        LinkedListFunction fc = new LinkedListFunction();
//        ListNode connect = fc.oddEvenList(l1);
        ListNode connect1 = fc.oddEvenList2(l1);
//        fc.printNode(connect);
        fc.printNode(connect1);

        System.out.println("链表从右侧进行旋转(k=2)的结果为: ");
        ListNode rotateHead = fc.rotateRight(connect1, 2);
        fc.printNode(rotateHead);



    }
}

