package com.zxb.structurealgo.dp;

/**
 * @ClassName MoneyChange
 * @Description 给定一个数组，里面存储的是找零的零钱，再给定一个钱数金额，问怎么找钱所使用的零钱张数最少
 *
 * 比如：{1,3,5},9
 * 则9=3*3=1+3+5, 最少使用3张
 * @Author xuery
 * @Date 2019/3/15 11:48
 * @Version 1.0
 */
public class MoneyChange {

    public static void main(String[] args) {
        MoneyChange moneyChange = new MoneyChange();
        int[] coins = new int[]{1,3,5};
        int money = 9;
        moneyChange.moneyChangeByBacktracking(0,0,coins,money);
        System.out.println(moneyChange.minCnt);


        System.out.println(moneyChange(coins,money));
    }

    /**
     * 先通过回溯法可以求解，回溯法很简单：就是当前取coins中的任意一个，
     * 然后递归树画出来，发现只有两个参数
     * 说明是一维数组dp[i],i是当前钱数，值是钱的张数
     *
     * 起始位置直接从0开始
     * @param coins
     * @return
     */
    public static int moneyChange(int[] coins, int money){

        int[] dp = new int[money+1];
        for(int i=0;i<=money;i++){
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        //最后dp[money]就是所求的结果
        for(int i=0;i<=money;i++){
            //求dp[i]
            for(int j=0;j<coins.length;j++){
                if(i-coins[j] >=0 && dp[i-coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i],dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[money];
    }

    /**
     *
     * @param cm 当前钱数
     * @param cnt 当前所用的钱张数
     * @param coins
     * @param money
     * @return
     */
    int minCnt = Integer.MAX_VALUE;
    public void moneyChangeByBacktracking(int cm, int cnt, int[] coins, int money){

        if(cm == money){
            minCnt = Math.min(minCnt, cnt);
        }

        for(int i=0;i<coins.length;i++){
            if(cm + coins[i] <= money) {
                moneyChangeByBacktracking(cm + coins[i], cnt + 1, coins, money);
            }
        }
    }
}
