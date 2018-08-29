package com.zxb.leetcode;

/**
 * 描述：
 *  已知一个数组，数组中除了一个元素只出现一次，其他元素均出现三次；请找出只出现一次的元素
 *  如[1,2,1,1,2,3,2]-->3
 * @author 01368080
 * @date 2018/8/20
 */
public class SingleNumberII137 {
    public static void main(String[] args) {

    }

    //很难想到，说实话 https://cloud.tencent.com/developer/article/1131945
    public static int singleNumberII(int[] arr){
        int result = 0, preResult = 0;
        for(int i=0;i<arr.length;i++){
            // 第一次遇到x时，result = x, preResult = 0
            // 第二次遇到x时，result = 0, preResult = x
            // 第三次遇到x时，result = 0, preResult = 0
            result = (result ^ arr[i]) & ~preResult;
            preResult = (preResult ^ arr[i]) & ~ result;
        }
        return result;
    }
}
