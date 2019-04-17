package com.zxb.structurealgo.practice.day03.binarySearch;

import com.zxb.structurealgo.practice.day03.sort.ArrayGeneUtil;
import com.zxb.structurealgo.practice.day03.sort.QuickSort;

/**
 * @ClassName SearchFirstEqualOrBigger
 * @Description 有序数组中查找第一个大于等于某个数的下标
 * @Author xuery 14:20-14:35 ok
 * @Date 2019/4/17 14:17
 * @Version 1.0
 */
public class SearchFirstEqualOrBigger {

    public static void main(String[] args) {

        int[] arr = ArrayGeneUtil.generateIntArray(8,10);

        QuickSort.quickSort(arr);
        ArrayGeneUtil.printArray(arr);

        System.out.println(searchFirstEqualOrBigger(arr,3));

    }


    public static int searchFirstEqualOrBigger(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int begin = 0, end = arr.length - 1;
        int mid = begin + (end - begin) / 2;
        while (begin <= end) {
            if (arr[mid] >= target) {
                if(mid - 1 < 0 || arr[mid-1] < target){
                    return mid;
                } else{
                    //重新二分
                    end = mid -1;
                }
            } else {
                begin = mid + 1;
            }
            mid = begin + (end-begin)/2;
        }
        return -1;
    }
}
