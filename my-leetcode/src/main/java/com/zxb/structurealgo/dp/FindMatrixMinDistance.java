package com.zxb.structurealgo.dp;

/**
 * @ClassName FindMatrixMinDistance
 * @Description 给定一个数字矩阵，从左上角走到右下角，每次只能往左或者往右走，问经过的数字和最小的路径长是多少
 * @Author xuery
 * @Date 2019/3/15 11:48
 * @Version 1.0
 */
public class FindMatrixMinDistance {

    public static void main(String[] args) {
        int[][] matrix = {{0,3,5,9},{2,1,3,4},{5,2,6,7},{6,8,4,3}};
        System.out.println(findMatrixMinDistance(matrix));
    }

    public static int findMatrixMinDistance(int[][] matrix){

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }

        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m];
        //dp[i][j] = Min(dp[i-1][j],dp[i][j-1])+matrix[i][j]
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i-1 >=0 && j-1>=0) {
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+matrix[i][j];
                } else if(i-1 >=0){
                    dp[i][j] = dp[i-1][j]+matrix[i][j];
                } else if(j-1>=0){
                    dp[i][j] = dp[i][j-1]+matrix[i][j];
                } else {
                    dp[i][j] = matrix[i][j];
                }
            }
        }

        //如果要求路径也很简单，直接通过回溯即可，比如当前dp[i][j]则看dp[i][j]-matrix[i][j]等于dp[i-1][j]、dp[i][j-1]中的哪个，决定回溯方向

        return dp[n-1][m-1];
    }

}
