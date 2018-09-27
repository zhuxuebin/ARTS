package com.zxb.leetcode.topk;

/**
 * Created by xuery on 2018/9/22.
 * 给定一个n个数字大小的数组，求k个最大的元素
 * 1. 排序之后求O（nlogn）
 * 2. 冒泡排序，每次遍历算出一个值，O（kn）
 * 3. 堆排，O（nlogk）
 * 4. 快排，归并法，O(nlogn)
 * 5. 减治法
 */
public class TopKAlgo {

    //减治法的相关代码
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        });
        t.start();
        t.start();
    }
}
