package com.zxb.daycode;

/**
 * @ClassName FindNearestValue
 * @Description 给定一个有序数组，找到数组中最接近指定值的元素
 * @Author xuery
 * @Date 2019/6/6 9:26
 * @Version 1.0
 */
public class FindNearestValue {

    public static void main(String[] args) {

        int[] arr = {1,3,5,8,10,12,13,15,19,21,24};

        FindNearestValue findNearestValue = new FindNearestValue();
        System.out.println(findNearestValue.findNearestValue(arr,23));
    }


    public int findNearestValue(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int begin = 0, end = arr.length - 1;
        int mid = begin + (end - begin) / 2;

        while (begin <= end) {
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                //先看下arr[mid-1]与target的大小
                if (mid - 1 < 0) {
                    return mid;
                } else if (arr[mid - 1] < target) {
                    //则arr[mid-1]与arr[mid]二者取其1
                    if(target - arr[mid-1] > arr[mid] - target){
                        return mid;
                    } else {
                        return mid-1;
                    }
                } else {
                    end = mid - 1;
                }
            } else {
                //先看下arr[mid+1]与target的大小
                if (mid + 1 >= arr.length) {
                    return mid;
                } else if (arr[mid + 1] > target) {
                    //arr[mid]与arr[mid+1]二者取1
                    if(target - arr[mid] > arr[mid+1]-target){
                        return mid+1;
                    } else {
                        return mid;
                    }
                } else {
                    begin = mid + 1;
                }
            }

            mid = begin + (end-begin)/2;
        }

        return -1;
    }
}
