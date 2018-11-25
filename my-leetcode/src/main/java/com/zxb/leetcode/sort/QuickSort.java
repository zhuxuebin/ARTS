package com.zxb.leetcode.sort;

import java.util.Random;

/**
 * Created by xuery on 2018/9/22.
 * https://blog.csdn.net/MoreWindows/article/details/6684558 --填坑法
 */
public class QuickSort {

    private QuickSort(){

    }

    private static Random random = new Random();

    public static void main(String[] args) {
        int[] arr = new int[100];
        for(int i=0;i<100;i++){
            arr[i] = random.nextInt(Integer.MAX_VALUE);
        }
        QuickSort.quickSort(arr);
        for(int i=0;i<arr.length;i++) {
            System.out.println(arr[i]);
        }
    }

    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    public static void quickSort(int[] arr, int begin, int end){
        if(begin > end){
            return;
        }
        int p = partition(arr, begin, end);
        quickSort(arr, begin, p-1);
        quickSort(arr, p+1, end);
    }

    public static int partition(int[] arr, int begin, int end){
        // 1. select random p between begin and end
        int p = begin + random.nextInt(end+1-begin);
        // 2. move the element less than arr[p] to p's left, ...
        int pVal = arr[p];
        //交换随机选择的p与end，转换成选择最后一个元素为基准
        ArrayUtil.swap(arr, p, end);
        int i = begin, j = end;
        while(i < j){
            while(i < j && arr[i] <= pVal){
                i++;
            }
            //填arr[j]这个坑, 大填小（arr[end]为第一个坑）
            if(i < j){
                arr[j] = arr[i];
                j--;
            }
            //接下来要填arr[i]这个坑，小填大
            while(i < j && arr[j] >= pVal){
                j--;
            }
            if(i < j){
                arr[i] = arr[j];
                i++;
            }
        }
        arr[i] = pVal;
        return i;
    }
}
