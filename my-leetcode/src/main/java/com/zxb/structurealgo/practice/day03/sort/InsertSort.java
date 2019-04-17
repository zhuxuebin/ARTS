package com.zxb.structurealgo.practice.day03.sort;

/**
 * @ClassName InsertSort
 * @Description 插入排序 10:45-10:59 ok
 * @Author xuery
 * @Date 2019/4/17 10:33
 * @Version 1.0
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = ArrayGeneUtil.generateIntArray(10);
        ArrayGeneUtil.printArray(arr);

        insertSort(arr);

        ArrayGeneUtil.printArray(arr);
    }

    public static void insertSort(int[] arr){
        if(arr == null || arr.length == 0){
            return;
        }

        for(int i=1;i<arr.length;i++){
            //找到arr[i]应该插入到arr[0..i-1]的哪个位置，从末尾开始找，找到第一个小于等于arr[i]的下标，插入到它后面就可以了
            int target = arr[i];
            int index = -1;
            for(int j=i-1;j>=0;j--){
                if(arr[j] <= target){
                    index = j;
                    break;
                }
            }
            //将[index+1...i-1]的元素后移一位
            for(int j=i-1;j>=index+1;j--){
                arr[j+1] = arr[j];
            }
            arr[index+1] = target;
        }

    }
}
