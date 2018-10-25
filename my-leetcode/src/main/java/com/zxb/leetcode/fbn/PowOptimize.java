package com.zxb.leetcode.fbn;

/**
 * 描述：
 * 乘方运算优化，O（n）优化为O（log(n)）
 * 具体思路：x^n=x^(n-1)*x可以优化为x^n=x^(n/2)*x^(n/2)
 *
 * @author 01368080
 * @date 2018/10/11
 */
public class PowOptimize {
    int count = 0;

    public static void main(String[] args) {
        PowOptimize powOptimize = new PowOptimize();
        System.out.println(powOptimize.pow(2, 16));
        System.out.println(powOptimize.count);
    }

    public int pow(int x, int n) {
        count++;
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

        if ((n & 1) == 1) {
            //这里必须中转一下，否则：pow(x, (n - 1) / 2)*pow(x, (n - 1) / 2)会重复递归很多次
            int r = pow(x, (n - 1) / 2);
            return r * r * x;
        } else {
            int r = pow(x, n / 2);
            return r*r;
        }
    }
}
