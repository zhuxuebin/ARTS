package com.zxb.structurealgo.practice.day05.heap;

import com.zxb.common.ArrayUtil;

import java.util.jar.Attributes;

/**
 * @ClassName HeapSort
 * @Description 堆排 easy
 * @Author xuery
 * @Date 2019/6/6 10:07
 * @Version 1.0
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {-1,5,4,1,3,2,9,7,8,20,18,17,19};
        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(arr);
        for(int i=1;i<arr.length;i++) {
            System.out.println(arr[i]);
        }
    }

    //假设从下标1开始存储数据
    public void heapSort(int[] arr){
        if(arr == null || arr.length <= 1){
            return;
        }

        //1、先建成最大堆，[存在重复不采用]不断从下往上堆化--只需要叶子节点堆化即可（从上往下堆化，无法保证最大值到堆顶）
        // [这个思路好]或者从上往下堆化--只需要堆化非叶子节点，从右侧开始
        buildMaxHeap(arr);

        //2. 将堆顶元素与最后一个未排序的元素交换，然后调整为最大堆重复此过程直至排序完毕
        int lastIndex = arr.length-1;
        while(lastIndex > 1){
            //交换1和lastIndex的元素，然后从堆顶开始往下堆化
            ArrayUtil.swap(arr,1,lastIndex);
            lastIndex--;

            maxHeapify(arr, 1, lastIndex);
        }
    }

    private void maxHeapify(int[] arr, int index, int size){

        int parentIndex = index;
        while(parentIndex <= size/2){
            int leftIndex = 2*parentIndex;
            int rightIndex = 2*parentIndex+1;

            int  maxIndex = leftIndex;
            if(rightIndex <= size && arr[leftIndex] < arr[rightIndex]){
                maxIndex = rightIndex;
            }

            if(arr[parentIndex] < arr[maxIndex]){
                ArrayUtil.swap(arr, parentIndex, maxIndex);
                parentIndex = maxIndex;
            } else {
                break;
            }

        }
    }

    private void buildMaxHeap(int[] arr){
        //1...arr.length-1, 令arr.length-1=size, 则非叶子节点下标从：1...size/2
        int size = arr.length-1;

        int index = size/2;
        //todo xuery 从上往下堆化，错误示范
        while(index >= 1){

           maxHeapify(arr, index, size);

           index--;
        }
    }
}
