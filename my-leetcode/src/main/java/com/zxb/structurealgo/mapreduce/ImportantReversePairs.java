package com.zxb.structurealgo.mapreduce;

/**
 * @ClassName ImportantReversePairs
 * @Description 493. Reverse Pairs
 * @Author xuery
 * @Date 2019/3/1 21:53
 * @Version 1.0
 */
class ImportantReversePairs {

    public static void main(String[] args) {
        int[] nums = {1,3,2,3,1};
        ImportantReversePairs reversePairs = new ImportantReversePairs();
        int resSum = reversePairs.reversePairs(nums);
        System.out.println(resSum);
    }

    private int sum = 0;

    public int reversePairs(int[] nums) {

        if(nums == null || nums.length <= 1){
            return 0;
        }

        sum = 0;
        //归并排序 divide + merge
        mergeSort(nums, 0, nums.length-1);

        return sum;
    }

    private void mergeSort(int[] nums, int begin, int end){

        if(begin >= end){
            return;
        }

        //divide into 2 parts [begin, mid], (mid,end]
        int mid = begin + (end-begin)/2;
        mergeSort(nums, begin, mid);
        mergeSort(nums, mid+1, end);

        //merge 2 parts and counts important reverse pair
        int i=begin, j=mid+1;
        int[] temp = new int[nums.length]; //空间复杂度为O（n），串行的，每次计算完都会回收掉
        int index = begin;
        while(i <= mid && j <= end){

            if(nums[i] <= nums[j]){
                temp[index++] = nums[i++];
            } else {
                //这里可能存在nums[i] > 2*nums[j],则nums[i..mid]与nums[j]互为重要逆序对,
                //每次都是求前半部分与nums[j]互为重要逆序对的个数
                if(nums[i] > 2*nums[j]){
                    sum += mid-i+1;
                }
                temp[index++] = nums[j++];
            }
        }

        while(i <= mid){
            temp[index++] = nums[i++];
        }

        while(j <= end){
            temp[index++] = nums[j++];
        }

        for(int k=begin;k<=end;k++){
            nums[k] = temp[k];
        }

        //done end
    }
}
