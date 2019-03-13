package com.zxb.structurealgo.backtracking;

/**
 * @ClassName SimpleBagProblem
 * @Description 基础0-1背包问题，给定一个背包，最多可以装W重量的物品，有n个重量不等的物品且物品都不可分割，
 * 请问背包可以装物品的最大重量是多少？
 * @Author xuery
 * @Date 2019/3/5 11:37
 * @Version 1.0
 */
public class SimpleBagProblem {

    public static void main(String[] args){
        int w = 100;
        int[] goods = new int[]{10,15,30,30,50};
        SimpleBagProblem simpleBagProblem = new SimpleBagProblem();
        simpleBagProblem.zeroOneBag(0, 0, w, goods);
        System.out.println(simpleBagProblem.maxW);
    }

    /**
     * 求解思路：回溯法，还是全部试一遍，看符合条件的最大重量
     * @param cw 背包中现在的重量cw
     * @param index 第index个物品，装or不装
     * @param w 背包所能装的最大重量
     * @param goods n个物品的重量值
     */
    int maxW = Integer.MIN_VALUE;
    public void zeroOneBag(int cw, int index, int w, int[] goods){

        if(index >= goods.length || cw == w){
            //遍历完或者已经达到包所容纳的最大重量则结束
            maxW = Math.max(maxW, cw);
            return;
        }

        zeroOneBag(cw, index+1, w, goods); //当前goods[index]不放入背包
        if(cw + goods[index] <= w){
            //剪枝法
            zeroOneBag(cw+goods[index], index+1, w, goods);
        }

    }
}
