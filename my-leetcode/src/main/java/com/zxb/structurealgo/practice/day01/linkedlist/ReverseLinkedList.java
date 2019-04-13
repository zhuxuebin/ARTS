package com.zxb.structurealgo.practice.day01.linkedlist;

/**
 * @ClassName ReverseLinkedList
 * @Description 翻转单链表
 *
 * 虽然简单，但是还是动手写一下
 *
 * @Author xuery
 * @Date 2019/4/13 11:34
 * @Version 1.0
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);

        head.next = node1;node1.next=node2;
        Node reverseHead = reverseLinkedList(head);
        Node p = reverseHead;
        while(p!=null){
            System.out.println(p.value);
            p = p.next;
        }

    }

    public static Node reverseLinkedList(Node head){

        //这里就不引入虚拟头结点了
        if(head == null){
            return head;
        }

        Node p = head;
        Node q = p.next;
        while(p!= null && q!=null){

            p.next = q.next;
            q.next = head;

            head = q;
            q = p.next;
        }

        return head;
    }


    static class Node{

        private int value;

        Node next = null;

        public Node(int value){
            this.value = value;
        }
    }
}
