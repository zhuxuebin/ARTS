package com.zxb.structurealgo.practice.day02.stack;

import java.time.Instant;
import java.util.Stack;

/**
 * @ClassName StackByArray
 * @Description 数组实现一个顺序栈
 *
 * 思路：很简单，进栈插入尾部并将元素个数+1，出栈从末尾取数据并将元素个数-1
 *
 * 注意点：数组满了之后要扩容，数组为空出栈返回null
 *
 * @Author xuery
 * @Date 2019/4/13 15:05
 * @Version 1.0
 */
public class StackByArray {

    public static void main(String[] args) {
        StackByArray stack = new StackByArray();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    /**
    * 这里就不写扩容操作了
     */

    private int maxSize = 4;

    private int elementNums = 0;

    Integer[] arr;

    public StackByArray(){
        arr = new Integer[maxSize];
    }

    public StackByArray(int maxSize){
        this.maxSize  = maxSize;
        arr = new Integer[maxSize];
    }

    public Integer peek(){
        if(elementNums == 0){
            return null;
        }
        return arr[elementNums-1];
    }

    public Integer pop(){
        if(elementNums == 0){
            return null;
        }
        Integer popValue = arr[elementNums-1];
        elementNums--;
        return popValue;
    }

    public void push(Integer val){
        if(elementNums == maxSize){
            throw  new RuntimeException("stack is already full");
        }
        arr[elementNums] = val;
        elementNums++;
    }

    public boolean isEmpty(){
        return elementNums == 0;
    }
}
