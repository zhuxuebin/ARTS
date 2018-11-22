package com.zxb.leetcode.sort;

import java.util.Random;

/**
 * 描述：选择排序
 *
 * @author xuery
 * @date 2018/11/20
 */
public class SelectSort {

    private static final int ARR_SIZE = 10;

    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArray(ARR_SIZE, 50);
        selectSort(arr);
        ArrayUtil.printArray(arr);
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                ArrayUtil.swap(arr, i, minIndex);
            }
        }
    }
}
