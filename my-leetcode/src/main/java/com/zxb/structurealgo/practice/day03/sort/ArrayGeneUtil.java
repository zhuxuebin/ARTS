package com.zxb.structurealgo.practice.day03.sort;

import java.util.Random;

/**
 * @ClassName ArrayGeneUtil
 * @Description 产生测试数组类
 * @Author xuery
 * @Date 2019/4/17 9:36
 * @Version 1.0
 */
public class ArrayGeneUtil {

    private static Random random = new Random();

    public static int[] generateIntArray(int n){
        return generateIntArray(n,100);
    }

    public static int[] generateIntArray(int n, int bound){
        if(n <= 0){
            return null;
        }
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }

    public static void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
