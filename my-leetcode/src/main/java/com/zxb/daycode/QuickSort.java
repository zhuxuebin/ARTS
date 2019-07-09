package com.zxb.daycode;

import com.zxb.common.ArrayUtil;

/**
 * @ClassName QuickSort
 * @Description TODO
 * @Author xuery
 * @Date 2019/7/1 14:32
 * @Version 1.0
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {8,7,6,1,3,5,4,10,9,2,12,11};
        quickSort.quickSort(arr,0,arr.length-1);
        ArrayUtil.printArray(arr);
    }

    public void quickSort(int[] arr, int begin, int end){
        if(begin >= end){
            return;
        }

        int p = partition(arr, begin, end);
        quickSort(arr, begin, p-1);
        quickSort(arr,p+1,end);
    }

    private int partition(int[] arr, int begin, int end){
        int pilot = arr[end];
        int p = begin, q = end;
        while(p < q){
            while(p < q && arr[p] <= pilot){
                p++;
            }
            if(p >= q){
                break;
            }

            arr[q--] = arr[p];//q--中的--别忘了

            while(p < q && arr[q] >= pilot){
                q--;
            }
            if(p >= q){
                break;
            }

            arr[p++] = arr[q]; //p++中的++别忘了
        }
        arr[p] = pilot;

        return p;
    }
}
