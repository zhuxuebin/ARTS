package com.zxb.structurealgo.practice.day03.binarySearch;

/**
 * @ClassName BinarySearch
 *
 *一定要学会头脑单元测试
 * @Description 11:41-11:48 ok
 * @Author xuery
 * @Date 2019/4/17 11:41
 * @Version 1.0
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,4,5,6,7};
        int index = binarySearch(arr,6);
        System.out.println(index);

        String s = "111";
    }

    public static int binarySearch(int[] arr,int target){
        if(arr == null || arr.length == 0){
            return -1;
        }

        int begin = 0, end = arr.length-1;
        int mid = begin+(end-begin)/2;
        while(begin <= end){
            if(arr[mid] == target){
                return mid;
            } else if(arr[mid] > target){
                end = mid -1;
            } else {
                begin = mid+1;
            }
            mid = begin+(end-begin)/2;
        }
        return -1;
    }
}
