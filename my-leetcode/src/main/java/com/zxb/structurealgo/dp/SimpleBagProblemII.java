package com.zxb.structurealgo.dp;

/**
 * @ClassName SimpleBagProblemII
 * @Description 升级版背包问题，共有n件物品的重量，每件物品有一个价格；给定一个背包最多装w重的物品，问怎么装装的价值最大
 * <p>
 * 比如goods={2,2,3,4,5} prices={2,3,4,5,6} w=10
 * <p>
 * 选第1件，第2件，第4件， 重量：2+3+5=10,价值：3+4+6=13
 * <p>
 * 动态规划解决
 * <p>
 * 仿造简单版构造一个二维数组dp[n][w+1],值是当前的背包所装物品的价值而不是boolean型
 * @Author xuery
 * @Date 2019/3/13 20:45
 * @Version 1.0
 */
public class SimpleBagProblemII {

    public static void main(String[] args) {
        int[] goods = new int[]{2,2,4,6,3};
        int[] prices = new int[]{3,2,8,9,6};
        int w =9;
        System.out.println(simpleBagProblemII(goods,prices,w));
        System.out.println(simpleBagProblemIIOptimize(goods, prices,w));
        System.out.println(simpleBagProblemIIUpgrade(goods,prices,w));
    }

    public static int simpleBagProblemII(int[] goods, int[] prices, int w) {

        int n = goods.length;

        int[][] dp = new int[n][w + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = -1;
            }
        }
        //初始化第一个物品装or不装
        dp[0][0] = 0;
        if (goods[0] <= w) {
            dp[0][goods[0]] = prices[0];
        }
        for (int i = 1; i < n; i++) {

            //不取goods[i]
            for (int j = 0; j <= w; j++) {
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = dp[i - 1][j];
                }
            }

            //取goods[i]
            for (int j = 0; j <= w - goods[i]; j++) {
                if (dp[i - 1][j] != -1 && j + goods[i] <= w) {
                    dp[i][j + goods[i]] = Math.max(dp[i][j + goods[i]],dp[i - 1][j] + prices[i]); //todo 这里可能存在覆盖，要取大值
                }
            }
        }

        //遍历最后一行,取值最大的，不一定是最后一个
        int maxPrice = Integer.MIN_VALUE;
        for (int i = w; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, dp[n - 1][i]);
        }

        return maxPrice == -1 ? 0 : maxPrice;
    }

    //优化：二维降为一维数组
    public static int simpleBagProblemIIOptimize(int[] goods, int[] prices, int w) {

        int n = goods.length;

        int[] dp = new int[w + 1];
        for (int i = 0; i <= w; i++) {
            dp[i] = -1;
        }
        //初始化第一个物品装or不装
        dp[0] = 0;
        if (goods[0] <= w) {
            dp[goods[0]] = prices[0];
        }
        for (int i = 1; i < n; i++) {

            //只需要考虑goods[i]，也要注意从后面往前面算
            for (int j = w-goods[i]; j >= 0; j--) {
                if (dp[j] != -1 && j + goods[i] <= w) {
                    dp[j + goods[i]] = Math.max(dp[j+goods[i]],dp[j] + prices[i]);
                }
            }
        }

        //取值最大的，不一定是最后一个
        int maxPrice = Integer.MIN_VALUE;
        for (int i = w; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, dp[i]);
        }

        return maxPrice == -1 ? 0 : maxPrice;
    }

    //继续升级，要求输出价值最大时，装了哪些物品，回溯法
    public static int simpleBagProblemIIUpgrade(int[] goods, int[] prices, int w) {

        int n = goods.length;

        int[][] dp = new int[n][w + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = -1;
            }
        }
        //初始化第一个物品装or不装
        dp[0][0] = 0;
        if (goods[0] <= w) {
            dp[0][goods[0]] = prices[0];
        }
        for (int i = 1; i < n; i++) {

            //不取goods[i]
            for (int j = 0; j <= w; j++) {
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = dp[i - 1][j];
                }
            }

            //取goods[i]
            for (int j = 0; j <= w - goods[i]; j++) {
                if (dp[i - 1][j] != -1 && j + goods[i] <= w) {
                    dp[i][j + goods[i]] = Math.max(dp[i][j + goods[i]],dp[i - 1][j] + prices[i]);//todo 这里可能存在覆盖，要取大值
                }
            }
        }

        //遍历最后一行,取值最大的，不一定是最后一个
        int maxPrice = Integer.MIN_VALUE;
        int currW = -1;
        for (int i = w; i >= 0; i--) {
            if(maxPrice < dp[n-1][i]){
                maxPrice = dp[n-1][i];
                currW = i;
            }
        }

        //取的dp[n-1][lastW]
        int currPrice = maxPrice;
        for(int i=n-1;i>=1 && currW > 0;i--){
            //每次看是否装了当前件，取没取看dp[i-1][currW-goods[i]] ?== maxPrice-prices[i]，是否能往回推
            if(currW-goods[i] >=0 && dp[i-1][currW-goods[i]] == currPrice-prices[i]){
                //说明取了当前件
                System.out.println(i+":"+goods[i]);
                currW -= goods[i];
                currPrice -= prices[i];
            } //else 没有取当前件，继续下一个
        }
        if(currPrice != 0){
            //说明第一个装了
            System.out.println(0+":"+goods[0]);
        }

        return maxPrice == -1 ? 0 : maxPrice;
    }
}
