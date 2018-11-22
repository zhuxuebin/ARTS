package com.zxb.leetcode.sort;

import java.util.Random;

/**
 * 描述：快排填坑法再搞一遍
 * 非稳定排序，相同值可能由于partition导致顺序改变
 *
 * @author xuery
 * @date 2018/11/20
 */
public class QuickSort2 {

    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArray(10, 20);
        ArrayUtil.printArray(arr);
        quickSort(arr);
        ArrayUtil.printArray(arr);
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }

        int partition = partition(arr, begin, end);

        //divide into three parts including [begin,partition-1],partition,[partition+1,end]
        quickSort(arr, begin, partition - 1);
        quickSort(arr, partition + 1, end);
    }

    //挖坑：第一个坑是基准所在的位置，填坑条件：与基准值比较，每次循环填两个坑，一左一右
    public static int partition(int[] arr, int begin, int end) {
        //随机取其中的一个值作为基准并与最后一个值交换
        int pIndex = begin + random.nextInt(end - begin + 1);
        if (pIndex != end) {
            ArrayUtil.swap(arr, pIndex, end);
        }
        int pivot = arr[end];
        int i = begin, j = end;
        while (i < j) {
            //从左往右找第一个大于pivot的
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }

            //从右往左找第一个小于pivot的
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
        }
        arr[i] = pivot;
        return i;
    }
}
