package com.zxb.structurealgo.heapsort;

import com.zxb.common.ArrayUtil;

/**
 * @ClassName HeapSort
 * @Description 堆排序=构建最大堆+交换堆顶与当前最后一个元素并从堆顶向下堆化（类似删除堆顶元素）
 * <p>
 * 如果下标从0开始，则i的左右子树为：2*i+1,2*i+2
 * 子树为i则父节点为(i-1)/2
 * <p>
 * 则0--n-1,
 * n为奇数则n/2--n-1是叶子节点，所以从n/2-1开始建堆即可
 * n为偶数则n/2--n-1是叶子节点，所以从n/2-1开始建堆
 * 所以可以统一：n/2-1开始建堆即可
 * <p>
 * 上述得分各种情况，所以这就是为什么从1开始存储方便
 * <p>
 * 如果下标从1开始存储
 * 则i的左右子树为：2*i,2*i+1
 * 子树i的父节点为i/2
 * 1--n, 则n/2+1--n一定是叶子节点，则从n/2开始构建最大堆即可
 * @Author xuery
 * @Date 2019/1/8 15:37
 * @Version 1.0
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] nums = {10,7,1,2,4,5,8,3,6};
        HeapSort heapSort = new HeapSort();
        heapSort.heapSortAsc(nums);
        ArrayUtil.printArray(nums);

        heapSort.heapSortDesc(nums);
        ArrayUtil.printArray(nums);
    }

    public void heapSortAsc(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        //1.先构建最大堆
        HeapSortUtil.buildMaxHeap(nums, nums.length);

        //2.开始排序，从最后一个点开始，按照删除节点的思路：与堆顶元素先交换、堆化，然后倒数第二点直至遍历完
        HeapSortUtil.maxHeapSort(nums, nums.length - 1);
    }

    public void heapSortDesc(int[] nums){
        if (nums == null || nums.length <= 1) {
            return;
        }
        //1.先构建最小堆
        HeapSortUtil.buildMinHeap(nums, nums.length);

        //2.开始排序，从最后一个点开始，按照删除节点的思路：与堆顶元素先交换、堆化，然后倒数第二点直至遍历完
        HeapSortUtil.minHeapSort(nums, nums.length - 1);
    }

}
