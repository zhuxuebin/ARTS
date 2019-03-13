package com.zxb.structurealgo.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SimpleBagProblem
 * @Description 简单0-1背包问题，给定n个物品及其重量，再给定一个背包可装的最大重量为w，问物品不可分割，则背包能装物品的最大重量为多少？
 *
 * 比如：goods[5]={2,2,3,4,5},n=5     w=10   则可装的最大重量为2+3+5=10
 *
 * 回溯法自然是可以解决的，全遍历，考察当前物品装or不装
 *
 * 动态规划法：【当前物品能否根据上一物品的装包后的情况，选择相应的装包策略】
 * 当然可以，根据上一物品装or不装之后得到的背包重量，得到当前物品装or不装之后的背包重量
 * 通过构造boolean[][] dp[n][w+1]数组, 为true说明当前列下标重量是可达成的，当前行的列是否可达成可由上一行的列是否可达成+当前物品重量推导出来
 *
 * 回溯法时间复杂度O(2^n), dp时间复杂度O(w*n)
 * @Author xuery
 * @Date 2019/3/13 11:28
 * @Version 1.0
 */
public class SimpleBagProblem {

    public static void main(String[] args) {
        int[] goods = {2,2,2,2,5};
        int w = 10;

        System.out.println(simpleBagProblemByDp(goods,w));

        System.out.println(simpleBagProblemByDpOptimize(goods,w));

    }

    public static int simpleBagProblemByDp(int[] goods, int w){
        if(goods == null || goods.length == 0 || w <= 0){
            return 0;
        }

        int n = goods.length;
        boolean[][] dp = new boolean[n][w+1];
        //第0个物品初始化下
        dp[0][0] = true;
        if(goods[0] <= w) {
            dp[0][goods[0]] = true;
        }
        for(int i=1;i<n;i++){

            //1、不取当前goods[i]时的dp[i][j]=dp[i-1][j];
            for(int j=0;j<=w;j++){
                if(dp[i-1][j]) {
                    dp[i][j] = dp[i - 1][j];
                }
            }

            //2、考察取当前goods[i]时的dp[i][j], 它是由之前的dp[i-1][...]得来的，只有dp[i-1][...]为true时，才能递推出dp[i][j]
            for(int j=0;j<=w-goods[i];j++){
                if(dp[i-1][j] && j+goods[i] <= w){
                    dp[i][j+goods[i]] = true;
                }
            }
        }

        //考察dp[n-1][0...w]中为true且列下标为最大值，就是结果
        for(int i=w;i>=0;i--){
            if(dp[n-1][i]){
                return i;
            }
        }

        return 0;
    }

    //二维数组优化为一维数组
    public static int simpleBagProblemByDpOptimize(int[] goods, int w){
        if(goods == null || goods.length == 0 || w <= 0){
            return 0;
        }

        int n= goods.length;
        boolean[] dp = new boolean[w+1];
        //初始化
        dp[0] = true;
        if(goods[0] <= w){
            dp[goods[0]] = true;
        }
        for(int i=1;i<n;i++){

            /**这里采用一维数组，所以不取goods[i]的情况就不用考虑了，因为在dp中已经有了，之前是因为二维数组的关系
            * todo 从小到大写会有问题，这样会导致j值小的dp[j+goods[i]]=true之后，到下一个j'=j+goods[i]时又会重新计算一遍，
             * 结果就是dp[j+2*goods[i]]=true,显然不对
             * */
//            for(int j=0;j<=w-goods[i];j++){
//                if(dp[j] && j+goods[i] <= w){
//                    dp[j+goods[i]] = true;
//                }
//            }

            //这样才是对的，j的方向要与dp的方向相反
            for(int j=w-goods[i];j>=0;j--){
                if(dp[j] && j+goods[i] <= w){
                    dp[j+goods[i]] = true;
                }
            }
        }

        for(int i=w;i>=0;i--){
            if(dp[i]){
                return i;
            }
        }

        return 0;
    }
}
