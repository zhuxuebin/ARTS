package com.zxb.diagram.ch09.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：
 * 背包问题
 * 给定一个背包的容量n
 * 给定m件东西并给定相应的相应的价值和重量
 * 问怎么装能使背包
 * <p>
 * 比如背包容量为n=4
 * 现在有 音箱：3000/4， 笔记本电脑：2000/3, 吉他：1500/1, iphone:2000/1
 * 显而易见应该偷：笔记本电脑+iphone
 * <p>
 * 分析：这个明显是一个DP问题，一个背包可以拆分成两个小包，每个小包里面又可以套多个小包，不断递归
 * 根据dp的思想，可以转换成填写网格的方式，横坐标为1--n的背包重量，纵坐标为m件商品，L[m][n]就是最后的结果
 * L[i][j]代表j容量的背包且只有前i件商品时可以获取的最大价值
 * L[i][j]要么取当前第i件商品，则价值为：V1=goods[i]+L[i-1][j-goods[i]],要么不取当前第i件商品, 则价值为V2=L[i-1][j],
 * 最后L[i][j]取max(V1,V2)即可，注意这样可以保证L[i][j]一定是递增的
 *
 * @author 01368080
 * @date 2018/8/9
 */
public class BagMaxValueProblem {

    public static void main(String[] args) {
        int n = 4;
        List<Goods> goodsList = new ArrayList<>(Arrays.asList(
                new Goods("音箱", 3000, 4),
                new Goods("笔记本电脑", 2000, 3),
                new Goods("吉他", 1500, 1),
                new Goods("iphone", 2000, 1),
                new Goods("黄金", 3000,2)
        ));
        System.out.println(bagMaxValueAlgo(n,goodsList));
    }

    public static int bagMaxValueAlgo(int n, List<Goods> goodsList) {
        if (goodsList == null || goodsList.size() == 0) {
            return 0;
        }
        //之所以加1是方便计算时不越界
        int[][] arr = new int[goodsList.size() + 1][n + 1];
        for (int i = 1; i <= goodsList.size(); i++) {
            for (int j = 1; j <= n; j++) {
                Goods goods = goodsList.get(i - 1);
                if (j - goods.weight < 0) {
                    //说明当前袋子容量装不下当前商品
                    arr[i][j] = arr[i - 1][j];
                } else {
                    arr[i][j] = Math.max(goods.value + arr[i - 1][j - goods.weight], arr[i - 1][j]);
                }
            }
        }
        return arr[goodsList.size()][n];
    }

    static class Goods {
        private String name;
        private int value;
        private int weight;

        public Goods(String name, int value, int weight) {
            this.name = name;
            this.value = value;
            this.weight = weight;
        }
    }
}
