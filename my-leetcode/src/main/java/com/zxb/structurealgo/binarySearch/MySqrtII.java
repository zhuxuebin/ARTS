package com.zxb.structurealgo.binarySearch;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Description 进阶：求一个double型数字的开平方值，精确到6位小数
 * 暂时不考虑，mid*mid越界的场景
 *
 * 精确到多少位就是一个烟雾弹，其实只是二分查找每次更新条件的粒度变小了，其他的都不变
 * Created by xuery on 2018/12/8.
 */
public class MySqrtII {

    private final static double precise = 0.0000001;

    private static int count = 0;

    private final static DecimalFormat df = new DecimalFormat("0.000000");

    public static void main(String[] args) {
        double d = 13.12545;
        System.out.println(df.format(d));

        BigDecimal b = new BigDecimal(d);
        d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(d);

        System.out.println(df.format(Math.sqrt(2.0)));
        System.out.println(mySqrt(14.0));
        System.out.println(count);
    }

    public static double mySqrt(double x) {

        //MySqrt思路一模一样，只不过精确的位数不一样了
        //二分查找法
        double begin = 0, end = x;
        double mid = begin + (end - begin) / 2;
        double square = mid * mid;
        double squarePlus = (mid + precise) * (mid + precise);
        while (end - begin >= precise) {
            count++;
            if (square > x) {
                end = mid - precise;
            } else if (squarePlus <= x) { //等号一定要带上，不带上可以自己debug下刚好等于时会出现什么情况
                begin = mid + precise;
            } else {
                return mid;
            }
            //更新下一轮迭代的值
            mid = begin + (end - begin) / 2;
            square = mid * mid;
            squarePlus = (mid + precise) * (mid + precise);
        }
        return Double.parseDouble(df.format(mid));
    }
}
