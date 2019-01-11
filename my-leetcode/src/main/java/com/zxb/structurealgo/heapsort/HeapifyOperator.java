package com.zxb.structurealgo.heapsort;

import com.zxb.common.ArrayUtil;

/**
 * @ClassName HeapifyOperator
 * @Description 向上或者向下堆化，这里都以大顶堆为例
 * <p>
 * 向上堆化：以当前元素为关注点，看它与其父节点的大小，比父节点大则与父节点交换，然后以父节点为关注点递归上去，直至根节点
 * 向下堆化：以当前元素为关注点，看它与其子树较大值的大小，某个子树比它大则交换，然后递归相应子树直至叶子节点
 * <p>
 * 两个操作
 * 1. 插入元素
 * 2. 删除元素
 * 2.1 删除堆顶元素 先将堆顶元素与最后一个元素交换，然后剔除最后一个元素位置，之后从堆顶往下堆化
 * 2.2 删除某个元素 类似删除堆顶元素，先与最后一个元素交换，剔除最后一个元素，然后根据当前删除元素位置的值，
 * 如果它比左右子树都大则向上堆化，否则向下堆化
 * @Author xuery
 * @Date 2019/1/8 15:37
 * @Version 1.0
 */
public class HeapifyOperator {

    /**
     * 这里都从下标1开始存储堆顶元素，主要是方便父节点与子节点下标的转换
     * 父子节点下标为i，则左子树下标为2*i，右子树下标为2*i+1
     * 子树下标为i,则父子节点下标为i/2
     *
     * @param args
     */
    public static void main(String[] args) {
        //0代表没有元素， n=13代表实际元素个数
        int[] arr = new int[]{0, 33, 17, 21, 16, 13, 15, 9, 5, 6, 7, 8, 1, 2, 0, 0};

        HeapifyOperator heapifyOperator = new HeapifyOperator();
        heapifyOperator.insert(arr, 13, 22);

        ArrayUtil.printArray(arr);

        heapifyOperator.deleteTopOfHeap(arr, 14);
        ArrayUtil.printArray(arr);
    }

    /**
     * 将num先插入到最后一个节点,然后向上堆化
     * <p>
     * 这里不是特别严谨，没有更新实际元素个数
     * 伪代码
     * @param arr
     * @param n   实际元素个数，从下标1到n
     * @param num
     */
    public void insert(int[] arr, int n, int num) {

        arr[n + 1] = num;
        //开始向上堆化
        upHeapify(arr, n + 1, n + 1);
    }

    /**
     * 向上堆化
     *
     * @param arr
     * @param n     实际元素个数
     * @param index 当前堆化元素
     */
    public void upHeapify(int[] arr, int n, int index) {
        //截止条件为遍历到根节点 且父节点<当前节点值
        while (index != 1 && arr[index] > arr[index / 2]) {
            ArrayUtil.swap(arr, index, index / 2);

            index = index / 2;
        }
    }


    /**
     * 这里不是特别严谨，没有更新实际元素个数
     * 伪代码
     * @param arr
     * @param n
     */
    public void deleteTopOfHeap(int[] arr, int n) {
        if (n <= 0) {
            return;
        } else if (n == 1) {
            arr[1] = 0;
        } else {
            ArrayUtil.swap(arr, 1, n);
            arr[n] = 0;
            //从先有堆的堆顶往下堆化直至叶子节点, 1--n-1下标有元素，注意如果有n个元素则n/2+1--n都是叶子节点
            int index = 1;
            //非叶子节点 且左右子树存在比当前节点大的
            while (index < (n - 1) / 2 + 1 && (arr[2 * index] > arr[index] || arr[2 * index + 1] > arr[index])) {
                int bigChild = arr[2 * index] > arr[2 * index + 1] ? 2 * index : (2 * index + 1);
                ArrayUtil.swap(arr, index, bigChild);
                index = bigChild;
            }
        }
    }
}
