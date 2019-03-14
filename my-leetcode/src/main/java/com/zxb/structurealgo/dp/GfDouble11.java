package com.zxb.structurealgo.dp;

/**
 * @ClassName GfDouble11
 * @Description 女朋友双十一购物，满200减，问购买哪些物品以最大程度的褥羊毛，就是最好刚刚超过200多一点最划算
 * <p>
 * 类似背包问题，只不过背包重量换成了多少钱
 * <p>
 * 这里可以先设定一个最多可能花钱的数，比如200*3+1=601，然后同理计算boolean dp[n][3*w+1]，取最后一行，大于等于200的第一个为true值就是最划算的钱数
 * 然后采用回溯法得出买了哪些物品
 * @Author xuery
 * @Date 2019/3/14 15:36
 * @Version 1.0
 */
public class GfDouble11 {

    public static void main(String[] args) {
        int w = 200;
        int[] goods = new int[]{15, 40, 100, 90, 50, 80};
        System.out.println(gfDouble11(goods, w));
    }


    /**
     * @param goods 女朋友商品列表
     * @param w     满多少减
     * @return
     */
    public static int gfDouble11(int[] goods, int w) {

        int n = goods.length;
        boolean[][] dp = new boolean[n][3 * w + 1];

        //初始化
        dp[0][0] = true;
        if (goods[0] <= 3 * w) {
            dp[0][goods[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 3 * w; j++) {
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                }
            }

            for (int j = 0; j <= 3 * w - goods[i]; j++) {
                if (dp[i - 1][j] && j + goods[i] <= 3 * w) {
                    dp[i][j + goods[i]] = true; //这里覆盖是没有关系的，true覆盖true，如果是int型就要注意了
                }
            }
        }

        int minW = -1;
        for (int i = w; i <= 3 * w; i++) {
            if (dp[n - 1][i]) {
                minW = i;
                break;
            }
        }

        //回溯出买了哪些物品
        int currW = minW;
        if (currW != -1) {
            for (int i = n - 1; i >= 1; i--) {
                if (currW - goods[i] >= 0 && dp[i - 1][currW - goods[i]]) {
                    //说明当前物品是买了的
                    System.out.println(i + ":" + goods[i]);
                    currW -= goods[i];
                } //else 没买则不用管
            }
        }
        if (currW != 0) {
            //说明第一个商品要取
            System.out.println(0 + ":" + goods[0]);
        }


        return minW; //-1说明没有符合条件的
    }
}
