package com.zxb.structurealgo.practice.day03.sort;

import java.util.LinkedList;

/**
 * @ClassName BasicSort
 * @Description 基数排序
 * @Author xuery
 * @Date 2019/4/20 17:09-
 * @Version 1.0
 */
public class BasicSort {

    public static void main(String[] args) {
        int[] arr = ArrayGeneUtil.generateIntArray(10,999);
        ArrayGeneUtil.printArray(arr);

        basicSort(arr,3);
        ArrayGeneUtil.printArray(arr);

    }

    /**
     * 先按个位，再十位，依次类推
     * 每次0-9个桶，排完出桶；然后继续下一次
     * 这里就直接假设最多有三位吧，实际中可能要先遍历一遍求出最大值，再确定最多有多少位
     * @param arr
     * @param k 最多有多少位
     */
    public static void basicSort(int[] arr,int k){

        LinkedList<Integer>[] buckets = new LinkedList[10];
        for(int i=0;i<10;i++){
            buckets[i] = new LinkedList<>();
        }

        int loop = 0;
        while(k-- > 0){

            //看第loop位 (x/10^loop)%10
            int div = 1;
            for(int i=0;i<loop;i++){
                div *= 10;
            }

            //进桶
            for(int i=0;i<arr.length;i++){
                int index = (arr[i]/div)%10;
                buckets[index].add(arr[i]);
            }

            //出桶
            int index = 0;
            for(int i=0;i<10;i++){
                while(!buckets[i].isEmpty()){
                    arr[index++] = buckets[i].poll();
                }
            }

            loop++;
        }

    }
}
