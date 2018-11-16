package com.zxb.structurealgo.linkedlist;

/**
 * 描述：
 *  判断一个单链表是否有环？
 *  有的话，请进一步找出环入口的节点
 *
 *  1. 是否有环，快慢指针，如果有环，用脚想一想也知道快慢指针是一定会相遇的
 *  （怎么证明：当快慢指针都在环里的时候，如果相差x步，则快指针追上慢指针时，慢指针有走了y步，则x+y=2*x=>y==x, 即慢指针再走x步就会被快指针追上）
 *   相遇时假设慢指针走了x步，则快指针走了2*x步，假设环的大小为n, 则可以知道快指针比慢指针多走了r*n步（r圈），即2*x=x+r*n==>x=r*n
 *  2. 找环的入口节点
 *  假设从头结点到入口节点总共有y个节点，则相遇时慢指针在环内走了x-y步，如果让慢指针继续在环内走y步，则这时候慢指针在环内总共走了x-y+y=x=r*n,
 *  刚好会回到入口处；接下来最关键的一点，当快慢指针相遇时，将快指针指向链表头，快慢指针一步一步走直到相遇点就是入口了
 *
 *  开搞，可以自己举例试一下
 *
 * @author xuery
 * @date 2018/11/14
 */
public class CircularLinkedList {

    public static void main(String[] args) {
        Node head = new Node(1);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        head.next = node2;node2.next=node3;node3.next=node4;node4.next=node5;node5.next=node6;node6.next=node4;

        CircularLinkedList cll = new CircularLinkedList();

        Node entryNode = cll.findCircularLinkedListEntry(head);
        if(entryNode != null) {
            System.out.println(entryNode.val);
        }
    }

    /**
     *
     * @param head 是链表的第一个节点，而不是哨兵虚拟节点，并不是什么时候哨兵都好使的
     * @return
     */
    public Node findCircularLinkedListEntry(Node head){
        if(head == null){
            return null;
        }

        Node slow = head, fast = head;

        //要特殊考虑一个节点时，自己指向自己吗？-不用下面已经包含了
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                //说明有环
                break;
            }
        }

        if(fast != null && fast.next != null){
            fast = head;
            while(slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        } else {
            return null;
        }
    }
}
