package com.zxb.structurealgo.dp;

/**
 * @ClassName PascalTriangle
 * @Description 杨辉三角-求路径最短路径值
 * @Author xuery
 * @Date 2019/3/14 19:10
 * @Version 1.0
 */
public class PascalTriangle {

    public static void main(String[] args) {
        int[][] matrix = {{5}, {7, 3}, {2, 3, 4}, {4, 9, 6, 1}, {2, 7, 9, 4, 5}};

        System.out.println(pascalTriangle(matrix));
    }

    /**
     * matrix[M][N]
     * dp[M][N]  dp[i][j]=Min(dp[i-1][j],dp[i-1][j])+matrix[i][j]
     * <p>
     * matrix[i][0..i]
     *
     * @param matrix
     * @return
     */
    public static int pascalTriangle(int[][] matrix) {

        int m = matrix.length;

        int[][] dp = new int[m][];
        for (int i = 1; i <= m; i++) {
            dp[i - 1] = new int[i];
        }
        //第一行初始化
        dp[0][0] = matrix[0][0];

        for (int i = 1; i < matrix.length; i++) {
            //一行一行从上往下遍历
            int n = matrix[i].length; //或者 n=i+1;
            for (int j = 0; j < n; j++) {
                //考察dp[i-1][j],dp[i-1][j-1]
                if(j < n-1 && j-1 >= 0){
                    dp[i][j]=Math.min(dp[i-1][j],dp[i-1][j-1])+matrix[i][j];
                } else if(j < n-1){
                    dp[i][j] = dp[i-1][j]+matrix[i][j];
                } else if(j-1 >= 0){
                    dp[i][j] = dp[i-1][j-1]+matrix[i][j];
                }
            }
        }

        //遍历最后一层，取最小值
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;//用于回溯
        for(int i=0;i<matrix[m-1].length;i++){
            if(minValue > dp[m-1][i]){
                minValue = dp[m-1][i];
                minIndex = i;
            }
        }

        //回溯求出路径
        int colIndex = minIndex; //最小值时，记录当前层取的是哪个下标的值
        for(int i=m-1;i>=1;i--){
            //找出上一层等于dp[i][colIndex]-matrix[i][colIndex]的dp[][]
            int n = matrix[i-1].length;
            for(int j=0;j<n;j++) {
                if(dp[i-1][j] == dp[i][colIndex] - matrix[i][colIndex]){
                    System.out.print(matrix[i][colIndex]+" ");
                    colIndex = j;
                    break;
                }
            }
        }
        System.out.println(matrix[0][0]);

        return minValue;
    }
}
