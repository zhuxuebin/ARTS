package com.zxb.leetcode.sort;

/**
 * 描述：计数排序
 * <p>
 * 算法描述：如其名，遍历数组，数组中的值对应计数数组的下标，将对应计数数组下标对应的值+1操作；之后再遍历计数数组即可完成排序
 * <p>
 * 要求数组中元素的值分布在某个比较小的范围内
 * <p>
 * 可以是不稳定排序或者稳定排序
 *
 * @author xuery
 * @date 2018/11/20
 */
public class CountSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArray(10, 20);
        ArrayUtil.printArray(arr);
        stableCountSort(arr);
        ArrayUtil.printArray(arr);
    }

    /**
     * 不稳定的计数排序，无法保证相同数值保证顺序不变，因为是采用从头到尾直接遍历计数数组的方式
     * <p>
     * 假设只有非负整数
     *
     * @param arr
     */
    public static void notStableCountSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        //find max value
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (maxValue < arr[i]) {
                maxValue = arr[i];
            }
        }
        //new a counting arr to count now
        int[] countArr = new int[maxValue + 1];
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]]++;
        }

        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            while (countArr[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }

    /**
     * 上面的方式是不稳定排序，可以采用比较巧妙的方法改写成稳定排序
     * <p>
     * 从头遍历countArr数组，将之前的数值累加到当前位置并放入countSumArr数组中，这样countSumArr[i]就表示当前小于等于i的数值个数为countSumArr[i]
     * 然后从尾部开始遍历countArr, 比如遍历到countArr[i],根据对应的countSumArr[i]的值就知道应该把i放回原数组arr的哪个位置，
     * 并将countArr[i]和countSumArr[i]分别减一, 对于countArr重复该操作，直至countArr[i]变为0
     * 这样可以巧妙的保证相同数值的元素顺序保持不变
     *
     * @param arr
     */
    public static void stableCountSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        //find max value
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (maxValue < arr[i]) {
                maxValue = arr[i];
            }
        }
        //new a counting arr to count now
        int[] countArr = new int[maxValue + 1];
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]]++;
        }

        //calcalate countSumArr just as explained
        int[] countSumArr = new int[maxValue + 1];
        countSumArr[0] = countArr[0];
        for (int i = 1; i < countArr.length; i++) {
            countSumArr[i] = countSumArr[i - 1] + countArr[i];
        }

        for (int i = countArr.length - 1; i >= 0; i--) {
            while(countArr[i]-- > 0){
                arr[--countSumArr[i]] = i;
            }
        }

    }
}
