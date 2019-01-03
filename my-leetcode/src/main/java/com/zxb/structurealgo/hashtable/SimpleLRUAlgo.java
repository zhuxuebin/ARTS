package com.zxb.structurealgo.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 简单的LRU算法=HashMap+双向链表
 * 相比于LRUAlgo利用散列表可以大大加快访问某个节点的速度
 *
 * 链表头淘汰，链表尾是最新的数据
 *
 * 这里假设Node的key具备唯一标示性
 * Created by xuery on 2018/12/23.
 */
public class SimpleLRUAlgo {

    public static void main(String[] args) {
        SimpleLRUAlgo simpleLRUAlgo = new SimpleLRUAlgo();

        Node node1 = new Node("1",1);
        Node node2 = new Node("2",2);
        Node node3 = new Node("3",3);
        Node node4 = new Node("4",4);
        simpleLRUAlgo.put(node1);
        simpleLRUAlgo.put(node2);
        simpleLRUAlgo.put(node3);
        simpleLRUAlgo.put(node4);
        simpleLRUAlgo.put(node1);

        printAllNode(simpleLRUAlgo);

        Node node5 = new Node("5",5);
        simpleLRUAlgo.put(node5);
        printAllNode(simpleLRUAlgo);

        Node findNode = simpleLRUAlgo.get("4");
        printAllNode(simpleLRUAlgo);
    }

    private static void printAllNode(SimpleLRUAlgo simpleLRUAlgo){
        Node p = simpleLRUAlgo.dummyHead;
        while(p.next != null){
            System.out.print(p.next.key+" ");
            p = p.next;
        }
        System.out.println();
    }

    //双向链表虚拟头结点，方便删除插入
    private Node dummyHead = new Node(null,0);

    //tail是指向实际尾结点
    private Node tail = null;

    private final static int MAX_LEN = 4;

    //map结构
    private Map<String, Node> map = new HashMap<>(16);

    public SimpleLRUAlgo(){
        //initial

    }

    /**
     * 先判断map中是否存在node对应的key，有则更新map并将结点移动到链表尾
     * 无则判断链表是否已经满员，满员删除头元素和map中对应的元素，然后将新增元素放入map并放入链表尾部
     *                           未满员则将新增元素放入map并放入链表尾部
     * 注意更新头结点和尾节点（因为虚拟头结点的存在，所以头结点更新很方便）
     * @param node
     */
    public void put(Node node){
        if(node == null){
            return;
        }

        if(map.containsKey(node.key)){
            map.put(node.key, node);
            removeToLast(node);
        } else {
            if(map.size() == MAX_LEN) {
                //先删除虚拟头结点的后一个元素
                Node firstNode = dummyHead.next;
                dummyHead.next = firstNode.next;
                firstNode.pre = dummyHead;
            }
            map.put(node.key, node);
            insertToLast(node);
        }
    }

    private void removeToLast(Node node){
        if(node == tail){
            return;
        }
        //先删除
        node.pre.next = node.next;
        node.next.pre = node.pre;

        //插入到尾节点,这时候尾节点一定有元素
        tail.next = node;
        node.pre = tail;
        node.next = null; //bug1:不要漏了

        //更新tail
        tail = node;
    }

    private void insertToLast(Node node){
        if(tail == null){
            dummyHead.next = node;
            node.pre = dummyHead;

            tail = node;
            return;
        }

        tail.next = node;
        node.pre = tail;

        tail = node;
    }

    /**
     *
     * @param key
     * @return
     */
    public Node get(String key){
        Node node = map.get(key);
        if(node == null){
            return null;
        }
        removeToLast(node);
        return node;
    }


    /**
     * 双向链表结构
     */
    private static class Node {
        int val;
        String key; //key要作为hashMap中的唯一标示
        Node pre;
        Node next;

        public Node(String key, int val){
            this.key = key;
            this.val = val;
        }
    }
}
