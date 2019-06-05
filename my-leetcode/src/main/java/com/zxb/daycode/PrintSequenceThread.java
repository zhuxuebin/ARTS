package com.zxb.daycode;

/**
 * @ClassName PrintSequenceThread
 * @Description TODO
 * @Author xuery
 * @Date 2019/6/5 21:27
 * @Version 1.0
 */
public class PrintSequenceThread implements Runnable {

    private static final Object LOCK = new Object();

    /**
     * 当前即将打印的数字
     */
    private static int current = 0;

    /**
     * 当前线程编号，从0开始
     */
    private int threadNo;

    /**
     * 线程数量
     */
    private int threadCount;

    /**
     * 打印的最大数值
     */
    private int max;

    public PrintSequenceThread(int threadNo, int threadCount, int max) {
        this.threadNo = threadNo;
        this.threadCount = threadCount;
        this.max = max;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (LOCK) {
                // 判断是否轮到当前线程执行
                while (current % threadCount != threadNo) {
                    if (current > max) {
                        break;
                    }
                    try {
                        // 如果不是，则当前线程进入wait
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 最大值跳出循环
                if (current > max) {
                    break;
                }
                System.out.println("thread-" + threadNo + " : " + current);
                current++;
                // 唤醒其他wait线程
                LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        int threadCount = 3;
        int max = 10;
        for (int i = 0; i < threadCount; i++) {
            new Thread(new PrintSequenceThread(i, threadCount, max)).start();
        }
    }

}
