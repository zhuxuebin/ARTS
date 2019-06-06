package com.zxb.structurealgo.practice.day05.heap;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName PriorityQueue
 * @Description 优先级队列
 *
 * 可以借助大小顶堆
 * 每次删除元素或者插入元素，通过向上或者向下堆化，始终保证堆顶元素时最大值或者最小值
 *
 * 这里假设每次优先出priority小的元素
 *
 * @Author xuery
 * @Date 2019/5/9 19:09
 * @Version 1.0
 */
public class PriorityQueue {

    private int size;
    private Element[] elements;

    private int currSize = 0; //当前elements中的元素个数，下标从1开始，为了方便计算父节点和叶子节点的关系

    public PriorityQueue(int size){
        this.size = size;
        elements = new Element[size+1];
    }

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(8);

        priorityQueue.push(new Element(3,"p3"));
        priorityQueue.push(new Element(4,"p4"));
        priorityQueue.push(new Element(5,"p5"));
        priorityQueue.push(new Element(1,"p1"));

        System.out.println(priorityQueue.poll());

        priorityQueue.push(new Element(9,"p9"));
        priorityQueue.push(new Element(6,"p6"));
        priorityQueue.push(new Element(2,"p2"));

        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
    }

    public Element poll(){
        if(currSize == 0){
            return null;
        }

        Element poll = elements[1];
        //并删除堆顶元素，将堆顶元素与最后一个元素交换；然后currSize--,并从堆顶开始向下堆化即可
        swap(elements,1, currSize);
        currSize--;

        int parentIndex = 1;
        while(parentIndex <= currSize/2){
            int leftIndex = parentIndex*2;
            int rightIndex = parentIndex*2 + 1;

            //找出左右子树中priority小的元素
            int minIndex = leftIndex;
            if(rightIndex <= currSize && elements[rightIndex].priority < elements[leftIndex].priority){
                minIndex = rightIndex;
            }

            if(elements[parentIndex].priority > elements[minIndex].priority){
                ///交换parent和minIndex
                swap(elements, parentIndex, minIndex);
                parentIndex = minIndex;
            } else {
                break;
            }
        }

        return poll;
    }

    private void swap(Element[] elements, int i, int j){
        Element temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }


    public boolean push(Element element){

        if(currSize == size){
            return false;
        }

        //先插入末尾，再从末尾往上堆化
        elements[++currSize] = element;

        int index = currSize;
        while(index > 1){
            int parentIndex = index/2;
            if(elements[parentIndex].priority > elements[index].priority){
                swap(elements, index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }

        return true;
    }

}

class Element{
    //默认为friendly，只有当前类和同包才能访问
    int priority;

    String key;

    public Element(int priority, String key){
        this.priority = priority;
        this.key = key;
    }

    @Override
    public String toString() {
        return "Element{" +
                "priority=" + priority +
                ", key='" + key + '\'' +
                '}';
    }
}
