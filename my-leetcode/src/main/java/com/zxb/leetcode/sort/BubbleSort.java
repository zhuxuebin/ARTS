package com.zxb.leetcode.sort;

import java.util.Random;

/**
 * 描述：冒泡排序
 *
 * @author xuery
 * @date 2018/11/20
 */
public class BubbleSort {

    private static final int ARR_SIZE = 10;

    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArray(ARR_SIZE, 50);
        optimizeBubbleSort(arr);
        ArrayUtil.printArray(arr);
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //注意这里不能大于等于，有等于则是非稳定排序
                if(arr[j] > arr[j+1]){
                    ArrayUtil.swap(arr, j ,j+1);
                }
            }
        }
    }

    public static void optimizeBubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            boolean continueFlag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //注意这里不能大于等于，有等于则是非稳定排序
                //优化，如果当前内层循环一次都没有进入到swap则说明已经有序了，不需要往下进行了
                if(arr[j] > arr[j+1]){
                    ArrayUtil.swap(arr, j ,j+1);
                    continueFlag = true;
                }
            }
            if (!continueFlag){
                break;
            }
        }
    }

}
