package com.zxb.structurealgo.heapsort;

/**
 * @ClassName HeapSort
 * @Description 堆排序=构建最大堆+交换堆顶与当前最后一个元素并从堆顶向下堆化（类似删除堆顶元素）
 *
 * 如果下标从0开始，则i的左右子树为：2*i+1,2*i+2
 * 子树为i则父节点为(i-1)/2
 *
 * 则0--n-1,
 * n为奇数则(n-1)/2--n-1是叶子节点，所以从(n-1)/2-1=(n-3)/2开始建堆即可
 * n为偶数则(n+1)/2--n-1是叶子节点，所以从(n-1)/2开始建堆
 *
 * 上述得分各种情况，所以这就是为什么从1开始存储方便
 *
 * 这里还是下标从1开始方便点，不然要考虑两种情况
 * 则i的左右子树为：2*i.2*i+1
 * 子树i的父节点为i/2
 * 1--n, 则n/2+1--n一定是叶子节点，则从n/2开始构建最大堆即可
 * @Author xuery
 * @Date 2019/1/8 15:37
 * @Version 1.0
 */
public class HeapSort {

    public static void main(String[] args) {

    }

    public void heapSort(int[] nums){
        if(nums == null || nums.length == 0){
            return;
        }
        //先构建最大堆
        buildHeap(nums);
    }

    /**
     * 从右往左构建，每次都从上往下堆化，从非叶子节点开始
     * @param nums
     */
    private void buildHeap(int[] nums){
        int n = nums.length;
        for(int i=(n-1)/2;i>=0;i--){

        }
    }
}
