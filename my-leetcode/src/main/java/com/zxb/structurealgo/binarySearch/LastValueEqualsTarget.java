package com.zxb.structurealgo.binarySearch;

/**
 * @Description 给定一个有序数组查找最后一个等于某个数值的元素
 * Created by xuery on 2018/12/9.
 */
public class LastValueEqualsTarget {
    public static void main(String[] args) {
        int[] arr = {1,2,2,3,3,3,4,4,5};
        System.out.println(findLastValueEqualsTarget(arr,3));
    }

    public static int findLastValueEqualsTarget(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int begin = 0, end = arr.length-1;
        int mid = begin + (end - begin) / 2;
        while (begin <= end) {
            if (arr[mid] == target) {
                if (mid == arr.length - 1 || arr[mid + 1] > target) {
                    return mid;
                } else {
                    //继续二分
                    begin = mid + 1;
                }
            } else if (arr[mid] < target) {
                begin = mid + 1;
            } else{
                end = mid - 1;
            }

            mid = begin + (end - begin) / 2;
        }
        return -1;
    }
}
