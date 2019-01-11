package com.zxb.leetcode.topk;

import com.zxb.leetcode.sort.QuickSort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by xuery on 2018/9/22.
 * 给定一个n个数字大小的数组，求k个最大的元素
 * 1. 排序之后求O（nlogn）
 * 2. 冒泡排序，每次遍历算出一个值，O（kn）
 * 3. 堆排，O（nlogk）
 * 4. 快排，归并法，O(nlogn)
 * 5. 减治法O(n)
 */
public class TopKAlgo {

    private static int quickSortCount = 0;
    private static int reduceQuickSortCount = 0;

    public static void main(String[] args) {
        TopKAlgo topKAlgo = new TopKAlgo();
        int MAX_LEN = 10;
        int[] arr = new int[MAX_LEN];
        Random random = new Random();
        for (int i = 0; i < MAX_LEN; i++) {
            arr[i] = random.nextInt(100);
        }
        printArr(arr);

        //两种方式
        int[] resArr = topKAlgo.getTopKByQuickSort(arr, 3);
        printArr(resArr);
        System.out.println(quickSortCount);

        Arrays.sort(arr);
        printArr(arr);

    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 方法1：快排法
     */
    public int[] getTopKByQuickSort(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int[] resArr = new int[k];
        quickSort(arr, 0, arr.length - 1, k, resArr);
        return resArr;
    }

    public void quickSort(int[] arr, int begin, int end, int k, int[] resArr) {
        quickSortCount++;
        if (begin > end) {
            //在这种条件下不能取等于，如果取等于则需要在里面判断等于时arr.length-begin == k,和后面一样的逻辑
            return;
        }
        int p = QuickSort.partition(arr, begin, end);
        if (arr.length - p == k) {
            //说明p及p之后共有k个最大数,找到则直接返回，后续快排不再进行
            for (int i = 0; i < k; i++) {
                resArr[i] = arr[i + p];
            }
            return;
        }
        quickSort(arr, begin, p - 1, k, resArr);
        quickSort(arr, p + 1, end, k, resArr);
    }

    /**
     * 方法2：减治法 O(n)
     * 基本思路：很简单，就是快排的优化，减少不必要的分支
     * 根据p的值判断，
     * currK = arr.length - p < k则只需要保留左侧分支，取其中的top(k-currK),同理遍历,不过注意写程序还是有一定区别的
     * currK = arr.length - p > k则只需要保留右侧分支，取其中的topK
     * currK = arr.length - p == k 则说明已经找到返回即可
     */
    public int[] getTopKByReduceQuickSort(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int[] resArr = new int[k];
        reduceQuickSort(arr, 0, arr.length - 1, k, resArr);
        return resArr;
    }

    public void reduceQuickSort(int[] arr, int begin, int end, int k, int[] resArr) {
        reduceQuickSortCount++;
        if (begin > end) {
            //在这种条件下不能取等于，如果取等于则需要在里面判断等于时arr.length-begin == k,和后面一样的逻辑
            return;
        }
        int p = QuickSort.partition(arr, begin, end);
        int currK = arr.length - p;
        if (currK == k) {
            //说明p及p之后共有k个最大数,找到则直接返回，后续快排不再进行
            for (int i = 0; i < k; i++) {
                resArr[i] = arr[i + p];
            }
        } else if (currK < k) {
            reduceQuickSort(arr, begin, p - 1, k, resArr);
        } else {
            reduceQuickSort(arr, p + 1, end, k, resArr);
        }
    }

}
