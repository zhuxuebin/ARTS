package com.zxb.structurealgo.dp;

/**
 * @ClassName LevenshteinDistance
 * @Description 求两个字符串的最短编辑距离，只一个字符串要变成另外一个字符串所需的最小编辑次数（编辑操作包括删除，新增、替换）
 * 求差异度
 * @Author xuery
 * @Date 2019/3/15 18:18
 * @Version 1.0
 */
public class LevenshteinDistance {

    public static void main(String[] args) {
        String s1 = "mitcmu";
        String s2 = "mtacnu";
        LevenshteinDistance ld = new LevenshteinDistance();
        ld.minDistBacktracking(0, 0, s1, s2, 0);
        System.out.println(ld.minDist);

        System.out.println(ld.minDistByDp(s1,s2));
    }

    /**
     * 回溯法先搞一波
     * minDistBacktracking(0,0,s1,s2,0);
     *
     * @param i
     * @param j
     * @param s1
     * @param s2
     * @param currDist 当前编辑距离
     */
    int minDist = Integer.MAX_VALUE;

    public void minDistBacktracking(int i, int j, String s1, String s2, int currDist) {

        int n1 = s1.length(), n2 = s2.length();
        if (i == n1 - 1) {
            currDist += (n2 - 1 - j);
            minDist = Math.min(minDist, currDist);
            return;
        } else if (j == n2 - 1) {
            currDist += (n1 - 1 - i);
            minDist = Math.min(minDist, currDist);
            return;
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            minDistBacktracking(i + 1, j + 1, s1, s2, currDist);
        } else {
            //s1.charAt(i) != s2.charAt(j)
            //(1) 删除s1[i] 或者在s2[j]前面新增s1[i]
            minDistBacktracking(i + 1, j, s1, s2, currDist + 1);
            //(2) 删除s2[j] 或者在s1[i]前面新增s2[j]
            minDistBacktracking(i, j + 1, s1, s2, currDist + 1);
            //(3) 替换s1[i]为s2[j] 或者替换s2[j]为s1[i]
            minDistBacktracking(i + 1, j + 1, s1, s2, currDist + 1);
        }
    }


    public int minDistByDp(String s1, String s2) {

        //根据之前的回溯法和画递归树基本可以写出结果
        int n1 = s1.length(), n2 = s2.length();
        int[][] dp = new int[n1][n2];
        //先特殊处理下s1[0...n1-1]与s2[0],s2[0...n2-1]与s1[0]
        for(int i=0;i<n1;i++){
            //看s1[0...i]中是否有与s2[0]相等的
            if(s1.charAt(i) == s2.charAt(0)){
                dp[i][0] = i;
            } else {
                if(i == 0){
                    dp[i][0] = 1;
                } else {
                    dp[i][0] = dp[i - 1][0] + 1;
                }
            }
        }

        for(int i=0;i<n2;i++){
            //看s2[0...i]中是否有与s1[0]相等的
            if(s2.charAt(i) == s1.charAt(0)){
                dp[0][i] = i;
            } else {
                if(i == 0){
                    dp[0][i] = 1;
                } else {
                    dp[0][i] = dp[0][i-1] + 1;
                }
            }
        }

        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1]; //todo 这个到底要不要写成dp[i][j]=Min(dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1])
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }
            }
        }

        return dp[n1-1][n2-1];
    }

}
