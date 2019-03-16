package com.zxb.structurealgo.dp;

/**
 * @ClassName LongestCommonSubstringLength
 * @Description 最长公共子序列，就是两个字符串的按顺序的最长公共子序列，不要求连续（仅可以采用删除、增加操作）
 * <p>
 * 求相似度
 * @Author xuery
 * @Date 2019/3/15 18:20
 * @Version 1.0
 */
public class LongestCommonSubstringLength {

    public static void main(String[] args) {
        String s1 = "abcdgf";
        String s2 = "sdacdbcdsdf"; //abcdf

        LongestCommonSubstringLength lcsl = new LongestCommonSubstringLength();
        lcsl.LCSByBacktracking(0,0,s1,s2,0);
        System.out.println(lcsl.maxLen);

        System.out.println(lcsl.LCSByDp(s1,s2));
    }

    //还是回溯法先搞下
    int maxLen = Integer.MIN_VALUE;
    public void LCSByBacktracking(int i, int j, String s1, String s2, int currLen) {
        int n1 = s1.length(), n2 = s2.length();
        if (i == n1 || j == n2) {
            maxLen = Math.max(maxLen, currLen);
            return;
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            LCSByBacktracking(i + 1, j + 1, s1, s2, currLen + 1);
        } else {
            LCSByBacktracking(i + 1, j, s1, s2, currLen);
            LCSByBacktracking(i, j + 1, s1, s2, currLen);
        }
    }

    /*
    *dp法-相对于回溯法，就是利用备忘录，空间换时间
    * 用dp的还有一个好处就是可以根据dp[][]回溯出具体是哪个最长公共子序列
     */
    public int LCSByDp(String s1, String s2){

        int n1 = s1.length(), n2 = s2.length();
        //dp[i][j] = dp[i-1][j-1], s1[i] == s2[j]; dp[i][j]=Max(dp[i-1][j]+1,dp[i][j-1]+1) s1[i] != s2[j]
        int[][] dp = new int[n1][n2];
        //初始化下dp[0...n1-1][0],dp[0][0...n2-1]
        for(int i=0;i<n1;i++){
            if(s1.charAt(i) == s2.charAt(0)){
                dp[i][0] = 1;
            } else {
                dp[i][0] = i == 0 ? 0 : dp[i-1][0];
            }
        }
        for(int i=0;i<n2;i++){
            if(s2.charAt(i) == s1.charAt(0)){
                dp[0][i] = 1;
            } else {
                dp[0][i] = i == 0 ? 0 : dp[0][i-1];
            }
        }

        for(int i=1;i<n1;i++){
            for(int j=1;j<n2;j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }

        return dp[n1-1][n2-1];
    }
}
