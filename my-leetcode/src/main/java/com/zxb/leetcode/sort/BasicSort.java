package com.zxb.leetcode.sort;

import java.util.LinkedList;
import java.util.Random;

/**
 * 描述：
 * 基数排序也称为桶排序
 * 按照每一位排序入笼，然依次遍历回数组
 * 有多少位就上述循环处理多少次，空间换时间算法
 * for循环时间复杂度，O（n）
 *
 * @author 01368080
 * @date 2018/10/11
 */
public class BasicSort {

    public static void main(String[] args) {
        int[] arr = new int[10];
        Random random = new Random();
        for(int i=0;i<arr.length;i++){
            arr[i] = random.nextInt(1000);
        }
        BasicSort basicSort = new BasicSort();
        basicSort.basicSort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public void basicSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        //确定位数，先找到最大的, 考虑下基数排序适用于负数数组吗？不适合，真要做需要一分为2，正数和负数部分，这里暂时不考虑
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        int loopCnt = 0;
        while (maxValue != 0) {
            maxValue /= 10;
            loopCnt++;
        }
        //0-9共10个桶,从个位开始
        LinkedList<Integer>[] buckets = new LinkedList[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new LinkedList<>();
        }

        int cnt = 0;
        while (loopCnt-- > 0) {
            //入桶
            for (int i = 0; i < arr.length; i++) {
                int bucketIndex = (int)(arr[i]/Math.pow(10,cnt)) % 10;
                buckets[bucketIndex].add(arr[i]);
            }
            cnt++;
            //桶数据按顺序回到数组
            int arrIndex = 0;
            for(int i=0;i<10;i++){
                while(!buckets[i].isEmpty()){
                    arr[arrIndex++] = buckets[i].poll();
                }
            }
        }
    }
}
