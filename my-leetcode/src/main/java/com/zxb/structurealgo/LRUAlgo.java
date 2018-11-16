package com.zxb.structurealgo;

/**
 * 描述：
 *  数据结构与算法之美第六讲：单链表（双向链表就更简单了）实现LRU算法
 *  基本思路：LRU算法是最近最少访问淘汰法，每次访问一个元素则将该元素删除并插入到链表头（保证链表头的数据是最新的），
 *  插入元素也直接插入表头；什么时候淘汰元素，当元素个数超过链表容量时，这里暂时不考虑多线程安全的场景
 *
 *  修改：需要将单链表改成双向链表，不然单链表删除尾节点很麻烦，时间复杂度是O（n）
 * @author xuery
 * @date 2018/11/13
 */
public class LRUAlgo {

    private static final int CAPACITY = 4;

    private int size = 0;

    private Node dummyHead; //这里假设头指针不包含任何元素

    private Node tail; //指向最后一个元素，用于删除元素

    public LRUAlgo(){
        dummyHead = new Node(0);
    }

    public static void main(String[] args) {

        LRUAlgo lruAlgo = new LRUAlgo();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        lruAlgo.insert(node1);lruAlgo.insert(node2);lruAlgo.insert(node3);lruAlgo.insert(node4);lruAlgo.insert(node5);

        lruAlgo.visit(node2);
        lruAlgo.visit(node5);

        lruAlgo.insert(node6);

        lruAlgo.printNodes(lruAlgo.dummyHead);
    }

    public void printNodes(Node dummyHead){
        Node nextNode = dummyHead.next;
        while(nextNode != null){
            System.out.println(nextNode.val);
            nextNode = nextNode.next;
        }
    }

    public void insert(Node node){
        Node dummyHeadNext = dummyHead.next;
        dummyHead.next = node;
        node.next = dummyHeadNext;
        node.pre = dummyHead;
        if(dummyHeadNext != null){
            dummyHeadNext.pre = node;
        }

        //插入前没有元素时，需要将tail指向插入的元素
        if(dummyHeadNext == null){
            tail = node;
        }
        //说明已经满了，需要删除尾节点
        if(size == CAPACITY){
            Node tailPre = tail.pre;
            tailPre.next = null;
            tail.pre = null;

            //更新tail
            tail = tailPre;
        } else {
            size++;
        }
    }

    public void visit(Node node){
        //第一步将node从链表中删除
        Node preNode = node.pre;
        Node nextNode = node.next;
        preNode.next = nextNode;
        if(nextNode != null){
            nextNode.pre = preNode;
        }

        //如果node为tail，则需要更新tail
        if(node == tail){
            tail = preNode;
        }

        //将Node插入链表头即可
        size--; //如果这时候刚好达到容量，假设不减肯定有问题
        insert(node);
    }

   static class Node {
       int val;
       Node next;
       Node pre;
       public Node(int val){
           this.val = val;
       }
   }
}
