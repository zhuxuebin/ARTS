package com.zxb.extraalgo;

import java.util.Stack;

/**
 * 【1】
 * 描述：给定一个数字字符串，删除k个元素是其值最小
 * <p>
 * 确定如何删除？一个一个删的话，从头遍历字符串，当左边数字字符>右边数字字符时，删除左边数字字符即可，
 * 好好想想就知道，贪心法保证字符代表数字的高位较大的数字被干掉
 * 实现方法：
 * 法1：一次遍历删除一个数字，遍历k次，时间复杂度O(k*n)
 * 法2：借助栈，遍历字符串，【栈中没有数据则入栈，有则与栈顶元素比较，栈顶元素较大则删除栈顶元素】，
 * 并重复【】中的操作
 * <p>
 * 注意：这里还要考虑每删除一个元素之后，如果最高几位是0，则要先删除这几个零；要跟实际的场景符合
 *
 * @author xuery
 * @date 2018/11/22
 */
public class DeleteKNumToMinValue {

    public static void main(String[] args) {
        String s = "112";
        System.out.println(minValueMethod1(s, 1));
        System.out.println(minValueMethod2(s, 1));
    }

    /**
     * 每次删除一个数字，时间复杂度O（k*n）
     *
     * @param numStr
     * @param k
     * @return
     */
    public static String minValueMethod1(String numStr, int k) {
        if (numStr == null || numStr.length() == 0 || numStr.length() <= k) {
            return "0";
        }

        for (int i = 0; i < k; i++) {
            boolean deleteFlag = false;
            for (int j = 0; j < numStr.length() - 1; j++) {
                if (numStr.charAt(j) > numStr.charAt(j + 1)) {
                    //删除numStr[j]
                    numStr = numStr.substring(0, j) + numStr.substring(j + 1, numStr.length());
                    numStr = removeZero(numStr);
                    deleteFlag = true;
                    break;
                }
            }
            if ("0".equals(numStr)) {
                break;
            }
            if (!deleteFlag) {
                numStr = numStr.substring(0, numStr.length() - 1); //上面没有删除，则删除最后一个
            }
        }

        return numStr;
    }

    private static String removeZero(String numStr) {
        int lastZeroIndex = -1;
        for (int i = 0; i < numStr.length(); i++) {
            if (numStr.charAt(i) != '0') {
                break;
            } else {
                lastZeroIndex = i;
            }
        }
        if (lastZeroIndex != -1) {
            if (lastZeroIndex != numStr.length() - 1) {
                numStr = numStr.substring(lastZeroIndex + 1, numStr.length());
            } else {
                numStr = "0";
            }
        }
        return numStr;
    }

    /**
     * 借助栈，时间复杂度O(n), 空间复杂O(n)
     *
     * @param numStr
     * @param k
     * @return
     */
    public static String minValueMethod2(String numStr, int k) {
        if (numStr == null || numStr.length() == 0 || numStr.length() <= k) {
            return "0";
        }

        Stack<Character> cStack = new Stack<>();
        for (int i = 0; i < numStr.length(); i++) {
            if (cStack.empty()) {
                cStack.push(numStr.charAt(i));
            } else {
                char topChar = cStack.peek();
                while (topChar > numStr.charAt(i) && k > 0) {
                    cStack.pop();
                    k--;
                    //先移除掉"0001212"这种前缀0
                    while (cStack.empty() && i < numStr.length() && numStr.charAt(i) == '0') {
                        i++;
                    }
                    if (cStack.empty()) {
                        break;
                    }
                    topChar = cStack.peek();
                }
                if (i < numStr.length()) {
                    cStack.push(numStr.charAt(i));
                }
            }
        }
        while (!cStack.empty() && k-- > 0) {
            cStack.pop();
        }

        //将元素出栈即可得到结果
        StringBuilder sb1 = new StringBuilder();
        while (!cStack.empty()) {
            sb1.append(cStack.pop());
        }
        StringBuilder sb2 = new StringBuilder();
        for (int i = sb1.length() - 1; i >= 0; i--) {
            sb2.append(sb1.charAt(i));
        }
        return sb2.toString().length() == 0 ? "0" : sb2.toString();
    }
}
