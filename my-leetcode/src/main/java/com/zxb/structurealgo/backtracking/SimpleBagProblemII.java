package com.zxb.structurealgo.backtracking;

/**
 * @ClassName SimpleBagProblemII
 * @Description 升级版背包问题，共有n件物品的重量，每件物品有一个价格；给定一个背包最多装w重的物品，问怎么装装的价值最大
 *
 * 比如goods={2,2,3,4,5} prices={2,3,4,5,6} w=10
 *
 * 选第1件，第2件，第4件， 重量：2+3+5=10,价值：3+4+6=13
 *
 * 回溯法依然可以解决
 * @Author xuery
 * @Date 2019/3/5 11:37
 * @Version 1.0
 */
public class SimpleBagProblemII {

    public static void main(String[] args){
        int w = 10;
        int[] goods = new int[]{2,2,3,4,5};
        int[] prices = new int[]{2,3,4,5,6};
        SimpleBagProblemII simpleBagProblem = new SimpleBagProblemII();
        simpleBagProblem.zeroOneBag(0, 0,0, w, goods,prices);
        System.out.println(simpleBagProblem.maxP);
    }

    /**
     * 求解思路：回溯法，还是全部试一遍，看符合条件的最大重量
     * @param cw 背包中现在的重量cw
     * @param cp 当前背包已装物品总价值
     * @param index 第index个物品，装or不装
     * @param w 背包所能装的最大重量
     * @param goods n个物品的重量值
     * @param prices n个物品的价值
     */
    int maxP = Integer.MIN_VALUE;
    public void zeroOneBag(int cw,int cp, int index, int w, int[] goods, int[] prices){

        if(index >= goods.length || cw == w){
            //遍历完或者已经达到包所容纳的最大重量则结束
            maxP = Math.max(maxP, cp);
            return;
        }

        zeroOneBag(cw, cp,index+1, w, goods,prices); //当前goods[index]不放入背包
        if(cw + goods[index] <= w){
            //剪枝法
            zeroOneBag(cw+goods[index], cp+prices[index], index+1, w, goods,prices);
        }

    }
}
