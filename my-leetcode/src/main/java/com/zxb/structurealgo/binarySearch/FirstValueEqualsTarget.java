package com.zxb.structurealgo.binarySearch;

/**
 * @Description 给定一个有序数组查找第一个等于某个数值的元素
 * <p>
 * 思路：很简单，判断是否是第一个，是则返回，不是则继续二分查找
 * Created by xuery on 2018/12/9.
 */
public class FirstValueEqualsTarget {

    public static void main(String[] args) {
        int[] arr = {1,2,2,3,3,3,4,4,5};
        System.out.println(findFirstValueEqualsTarget(arr,4));
    }

    public static int findFirstValueEqualsTarget(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int begin = 0, end = arr.length-1;
        int mid = begin + (end - begin) / 2;
        while (begin <= end) {
            if (arr[mid] == target) {
                if (mid == 0 || arr[mid - 1] < target) {
                    return mid;
                } else {
                    //继续二分
                    end = mid - 1;
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
