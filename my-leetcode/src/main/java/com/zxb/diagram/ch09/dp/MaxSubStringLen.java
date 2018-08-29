package com.zxb.diagram.ch09.dp;

/**
 * 描述：
 * 已知字符串s1/s2, 求两个字符串的最长公共子串长度
 * 典型的DP，可以递归，确定网格函数的意义arr[i][j]代表以s1以i下标结尾的子串，s2以j下标结尾的子串的最长公共子串长度，注意必须包含s1[i],s2[j]
 * arr[i][j]: arr[i-1][j-1]+1 s1[i]==s2[j]
 * arr[i][j]: 0 s1[i]!=s2[j] 上面说了必须包含s1[i],s2[j], 不包含的话当前最长子串一定在前面就统计了
 *
 * @author 01368080
 * @date 2018/8/9
 */
public class MaxSubStringLen {

    public static void main(String[] args) {
        String s1 = "foshabcd";
        String s2 = "fortabce";
        System.out.println(maxLengthSubString(s1, s2));
    }

    public static int maxLengthSubString(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }
        int max = 0;
        int s1MaxIndex = 0;
        // +1主要就是为了防止i-1,j-1时需要特殊判断
        int[][] arr = new int[s1.length()+1][s2.length()+1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                } else {
                    arr[i][j] = 0;
                }
                if (arr[i][j] > max) {
                    max = arr[i][j];
                    s1MaxIndex = i-1;
                }
            }
        }
        //扩展：进一步打印出最长公共子串, 这个就需要记录取得最长子串的下标了（s1,s2随意记录一个即可）
        System.out.println("maxSubString is: "+ s1.substring(s1MaxIndex+1-max,s1MaxIndex+1));

        return max;
    }
}
