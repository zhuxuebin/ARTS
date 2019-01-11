package com.zxb.leetcode.sort;

import com.zxb.common.ArrayUtil;

/**
 * 描述：归并排序及非递归实现
 *
 * @author xuery
 * @date 2018/11/20
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArray(7, 50);
        MergeSort mergeSort = new MergeSort();
        ArrayUtil.printArray(arr);
        mergeSort.notRecursionMergeSort(arr);
        ArrayUtil.printArray(arr);
    }

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        recursionMergeSort(arr, 0, arr.length - 1);
    }

    public void recursionMergeSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int mid = begin + (end - begin) / 2;
        //divide into two parts
        recursionMergeSort(arr, begin, mid);
        recursionMergeSort(arr, mid + 1, end);
        //merge
        int[] temp = new int[end - begin + 1];
        int i = begin, j = mid + 1, index = 0;
        while (i <= mid && j <= end) {
            //这里需要有等号，保证稳定性
            if (arr[i] <= arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= end) {
            temp[index++] = arr[j++];
        }
        //将temp复制会arr对应的位置
        for (int k = 0; k < end - begin + 1; k++) {
            arr[k + begin] = temp[k];
        }
    }

    /**
     * 非递归实现归并排序
     * 思路很简单：就是没有递，只有归，先1+1合并，再2+2合并，最后n/2+n/2合并
     * 如何编写代码：自己举例，确定两两合并的边界，边界一旦确定直接采用之前的merge就可以了
     * 注意点：两两个数不一定相同，要注意越界条件等的判断
     *
     * bug:一定要注意对某些变量操作完之后，它已经不代表最初的值了，想要最初的值就不能对变量操作或者新建一个变量保存最初的值
     * @param arr
     */
    public void notRecursionMergeSort(int[] arr) {

        //step表示step个元素+step个元素合并
        int step = 1;
        while (step < arr.length) {
            for (int i = 0; i < arr.length; i = i + 2 * step) {
                //i--i+step-1合并，i+step--i+2*step-1合并，都包括边界，注意越界条件判断
                //i+step>=arr.length时右边一半没有，结束本step合并
                if (i + step >= arr.length) {
                    break;
                }
                //可以开始合并
                int mid = i + step - 1, right = Math.min(i + 2 * step - 1, arr.length - 1);
                int[] temp = new int[right - i + 1];
                int leftIndex = i, rightIndex = mid + 1, index = 0;
                while (leftIndex <= mid && rightIndex <= right) {
                    if (arr[leftIndex] <= arr[rightIndex]) {
                        temp[index++] = arr[leftIndex++];
                    } else {
                        temp[index++] = arr[rightIndex++];
                    }
                }
                while (leftIndex <= mid) {
                    temp[index++] = arr[leftIndex++];
                }
                while (rightIndex <= right) {
                    temp[index++] = arr[rightIndex++];
                }
                for (int k = 0; k < right - i + 1; k++) {
                    arr[k + i] = temp[k];
                }
            }
            step = step * 2;
        }
    }
}
