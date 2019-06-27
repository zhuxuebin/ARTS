package com.zxb.leetcode.dp;

/**
 * @ClassName PartitionSetIntoTwoSubsets
 * @Description https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
 *
 * 将一个数组分成两部分，使两部分和之差相差最小，返回最小值
 * @Author xuery
 * @Date 2019/6/27 14:51
 * @Version 1.0
 */
public class PartitionSetIntoTwoSubsets {

    public static void main(String[] args) {
        PartitionSetIntoTwoSubsets psts = new PartitionSetIntoTwoSubsets();
        int[] arr =  {1, 6, 11, 5};
        int min = psts.findMin(arr);

        System.out.println(min);
    }


    public int findMin(int[] arr){

        findMinByBacktrack(arr,0,0,0);

        return min;
    }


    int min = Integer.MAX_VALUE;
    //1. 采用回溯全遍历法，arr[index]要么set1取，要么set2取（两种取法）
    public void findMinByBacktrack(int[] arr, int sum1, int sum2, int index){
        if(index == arr.length){
            min = Math.min(min, Math.abs(sum1-sum2));
            return;
        }

        //set1取
        findMinByBacktrack(arr, sum1+arr[index], sum2, index+1);
        //set2取
        findMinByBacktrack(arr, sum1, sum2 + arr[index], index+1);
    }

    //2. dp法思考下
}
