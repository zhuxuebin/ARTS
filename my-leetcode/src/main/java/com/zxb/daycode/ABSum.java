package com.zxb.daycode;

/**
 * @ClassName ABSum
 * @Description TODO
 * @Author xuery
 * @Date 2019/6/26 11:28
 * @Version 1.0
 */
public class ABSum {

    public static void main(String[] args) {
        System.out.println(aplusb(1,2));
    }

    public static int aplusb(int a, int b) {
        // write your code here
        //分为两部分：不进位、进位
        while(b != 0){
            a = a^b;
            b = (a&b)<<1;
            System.out.println();
        }
        return a;
    }
}
