package com.zxb.leetcode.sort;

import java.util.Random;

/**
 * 描述：
 *
 * @author xuery
 * @date 2018/11/20
 */
public class ArrayUtil {

    private ArrayUtil() {

    }

    private static final Random random = new Random();

    public static int[] generateArray(int n, int bounds) {
        if (n <= 0) {
            return null;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(bounds);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
