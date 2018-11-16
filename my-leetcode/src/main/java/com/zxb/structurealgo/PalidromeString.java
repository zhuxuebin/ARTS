package com.zxb.structurealgo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuery on 2018/11/11.
 * <p>
 * 题目描述：给定一个单链表连接的字符串，判断是不是回文字符串
 * 如：
 * a->b->c->b->a 是
 * a->b->b->a 是
 * a->b->c 否
 * <p>
 * 思路分析：
 * 如果是普通的字符串则很好判断，利用数组的访问为O（1）+ 对称性即可判断
 * 单链表则不能这么做，我的思路：
 * 思路1：将链表逆序之后与原链表对比：需要先将原链表拷贝一份(如何copy)，然后逆序
 * 思路2：
 */
public class PalidromeString {

    public static void main(String[] args) {
        PalidromeString ps = new PalidromeString();
        Node head = new Node('1');
//        Node p1 = head.next = new Node('1');
//        Node p2 = p1.next = new Node('2');
//        Node p3 = p2.next = new Node('1');
//        Node p4 = p3.next = new Node('1');
        System.out.println(ps.isPalidromeString2(head));
    }

    //思路1：时间复杂度：O（n）,空间复杂度：O（n）
    public boolean isPalidromeString(Node head) {

        if (head == null) {
            return true;
        }

        //copy head 这里假设head是包含元素的
        Node copyHead = new Node(head.val);
        Node originP = head.next, copyP = copyHead;
        while (originP != null) {
            copyP.next = new Node(originP.val);

            originP = originP.next;
            copyP = copyP.next;
        }

        //reverse copyHead
        Node p = copyHead, q = p.next;
        while (q != null) {
            p.next = q.next;
            q.next = copyHead;

            //更新
            copyHead = q;
            q = p.next;
        }

        //开始比较
        originP = head;
        copyP = copyHead;
        while (originP != null && copyP != null) {
            if (originP.val != copyP.val) {
                return false;
            }
            originP = originP.next;
            copyP = copyP.next;
        }

        return true;
    }

    /**
     * 思路2：快慢指针，先找到中间节点，然后将前半段逆序，然后分别从开头和中间顺序比较，最后将前半段逆序还原（一般实现是不改变输入）
     * 时间复杂度O（n）,空间复杂度O（1）
     * 注意点：找中间节点或者比较时，与链表中的个数是偶数还是奇数是息息相关的，注意区分对待；尤其要注意空指针的判断
     * 比如：
     * 偶数个：1->2->2->1, 中间节点mid这里取第二个2（当然如果取第一个2也可以，但是后面的逆序条件判断需要变化），
     * 逆序p,q,当q==mid时停止逆序，可以保证只有前半段逆序，比较指针：head, mid即可，逆序回去呢，同理
     * 奇数个：1->2->3->2->1, 中间节点mid为3，逆序p,q,也可以q==mid的时候停止逆序，则逆序之后为2->1->3->2->1,比较：head,mid.next开始比较
     * 逆序回去呢，同理
     * 上述分析可以保证偶数，奇数保持统一
     */

    public boolean isPalidromeString2(Node head) {
        if (head == null) {
            return true;
        }

        //按照上述分析，利用快慢指针找出符合条件的中间节点,最后慢指针指向的就是中间节点
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;

        //前半段逆序
        Node p = head, q = p.next;
        while (q != null && q != mid) {
            p.next = q.next;
            q.next = head;

            //更新
            head = q;
            q = p.next;
        }

        //开始比较，奇数：head，mid.next(注意只有一个元素时), 偶数：head, mid
        Node start1 = head, start2;
        if (fast == null) {
            //说明是偶数个元素
            start2 = mid;
        } else {
            start2 = mid.next;
        }
        //这样判断可以很好的避免只有一个元素的误判
        boolean isPalidromeString = true;
        while(start1 != null && start2 != null){
            if(start1.val != start2.val){
                isPalidromeString = false;
                break;
            }
            start1 = start1.next;
            start2 = start2.next;
        }

        //还原前半段逆序的元素
        p = head;
        q = p.next;
        while (q != null && q != mid) {
            p.next = q.next;
            q.next = head;

            //更新
            head = q;
            q = p.next;
        }

        return isPalidromeString;
    }

    static class Node {
        Node next = null;
        char val;

        public Node(char val) {
            this.val = val;
        }

    }
}
