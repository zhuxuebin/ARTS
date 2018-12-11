package com.zxb.structurealgo.binarySearch;

/**
 * @Description 给定一个int型整数，求它的开平方结果，小数部分舍去
 * Created by xuery on 2018/12/8.
 */
public class MySqrt {

    public static void main(String[] args) {
        int x = 2147395599;
        System.out.println(mySqrt(x));
    }

    public static int mySqrt(int x) {
        //二分查找法
        int begin = 0, end = x;
        int mid = begin + (end-begin)/2;
        long square = (long)mid*mid; //必须显式强转，不然mid很大时会越界
        long squarePlus = (long)(mid+1)*(mid+1);
        while(begin <= end){
            if(square > x){
                end = mid - 1;
            } else if (squarePlus <= x){ //等号一定要带上，不带上可以自己debug下刚好等于时会出现什么情况
                begin = mid + 1;
            } else{
                return mid;
            }
            //更新下一轮迭代的值
            mid = begin + (end-begin)/2;
            square = (long)mid * mid;
            squarePlus = (long)(mid+1)*(mid+1);
        }
        return mid;
    }
}
