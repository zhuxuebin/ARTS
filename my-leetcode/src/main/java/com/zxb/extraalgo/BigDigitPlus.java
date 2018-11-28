package com.zxb.extraalgo;

import java.math.BigInteger;

/**
 * 描述：大数相加
 * <p>
 * 优化点：参考BigInteger源码
 * Created by xuery on 2018/11/25.
 */
public class BigDigitPlus {

    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("12341243124341243412", 10);
        System.out.println(optimizeAddStrings("6913259244", "71103343"));
    }

    /**
     * 注意点1：虽然不包括前缀0，但是"0"是要考虑的
     * <p>
     * bug1: num1.charAt是Character类型，需要转换为数字:num1.charAt(i)-'0'
     * bug2: new String(resultArr,0,len), resultArr为int[],不能这么转，需要借助StringBuilder
     */
    public static String addStrings(String num1, String num2) {
        int len = Math.max(num1.length(), num2.length()) + 1;
        int carry = 0; //进位

        int[] resultArr = new int[len];

        int i = num1.length() - 1, j = num2.length() - 1, index = len - 1;
        while (i >= 0 || j >= 0) {

            if (i < 0) {
                resultArr[index--] = (num2.charAt(j) + carry - '0') % 10;
                carry = (num2.charAt(j--) + carry - '0') / 10;
            } else if (j < 0) {
                resultArr[index--] = (num1.charAt(i) + carry - '0') % 10;
                carry = (num1.charAt(i--) + carry - '0') / 10;
            } else {
                resultArr[index--] = (num1.charAt(i) - '0' + num2.charAt(j) - '0' + carry) % 10;
                carry = (num1.charAt(i--) - '0' + num2.charAt(j--) - '0' + carry) / 10;
            }
        }
        resultArr[0] = carry;

        StringBuilder sb = new StringBuilder(len);
        for (int k = 0; k < len; k++) {
            if (k == 0 && resultArr[0] == 0) {
                continue;
            } else {
                sb.append(resultArr[k]);
            }
        }
        return sb.toString();
    }

    /**
     * 优化方案：适用于num1,num2的位数特别多的时候
     * <p>
     * 之前的方案是每次只运算一位，其实可以每次运算多位，resultArr数组位int型，int表示的范围为-2^31--2^31-1(-2147483648--2147483647)
     * 可以表示10位之多，所以可以采用位表示9位运算（10位可能越界）
     * 这样每次运算9位，减少空间消耗，提升计算效率，实际底层也是一位一位运算的，这种提升效率是对于运用层来说的
     */
    public static String optimizeAddStrings(String num1, String num2) {

        int len = (Math.max(num1.length(), num2.length()) + 1) / 9 + 1;

        int[] resultArr = new int[len];

        int carry = 0;

        //9个为一组
        int i = num1.length() - 1, j = num2.length() - 1, index = len - 1;
        while (i >= 0 || j >= 0) {

            if (i < 0) {
                String sN2 = num2.substring(j - 8 > 0 ? j - 8 : 0, j + 1);
                resultArr[index--] = (Integer.parseInt(sN2) + carry) % 1000000000;
                carry = (Integer.parseInt(sN2) + carry) / 1000000000;
                j -= 9;
            } else if (j < 0) {
                String sN1 = num1.substring(i - 8 > 0 ? i - 8 : 0, i + 1);
                resultArr[index--] = (Integer.parseInt(sN1) + carry) % 1000000000;
                carry = (Integer.parseInt(sN1) + carry) / 1000000000;
                i -= 9;
            } else {
                //取9位，不够则有几位取几位
                String sN1 = num1.substring(i - 8 > 0 ? i - 8 : 0, i + 1);
                String sN2 = num2.substring(j - 8 > 0 ? j - 8 : 0, j + 1);
                resultArr[index--] = (Integer.parseInt(sN1) + Integer.parseInt(sN2) + carry) % 1000000000;
                carry = (Integer.parseInt(sN1) + Integer.parseInt(sN2) + carry) / 1000000000;
                i -= 9;
                j -= 9;
            }
        }
        if (index >= 0) {
            resultArr[index--] = carry;
        }

        StringBuilder sb = new StringBuilder(len);
        boolean zeroLeadFlag = true;
        boolean firstNoneZeroFlag = true;
        for (int k = 0; k < len; k++) {
            if (resultArr[k] != 0) {
                zeroLeadFlag = false;
            }
            if (!zeroLeadFlag) {
                //记得补齐9位
                if (firstNoneZeroFlag) {
                    sb.append(resultArr[k]);
                    firstNoneZeroFlag = false;
                } else {
                    String padZeroStr = padZero(String.valueOf(resultArr[k]));
                    sb.append(padZeroStr);
                }
            }
        }
        return zeroLeadFlag ? "0" : sb.toString();
    }

    private static String padZero(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < 9; i++) {
            sb.append('0');
        }
        sb.append(s);
        return sb.toString();
    }
}
