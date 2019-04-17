package com.zxb.structurealgo.practice.day03.sort;

/**
 * @ClassName BubbleSort
 * @Description 11:00-11:11 ok
 * @Author xuery
 * @Date 2019/4/17 11:00
 * @Version 1.0
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = ArrayGeneUtil.generateIntArray(10);
        ArrayGeneUtil.printArray(arr);

        bubbleSort(arr);

        ArrayGeneUtil.printArray(arr);
    }

    //每次将最大元素通过交换移到右边
    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length == 0){
            return;
        }

        for(int i=0;i<arr.length-1;i++){
            boolean continueFlag = false;
            for(int j=0;j<arr.length-1-i;j++){
                //compare arr[j+1] arr[j], 不取等，取等就不是稳定排序了
                if(arr[j+1] < arr[j]){
                    ArrayGeneUtil.swap(arr,j,j+1);
                    continueFlag = true;
                }
            }
            if(!continueFlag){
                break;
            }
        }
    }
}
