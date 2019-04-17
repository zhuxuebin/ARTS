package com.zxb.structurealgo.practice.day03.sort;

/**
 * @ClassName MergeSort
 * @Description 10min ok
 *
 * @Author xuery
 * @Date 2019/4/17 9:35
 * @Version 1.0
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] arr = ArrayGeneUtil.generateIntArray(10);
        ArrayGeneUtil.printArray(arr);

        mergeSort(arr);

        ArrayGeneUtil.printArray(arr);
    }

    public static void mergeSort(int[] arr){
        if(arr == null || arr.length == 0){
            return;
        }

        //divide and merge
        divideAndMerge(arr,0,arr.length-1);
    }

    private static void divideAndMerge(int[] arr, int begin, int end){
        if(begin >= end){
            return;
        }

        //divide
        int mid = begin + (end-begin)/2;
        divideAndMerge(arr,begin,mid);
        divideAndMerge(arr,mid+1,end);

        //merge
        int[] tempArr = new int[arr.length];
        int index = begin;
        int i = begin,j=mid+1;
        while(i <= mid && j <=end){
            if(arr[i] <= arr[j]){
                tempArr[index++] = arr[i++];
            } else {
                tempArr[index++] = arr[j++];
            }
        }

        while(i<=mid){
            tempArr[index++] = arr[i++];
        }

        while(j <= end){
            tempArr[index++] = arr[j++];
        }

        for(int k = begin;k<=end;k++){
            arr[k] = tempArr[k];
        }
    }
}
