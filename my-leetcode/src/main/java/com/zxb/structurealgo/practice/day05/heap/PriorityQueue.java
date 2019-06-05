package com.zxb.structurealgo.practice.day05.heap;

/**
 * @ClassName PriorityQueue
 * @Description 优先级队列
 *
 * 可以借助大小顶堆
 * 每次删除元素或者插入元素，通过向上或者向下堆化，始终保证堆顶元素时最大值或者最小值
 * @Author xuery
 * @Date 2019/5/9 19:09
 * @Version 1.0
 */
public class PriorityQueue {

    public Element poll(){
        return null;
    }
}

class Element{
    private int priority;

    private String key;

    public Element(int priority, String key){
        this.priority = priority;
        this.key = key;
    }
}
