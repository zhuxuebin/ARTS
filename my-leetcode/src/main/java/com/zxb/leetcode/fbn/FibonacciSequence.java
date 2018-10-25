package com.zxb.leetcode.fbn;

/**
 * 描述：
 *
 * @author 01368080
 * @date 2018/10/11
 */
public class FibonacciSequence {

    private static long count = 0L;

    //递归，测试发现，当n稍大的时候(大于45)机器就会卡死，计算不出来，实践证明这里采用递归实现一点都不适用，递归栈太深了且各种重复计算
    public static void main(String[] args) {
        FibonacciSequence fs = new FibonacciSequence();
        System.out.println(fs.fbn(45));
        System.out.println(count);
    }

    public int fbn(int n){
        count++;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return fbn(n-2) + fbn(n-1);
    }
}
