package com.zxb.daycode;

/**
 * @ClassName MultiThreadPrintArray
 * @Description 多个线程顺序打印一个数组的元素
 * <p>
 * 思路：加锁，每个线程只打印自己的，如果没有轮到自己打印则wait，轮到自己打印则打印并notifyAll
 * @Author xuery
 * @Date 2019/6/5 21:06
 * @Version 1.0
 */
public class MultiThreadPrintArray {

    private static final Object lock = new Object();

    static volatile int currIndex = 0;

    static int threadNum = 3;

    public static void main(String[] args) {
        MultiThreadPrintArray multiThreadPrintArray = new MultiThreadPrintArray();

        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i = 0; i < threadNum; i++) {
            new Thread(new MultiThread(arr,i)).start();
        }
    }


    static class MultiThread implements Runnable {

        int[] arr;
        int threadIndex;

        public MultiThread(int[] arr, int threadIndex) {
            this.arr = arr;
            this.threadIndex = threadIndex;
        }

        @Override
        public void run() {
            while (currIndex < arr.length) {
                synchronized (lock) {
                    while (currIndex % threadNum != threadIndex) {
                        //没轮到自己

                        //这个要注意，不然会死循环等待
                        if(currIndex >= arr.length){
                            break;
                        }

                        try {
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (currIndex >= arr.length) {
                        break;
                    }
                    System.out.println(arr[currIndex++]);
                    lock.notifyAll();
                }
            }
        }
    }
}
