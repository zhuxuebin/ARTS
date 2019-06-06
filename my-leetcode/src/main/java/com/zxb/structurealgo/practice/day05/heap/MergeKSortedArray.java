package com.zxb.structurealgo.practice.day05.heap;

import java.util.List;

/**
 * @ClassName MergeKSortedArray
 * @Description 合并多个有序数组
 *
 * 方法1：两两合并，最后合成一个， 假设共有n个数组，每个数组平均元素个数为m，则每次两两合并的时间复杂度为n*m
 * 共需要logn次才能合并完，所以时间复杂度为O(n*m*logn)
 * 这里需要假设每个数组大小足够大，这样可以完成原地归并；当然也可以申请新数组
 * leetcode再搞一遍
 *
 * 方法2：借助优先级队列
 * 先将每个数组的一个元素入队列，之后出队列，出队列的元素对应的数组再进一个元素到队列；直到最后队列元素为空
 * 每次入队列时间复杂度O(logn),所以时间复杂度还是O(n*m*logn)
 *
 *
 * @Author xuery
 * @Date 2019/6/6 15:11
 * @Version 1.0
 */
public class MergeKSortedArray {


    public int[] mergeKSortedArray(List<int[]> arrList){

        if(arrList == null){
            return null;
        }

        return null;
    }
}
