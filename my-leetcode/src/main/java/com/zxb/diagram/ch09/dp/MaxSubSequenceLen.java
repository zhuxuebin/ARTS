package com.zxb.diagram.ch09.dp;

/**
 * 描述：
 * 最长公共子序列：与最长公共子串的最大区别就是字符串可以不连续
 * 如fosh与fish的最长公共子串为sh, 最长公共子序列为fsh
 * <p>
 * 这个也很简单
 * arr[i][j] = arr[i-1][j-1] + 1   s1[i] == s2[j]
 * arr[i][j] = Max(arr[i-1][j],arr[i][j-1]) s1[i] != s2[j]
 *
 * @author 01368080
 * @date 2018/8/9
 */
public class MaxSubSequenceLen {

    public static void main(String[] args) {
        String s1 = "foshshsdse";
        String s2 = "fishdes";
//        String s2 = "fort";
        System.out.println(maxSubSequenceLen(s1, s2));
    }

    public static int maxSubSequenceLen(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }
        //+1一样的道理，防止i-1,j-1越界做特殊判断
        //左上方:0、左边:1、上面:-1
        int[][] arr = new int[s1.length() + 1][s2.length() + 1];
        int[][] flagArr = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                    flagArr[i][j] = 0;
                } else {
                    if (arr[i - 1][j] > arr[i][j - 1]) {
                        flagArr[i][j] = -1;
                    } else {
                        flagArr[i][j] = 1;
                    }
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }

        //正确解法：回溯法--每次都记录当前arr[i][j]是由哪个方向上的数据得来的，左上方:0、左边:1、上面:-1
        int i = s1.length(), j = s2.length();
        StringBuilder sb = new StringBuilder();
        while (i >= 1 && j >= 1) {
            if (flagArr[i][j] == 0) {
                //说明s1[i] == s2[j]
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (flagArr[i][j] == -1) {
                i--;
            } else {
                j--;
            }
        }
        String resStr = sb.toString();
        for (int k = resStr.length() - 1; k >= 0; k--) {
            System.out.print(resStr.charAt(k));
        }
        System.out.println();

        /**
         * 扩展：请打印出最长公共子序列; 这个需要的时间是O(m+n),往回不断对比两个字符串，直至找到最长长度的子序列即可,双指针(指针交替向前移动)
         * 错误事例：交替移动都没用，会错过，有可能一个不动，另外一个移两步就匹配上了
         * */
        /*int maxLen = arr[s1.length()][s2.length()];
        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        while(i < s1.length() && j < s2.length()){
            if(s1.charAt(i) == s2.charAt(j)){
                sb.append(s1.charAt(i));
                i++;
                j++;
            } else {
                if(flag){
                    i++;
                } else {
                    j++;
                }
                flag = !flag;
            }
        }
        System.out.println("longest subsequence is :"+sb.toString());*/

        //正确解法：回溯法--每次都记录当前arr[i][j]是由哪个方向上的数据得来的，左上方:0、左边:1、上面:-1


        return arr[s1.length()][s2.length()];
    }
}
