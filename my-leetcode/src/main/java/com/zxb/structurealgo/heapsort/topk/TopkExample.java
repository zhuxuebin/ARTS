package com.zxb.structurealgo.heapsort.topk;

import com.zxb.structurealgo.heapsort.HeapSortUtil;
import com.zxb.structurealgo.heapsort.HeapifyOperator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName TopkExample
 * @Description 这里就不模拟将一个大文件或者大数据通过hash取模路由到不同的小文件中，由不同的机器或者多线程并发处理后再归总（mapReduce）
 * <p>
 * 这里只模拟按照单线程计算topK,思路如下：
 * 1.通过hashMap统计出每个词条出现的次数
 * 2.遍历hashMap根据value值建立最小堆，将每次遍历的结果与最小堆堆顶比较，小于直接忽略，大于则替换掉堆顶元素并重新堆化（记得先初始化最小堆）
 * 3.如果topK需要有序，则可以利用堆排排下序
 * 时间复杂度：O(nlogk)
 *
 * 如果直接整体排序则时间复杂度为O(nlogn),当n很大时，O(nlogn) >>> O(nlogk)
 * @Author xuery
 * @Date 2019/1/15 10:29
 * @Version 1.0
 */
public class TopkExample {

    public static void main(String[] args) {
        int N = 100;
        Random random = new Random();
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = random.nextInt(10);
        }
        topK(arr, 5);
    }

    public static void topK(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return;
        }
        //1.构建hashMap
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            countMap.put(arr[i], countMap.get(arr[i]) == null ? 1 : countMap.get(arr[i]) + 1);
        }

        //2.构建最小堆
        Map.Entry[] minHeap = new Map.Entry[k];
        int currNum = 0; //堆中当前元素个数
        boolean first = true; //是否是第一次建堆
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if(currNum < k){
                //初始化堆
                minHeap[currNum++] = entry;
            } else {
                //第一次需要先构建最小堆
                if(first) {
                    HeapSortUtil.buildMinHeap(minHeap, currNum);
                    first = false;
                } else {
                    //非第一次则与堆顶元素比较,大于则替换堆顶元素，并向下堆化; 小于等于则丢弃
                    if(entry.getValue() > (Integer) minHeap[0].getValue()){
                        minHeap[0] = entry;
                        HeapSortUtil.minHeapDownHeapify(minHeap, currNum, 0);
                    }
                }
            }
        }

        //先构建下最小堆，可能存在currNum < k的场景
        HeapSortUtil.buildMinHeap(minHeap, currNum);
        HeapSortUtil.minHeapSort(minHeap, currNum-1);

        for(int i=0;i<currNum;i++){
            System.out.println(minHeap[i].getKey()+":"+minHeap[i].getValue());
        }
    }
}

