package com.zxb.structurealgo.practice.day07;

/**
 * @ClassName ReversePairs
 * @Description leetcode:493. Reverse Pairs
 * @Author xuery
 * @Date 2019/6/14 11:12
 * @Version 1.0
 */
class ReversePairs {

    public static void main(String[] args) {

        ReversePairs reversePairs = new ReversePairs();
        int[] arr1 = {1,2};
    }

    //相比于nums[i] > nums[j]的逆序对，统计逆序对时不要放在归并的时候就可以了，单独针对nums[i] > 2*nums[j]统计下
    //注意2*nums[j]的越界问题
    public int reversePairs(int[] nums) {


        if(nums == null || nums.length == 0){
            return 0;
        }

        mergeSort(nums, 0, nums.length-1);

        return sum;
    }

    int sum = 0;
    private void mergeSort(int[] nums, int begin, int end){
        if(begin >= end){
            return;
        }

        int mid = begin + (end-begin)/2;
        mergeSort(nums,begin, mid);
        mergeSort(nums, mid+1, end);

        int i= begin, j= mid+1;
        int[] temp = new int[nums.length];
        int index = begin;
        while(i <= mid && j <= end){

            if(nums[i] <= nums[j]){
                temp[index++] = nums[i++];
            } else {
                temp[index++] = nums[j++];
            }
        }

        while(i<=mid){
            temp[index++] = nums[i++];
        }

        while(j <= end){
            temp[index++] = nums[j++];
        }

        //单独统计一波逆序对
        int ii = begin, jj=mid+1;
        while(ii <= mid && jj <= end){
            long x = (long)nums[ii];
            long y = 2*(long)nums[jj];
            if(x > y){
                sum += (mid-ii+1);
                jj++;
            } else {
                ii++;
            }
        }


        for(int k=begin;k<=end;k++){
            nums[k] = temp[k];
        }
    }
}
