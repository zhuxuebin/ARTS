package com.zxb.structurealgo.heapsort;

import com.zxb.common.ArrayUtil;
import com.zxb.structurealgo.heapsort.mergeSmallFiles.Element;

/**
 * @ClassName HeapSortUtil
 * @Description 数组-堆排序相关操作
 * @Author xuery
 * @Date 2019/1/11 11:18
 * @Version 1.0
 */
public class HeapSortUtil {

    /**
     * 从右往左构建，每次都从上往下堆化，从非叶子节点开始
     * 根据实际情况还是从下标0开始存储吧
     * 大顶堆
     * @param nums
     * @param n 当前堆实际元素个数, 下标从0--n-1
     */
    public static void buildMaxHeap(int[] nums, int n) {

        int startHeapfiy = n / 2 - 1;
        for (int i = startHeapfiy; i >= 0; i--) {
            //从上往下堆化
            int index = i; //当前需要往下堆化的节点
            maxHeapDownHeapify(nums, n, index);
        }
    }

    /**
     * 建立小顶堆
     * @param nums
     * @param n 当前堆实际元素个数，下标从0--n-1
     */
    public  static void buildMinHeap(int[] nums, int n){
        int startHeapfiy = n / 2 - 1;
        for (int i = startHeapfiy; i >= 0; i--) {
            //从上往下堆化
            int index = i; //当前需要往下堆化的节点
            minHeapDownHeapify(nums, n, index);
        }
    }

    /**
     * 建立小顶堆
     * @param elements
     * @param n 当前堆实际元素个数，下标从0--n-1
     */
    public  static void buildMinHeap(Element[] elements, int n){
        int startHeapfiy = n / 2 - 1;
        for (int i = startHeapfiy; i >= 0; i--) {
            //从上往下堆化
            int index = i; //当前需要往下堆化的节点
            minHeapDownHeapify(elements, n, index);
        }
    }

    /**
     * 大顶堆排序
     * @param nums
     * @param targetIndex 需要与堆顶交换的下标值
     */
    public static void maxHeapSort(int[] nums, int targetIndex) {

        int target = targetIndex; //当前要与堆顶交换元素的下标值
        while (target != 0) {
            ArrayUtil.swap(nums, 0, target);

            //从0往下堆化, target下标对应最后一个有效元素
            int index = 0;
            maxHeapDownHeapify(nums, target, index);

            target--;
        }

    }

    /**
     * 小顶堆排序
     * @param nums
     * @param targetIndex 需要与堆顶交换的下标值
     */
    public static void minHeapSort(int[] nums, int targetIndex) {

        int target = targetIndex; //当前要与堆顶交换元素的下标值
        while (target != 0) {
            ArrayUtil.swap(nums, 0, target);

            //从0往下堆化, target下标对应最后一个有效元素
            int index = 0;
            minHeapDownHeapify(nums, target, index);

            target--;
        }

    }

    /**
     * 大顶堆从某个点开始往下堆化
     * @param nums
     * @param target
     * @param index
     */
    private static void maxHeapDownHeapify(int[] nums, int target, int index) {
        while (true) {
            //注意：这时候target对应的数据要么不存在，要么不算
            if (index >= target / 2) {
                break;
            }

            int maxChildIndex = index;
            //depressed:这里要注意防止n为偶数的时候2*i+2越界（等于startHeapfiy时右子树为空就会越界）
            //这里优化后代码判断减少很多
            if(2*index + 1 < target && nums[maxChildIndex] < nums[2*index+1]){
                maxChildIndex = 2*index + 1;
            }
            if(2*index + 2 < target && nums[maxChildIndex] < nums[2*index +2]){
                maxChildIndex = 2*index + 2;
            }

            if(maxChildIndex == index){
                break;
            }

            ArrayUtil.swap(nums, index, maxChildIndex);
            index = maxChildIndex;
        }
    }

    /**
     * 小顶堆从某个点开始往下堆化
     * @param nums
     * @param target
     * @param index
     */
    private static void minHeapDownHeapify(int[] nums, int target, int index) {
        while (true) {
            //注意：这时候target对应的数据要么不存在，要么不算(因为后续的数据已经有序了)
            if (index >= target / 2) {
                break;
            }

            int minChildIndex = index;
            //depressed:这里要注意防止n为偶数的时候2*i+2越界（等于startHeapfiy时右子树为空就会越界）
            //这里优化后代码判断减少很多
            if(2*index + 1 < target && nums[minChildIndex] > nums[2*index+1]){
                minChildIndex = 2*index + 1;
            }
            if(2*index + 2 < target && nums[minChildIndex] > nums[2*index +2]){
                minChildIndex = 2*index + 2;
            }

            if(minChildIndex == index){
                break;
            }

            ArrayUtil.swap(nums, index, minChildIndex);
            index = minChildIndex;
        }
    }

    /**
     * 小顶堆从某个点开始往下堆化
     * @param elements
     * @param n
     * @param index
     */
    public static void minHeapDownHeapify(Element[] elements, int n, int index) {
        while (true) {
            //注意：这时候n对应的数据要么不存在，要么不算(因为后续的数据已经有序了)
            if (index >= n / 2) {
                break;
            }

            int minChildIndex = index;
            //depressed:这里要注意防止n为偶数的时候2*i+2越界（等于startHeapfiy时右子树为空就会越界）
            //这里优化后代码判断减少很多
            if(2*index + 1 < n && elements[minChildIndex].value > elements[2*index+1].value){
                minChildIndex = 2*index + 1;
            }
            if(2*index + 2 < n && elements[minChildIndex].value > elements[2*index +2].value){
                minChildIndex = 2*index + 2;
            }

            if(minChildIndex == index){
                break;
            }

            ArrayUtil.swap(elements, index, minChildIndex);
            index = minChildIndex;
        }
    }
}
