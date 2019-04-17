package com.zxb.structurealgo.practice.day03.sort;

/**
 * @ClassName FindTheKMaxValue
 * @Description O(n)时间内找到一组数中的第K大的元素
 *
 * 11:13-11:40 ok
 *
 * 思路：借鉴快排思想+减枝法（就是根据K的值可以每次排除一半）
 * 这样时间复杂度为什么是O(n)呢？一般情况下：第一次求partition:n,第二次只有一半需要求partition:n/2,...
 * 类推得出时间复杂度为：n+n/2+n/4+...=O(n)
 * @Author xuery
 * @Date 2019/4/17 11:12
 * @Version 1.0
 */
public class FindTheKMaxValue {

    public static void main(String[] args) {
        int[] arr = ArrayGeneUtil.generateIntArray(8,10);
        ArrayGeneUtil.printArray(arr);

        System.out.println(findTheKMaxValue(arr,3));
    }

    /**
     *
     * @param arr
     * @param k 合法输入：k >= 1
     * @return
     */
    public static int findTheKMaxValue(int[] arr, int k){
        if(arr == null || arr.length == 0 || arr.length < k || k <= 0){
            return -1;
        }

        int begin = 0, end = arr.length-1;
        while(begin <= end){

            int p = QuickSort.partition(arr,begin,end);
            if(k-1 == p){
                return arr[p];
            } else if(k - 1 < p){
                //剪去右半边，从左半边查找
                end =  p-1;
            } else {
                //剪去左半边，从右半边查找
                begin = p+1;
            }
        }

        return -1;
    }

}
