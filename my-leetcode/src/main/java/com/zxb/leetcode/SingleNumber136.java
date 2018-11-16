package com.zxb.leetcode;

/**
 * 描述：
 *  给定一个数组，数组中除了一个数字出现一次之外，其他的数字都是出现两次，请找出这个数字
 *  如[2,2,1,1,3,5,3]-->5
 * @author xuery
 * @date 2018/8/20
 */
public class SingleNumber136 {

    public static void main(String[] args) {
        int[] arr = new int[]{2,2,1,1,3,5,3};
        System.out.println(singleNumber(arr));
    }

    public static int singleNumber(int[] arr){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int result = arr[0];
        for(int i=1;i<arr.length;i++){
            result ^= arr[i];
        }
        return result;
    }
}
