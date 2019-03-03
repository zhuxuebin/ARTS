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
        int[] nums = {2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
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
            //todo 3.注意2*nums[j]是否越界
            if(nums[i] <= nums[j]){
                //todo 1.如果nums[i],nums[j]均为负数,则nums[i]<=nums[j]时，也可能nums[i]>2*nums[j], -3<=-2,-3>-4
                //所以需要遍历2*num[j...end]与nums[i]的大小，因为i会加1，如果nums[j+1...end]中的某个符合条件的，则会错过
                int k = j;
                while(nums[i] < 0 && k <= end && nums[k] < 0) {
                    long x = (long) nums[i], y = (long) 2 * nums[k];
                    if (x > y) {
                        sum += 1; //注意这里只能加1，如果 += mid-i+1会导致重复统计，因为这里移动的事i
                        k++;
                    } else {
                        break;
                    }
                }
                temp[index++] = nums[i++];
            } else {
                //这里可能存在nums[i] > 2*nums[j],则nums[i..mid]与nums[j]互为重要逆序对,
                //每次都是求前半部分与nums[j]互为重要逆序对的个数
                long x = (long)nums[i], y = (long)2*nums[j];
                if(x > y){
                    sum += mid-i+1;
                } else{
                    /**
                     * todo 2.如果nums[j] < nums[i] <= 2*nums[j],则后续直接移动j,可能导致nums[i+1..mid]与nums[j]互为逆序对被漏掉，额外统计下
                     */
                    int k = i+1;
                    while(k <= mid){
                        long x1 = (long)nums[k], y1 = (long)2*nums[j];
                        if(x1 > y1){
                            sum += mid-k+1;
                            break;
                        }
                        k++;
                    }
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
