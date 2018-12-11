package com.zxb.structurealgo.binarySearch;

/**
 * @Description 给定一个有序数组查找第一个大于等于某个数值的元素
 * Created by xuery on 2018/12/9.
 */
public class FirstValueLargeOrEqualsTarget {

    public static void main(String[] args) {
        int[] arr = {1,2,2,2,4,4,5};
        System.out.println(findFirstValueLargeOrEqualsTarget(arr,3));
    }

    public static int findFirstValueLargeOrEqualsTarget(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int begin = 0, end = arr.length-1;
        int mid = begin + (end - begin) / 2;
        while (begin <= end) {
            if (arr[mid] >= target) {
                if (mid == 0 || arr[mid - 1] < target) {
                    return mid;
                } else {
                    //继续二分
                    end = mid - 1;
                }
            } else{
                begin = mid + 1;
            }

            mid = begin + (end - begin) / 2;
        }
        return -1;
    }
}
