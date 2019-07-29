package com.zxb.daycode;

/**
 * @ClassName ReverseSignedInteger
 * @Description 快手面试题
 * <p>
 * 翻转有符号整型数字, 不借助字符串和数组
 * 123-->321, -123-->-321, 120-->21
 * <p>
 * 我的思路:简单，先确定是正数还是负数，负数则先考虑正数部分
 * 1. 确定int数字的除符号位的总位数n
 * 2. 然后从个位开始，个位*10^n-1 + 十位*10^n-2 + 最高位*10^0
 * @Author xuery
 * @Date 2019/7/29 20:18
 * @Version 1.0
 */
public class ReverseSignedInteger {

    public static void main(String[] args) {
        System.out.println(reverseSignedInteger(123));
        System.out.println(reverseSignedInteger(-123));
        System.out.println(reverseSignedInteger(120));
        System.out.println(reverseSignedInteger(-120));
    }

    public static int reverseSignedInteger(int intVal) {
        boolean isNegative = false;
        if (intVal == 0) {
            return intVal;
        } else if (intVal < 0) {
            isNegative = true;
            intVal = Math.abs(intVal);
        }

        //1. 确定总共有多少位
        int tempVal = intVal;
        int digitNum = 0;
        while (tempVal != 0) {
            digitNum++;
            tempVal = tempVal / 10;
        }

        //2. 从个位开始遍历即可
        int result = 0;
        tempVal = intVal;
        while (tempVal != 0) {
            int currDigit = tempVal % 10;
            tempVal = tempVal / 10;
            result += currDigit * Math.pow(10, digitNum - 1);
            digitNum--;
        }

        return isNegative ? -result : result;
    }
}
