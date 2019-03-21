package com.zxb.structurealgo.dijkstra;

import java.util.*;

/**
 * @ClassName Dijkstra
 * @Description 最短单源路径算法
 *
 * 算法实现核心
 *  1. 每次从未遍历的节点中找出距离起点最短距离的节点
 *  2. 用(该点距离起点的长度+它到后继节点的长度值)X与后继节点的当前最短值Y比较，
 *     X < Y则更新后继节点的当前最短值
 *  3. 引入一个前继节点数组preNode，用于记录当前节点到起点的最短距离是由之前哪一个
 *     节点遍历而来的，如果X < Y则更新后继节点的前继节点下标
 *  4. 引入visited数组，记录节点是否已经访问过，一个节点只能访问一次
 *  5. 最后根据preNode,回溯法求出最短路径
 * @Author xuery
 * @Date 2019/3/21 16:21
 * @Version 1.0
 */
public class Dijkstra {

    public static void main(String[] args) {

    }

    public List<Integer> dijkstraAlgo(DirectedWeightGraph graph, int start, int end){

        //优先级队列，用于每次取距离最短的哪个节点,这里java自带的优先级队列没有提供update接口，只能自己实现一个了
        int v = graph.v;
        PriorityQueue queue = new PriorityQueue(v);

        //preNode数组，这里节点取值是0-v-1, 所以直接用数组就可以了；如果不是的话就要用map了
        int[] preNodeArr = new int[v];
        boolean[] visited = new boolean[v];

        //start作为第一个点,然后根据当前节点广度优先遍历其后继节点
        Vertx vertx0 = new Vertx(start, 0);
        queue.add(vertx0);
        while(!queue.isEmpty()){
            Vertx currMinDisVertx = queue.poll();

        }

        return null;
    }

}
