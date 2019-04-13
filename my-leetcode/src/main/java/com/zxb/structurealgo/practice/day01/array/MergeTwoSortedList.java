package com.zxb.structurealgo.practice.day01.array;

/**
 * @ClassName MergeTwoSortedList
 * @Description 合并两个有序数组，arr1,arr2
 *
 * 要求时间复杂度O（n）,空间复杂度O（1），原数组合并
 *
 * 这里假设arr1有足够的未存储元素的空间来存储arr2中的所有元素
 * @Author xuery
 * @Date 2019/4/12 16:09
 * @Version 1.0
 */
public class MergeTwoSortedList {

    public static void main(String[] args) {

        int[] arr1 = new int[20];
        arr1[0]=5;arr1[1]=6;arr1[2]=7;

        int[] arr2 = {8,9,10};

        int[] arr = mergeTwoSortedList(arr1,3,arr2,3);
        for(int i=0;i<6;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }


    public static int[] mergeTwoSortedList(int[] arr1, int len1,int[] arr2, int len2){

        int totalLen = len1 + len2;

        int p1 = len1-1, p2 = len2-1;

        //将数据统一合并到arr1中,从totalLen-1下标开始填充数据
        int index = totalLen - 1;
        while(p1 >= 0 && p2 >=0){
            if(arr1[p1] >= arr2[p2]){
                arr1[index--] = arr1[p1--];
            } else {
                arr1[index--] = arr2[p2--];
            }
        }

        if(p1 >= 0){
            return arr1;
        }

        while(p2 >= 0){
            arr1[index--] = arr2[p2--];
        }

        return arr1;
    }

}
