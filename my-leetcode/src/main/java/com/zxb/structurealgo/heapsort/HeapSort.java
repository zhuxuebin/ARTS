package com.zxb.structurealgo.heapsort;

import com.zxb.leetcode.sort.ArrayUtil;

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
        heapSort.heapSort(nums);
        ArrayUtil.printArray(nums);
    }

    public void heapSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        //1.先构建最大堆
        buildHeap(nums);

        //2.开始排序，从最后一个点开始，按照删除节点的思路：与堆顶元素先交换、堆化，然后倒数第二点直至遍历完
        swapTopToRightLocation(nums, nums.length - 1);
    }

    /**
     * @param nums
     * @param maxIndex 需要与堆顶交换的下标值的初始值
     */
    private void swapTopToRightLocation(int[] nums, int maxIndex) {

        int target = maxIndex; //当前要与堆顶交换元素的下标值
        while (target != 0) {
            ArrayUtil.swap(nums, 0, target);

            //从0往下堆化, target下标对应最后一个有效元素
            int index = 0;
            downHeapify(nums, target, index);

            target--;
        }

    }

    private void downHeapify(int[] nums, int target, int index) {
        while (true) {
            //注意：这时候target对应的数据要么不存在，要么不算
            if (index >= target / 2) {
                break;
            }
            //这里要注意防止n为偶数的时候2*i+2越界（等于startHeapfiy时右子树为空就会越界）
            if (nums[index] < nums[2 * index + 1] || (2 * index + 2 < target && nums[index] < nums[2 * index + 2])) {
                int maxChildIndex;
                if(2*index + 2 < target){
                    maxChildIndex = nums[2*index+1] > nums[2*index+2]?2*index+1:2*index+2;
                } else {
                    maxChildIndex = 2*index + 1;
                }
                ArrayUtil.swap(nums, index, maxChildIndex);
                index = maxChildIndex;
            } else {
                break;
            }
        }
    }


    /**
     * 从右往左构建，每次都从上往下堆化，从非叶子节点开始
     * 根据实际情况还是从下标0开始存储吧
     *
     * @param nums
     */
    private void buildHeap(int[] nums) {
        int n = nums.length;

        int startHeapfiy = n / 2 - 1;
        for (int i = startHeapfiy; i >= 0; i--) {
            //从上往下堆化
            int index = i; //当前需要往下堆化的节点
            downHeapify(nums, n, index);
        }
    }
}
