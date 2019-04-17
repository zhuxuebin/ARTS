package com.zxb.structurealgo.practice.day03.sort;

/**
 * @ClassName QuickSort
 * @Description 9:54-10:33 40min 太慢了，还要熟练下
 *
 * @Author xuery
 * @Date 2019/4/17 9:53
 * @Version 1.0
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = ArrayGeneUtil.generateIntArray(10);
        ArrayGeneUtil.printArray(arr);

        quickSort(arr);

        ArrayGeneUtil.printArray(arr);
    }


    public static void quickSort(int[] arr){
        if(arr == null || arr.length == 0){
            return;
        }

        quickSort(arr,0,arr.length-1);

    }

    private static void quickSort(int[] arr, int begin, int end){
        if(begin >= end){
            return;
        }

        //关键:partition
        int p = partition(arr,begin,end);
        //一分为3
        quickSort(arr, begin, p-1);
        quickSort(arr,p+1,end);
    }

    public static int partition(int[] arr, int begin, int end){
        //就以最后一个元素为参考点，之后可以再优化
        int pilot = arr[end];
        //采用左右指针填坑法
        int left = begin, right = end;
        while(left < right){

            //1.从左往右找到第一个大于pilot的元素 todo bug1: 参考标准是pilot，一开始写成arr[right]了
            while(left < right && arr[left] <= pilot){
                left++;
            }
            if(left >= right){
                break;
            }
            arr[right--] = arr[left];

            //2. 从右往左找到第一个小于pilot的元素
            while(left < right && arr[right] >= pilot){
                right--;
            }
            if(left >= right){
                break;
            }
            arr[left++] = arr[right];
        }
        int partition = left;
        arr[left] = pilot;

        return partition;
    }
}
