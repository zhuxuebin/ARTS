package com.zxb.structurealgo.linkedlist;

/**
 * 描述：
 *  删除链表的倒数第n个节点
 *
 *  注意边界判断：链表为空，链表只有1、2元素，n的大小取值是否超过范围
 * @author 01368080
 * @date 2018/11/14
 */
public class RemoveElementOfLinkedList {

    public static void main(String[] args) {
        Node dummyHead = new Node(0);

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
//        dummyHead.next = node1;
        dummyHead.next = node1;node1.next=node2;node2.next=node3;node3.next=node4;

        RemoveElementOfLinkedList reoll = new RemoveElementOfLinkedList();
        reoll.removeElement2(dummyHead,1);

        reoll.printNodes(dummyHead);

    }

    public void printNodes(Node dummyHead){
        Node nextNode = dummyHead.next;
        while(nextNode != null){
            System.out.println(nextNode.val);
            nextNode = nextNode.next;
        }
    }

    /**
     * 方法1：遍历两次：一次得到链表长度，一次求解
     * @param head:虚假头节点
     * @param n
     */
    public Node removeElement1(Node head, int n){
        if(head == null || head.next == null || n <= 0){
            return null;
        }

        //先遍历一遍计算链表的长度
        Node p = head.next;
        int len = 0;
        while(p != null){
            len++;
            p = p.next;
        }
        //异常输入n
        if(n > len){
            return null;
        }

        //找到需要删除的元素的前一个元素
        int k = len - n;
        Node preDeleteNode = head;
        while(k-- > 0){
            preDeleteNode = preDeleteNode.next;
        }

        //开始删除
        Node deleteNode = preDeleteNode.next;
        preDeleteNode.next = preDeleteNode.next.next;

        return deleteNode;
    }


    /**
     * 采用双指针，一次遍历完成
     * 快指针先走n步
     * @param head
     * @param n
     * @return
     */
    public Node removeElement2(Node head, int n){
        if(head == null || head.next == null || n <= 0){
            return null;
        }

        Node slow = head, fast = head;
        while(n-- > 0 && fast != null){
            fast = fast.next;
        }

        if(fast == null){
            //说明n超过了链表的长度
            return null;
        }

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        //这时候slow指向要删除节点的前一个位置
        Node deleteNode = slow.next;
        slow.next = slow.next.next;

        return deleteNode;
    }
}
