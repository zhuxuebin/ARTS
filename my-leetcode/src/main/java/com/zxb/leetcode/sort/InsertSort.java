package com.zxb.leetcode.sort;

import com.zxb.common.ArrayUtil;

/**
 * 描述：插入排序
 *
 * @author xuery
 * @date 2018/11/20
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArray(10, 50);
        insertSort(arr);
        ArrayUtil.printArray(arr);
    }

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            //从下表i-1遍历到0找到arr[i]插入的位置
            int insertIndex = 0;
            int arri = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                //注意，这里也不能取等号，否则就是不稳定排序了
                if(arr[j] > arri){
                    arr[j+1] = arr[j];
                } else {
                    insertIndex = j+1;
                    break;
                }
            }
            arr[insertIndex] = arri;
        }
    }
}
