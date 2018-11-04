package com.zxb.leetcode.dp;

/**
 * Created by xuery on 2018/11/4.
 */
public class BestTimetoBuyandSellStock121 {

    public static void main(String[] args) {
        BestTimetoBuyandSellStock121 btbss = new BestTimetoBuyandSellStock121();
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(btbss.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        //如果是1，则只能买入不能卖出、或者不交易
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }
        return max > 0 ? max : 0;
    }
}
