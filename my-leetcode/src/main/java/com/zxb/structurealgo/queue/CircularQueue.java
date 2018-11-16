package com.zxb.structurealgo.queue;

/**
 * 描述：
 * 循环队列的实现：这里假设tail指向的元素是空的（即tail指向最后一个元素的下一个位置）
 * <p>
 * 这里约定循环队列的结构：
 * 1. 循环队列有一个容量n，当循环队列满的时候共有n-1个元素，会浪费一个元素空间；为何如此设计，这样便于
 * 判断队列为空或者为满
 * 2. 插入元素时，先判断队列是否满，满了则插入失败；不满则直接插入到tail指向的位置，并将tail=(tail+1)%n
 * 3. 删除元素时，先判断队列是否为空，空则删除失败；非空则删除head指向的元素，并且head=(head+1)%n
 * 4. 关键点：如何判断队列为空或者已满，为空：head==tail, 已满：(tail+1)%n == head
 *
 * @author 01368080
 * @date 2018/11/15
 */
public class CircularQueue {

    private static final int CAPACITY = 4;
    Integer[] queueArr = new Integer[CAPACITY];
    int head = 0;
    int tail = 0;

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue();
        circularQueue.enqueue(1);
        circularQueue.enqueue(2);
        circularQueue.enqueue(3);

        circularQueue.enqueue(4); //插入失败

        circularQueue.dequeue();
        circularQueue.dequeue();

        circularQueue.enqueue(5);
        circularQueue.enqueue(6);

        System.out.println("head=" + circularQueue.head);
        System.out.println("tail=" + circularQueue.tail);

        for (Integer val : circularQueue.queueArr) {
            System.out.println(val);
        }
    }

    public boolean enqueue(int val) {
        //判断是否已满
        if ((tail + 1) % CAPACITY == head) {
            return false;
        }

        queueArr[tail] = val;
        tail = (tail + 1) % CAPACITY;
        return true;
    }

    public Integer dequeue() {
        //判断是否为空
        if (head == tail) {
            return null;
        }

        int dequeueVal = queueArr[head];
        queueArr[head] = null; //记得置为null

        head = (head + 1) % CAPACITY;

        return dequeueVal;
    }

}
