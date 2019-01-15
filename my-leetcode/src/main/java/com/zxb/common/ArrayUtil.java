package com.zxb.common;

import com.zxb.structurealgo.heapsort.mergeSmallFiles.Element;

import java.util.Map;
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
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printArray(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printCharArray(char[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(Element[] arr, int i, int j) {
        Element temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(Map.Entry<Integer, Integer>[] arr, int i, int j) {
        Map.Entry<Integer, Integer> temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
