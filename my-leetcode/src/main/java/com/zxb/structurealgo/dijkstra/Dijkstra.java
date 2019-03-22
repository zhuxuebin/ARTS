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
        DirectedWeightGraph graph = DirectWeightGraphGenUtil.directedWeightGraphGen1();

        Dijkstra dijkstra = new Dijkstra();
        List<Integer> resultList = dijkstra.dijkstraAlgo(graph,0,5);
        System.out.println(resultList);
    }

    public List<Integer> dijkstraAlgo(DirectedWeightGraph graph, int start, int end){

        //优先级队列，用于每次取距离最短的哪个节点,这里java自带的优先级队列没有提供update接口，只能自己实现一个了
        int v = graph.v;
        LinkedList<Edge>[] adj = graph.adj;
        PriorityQueue queue = new PriorityQueue(v);

        //preNode数组，这里节点取值是0-v-1, 所以直接用数组就可以了；如果不是的话就要用map了
        int[] preNodeArr = new int[v];
        boolean[] visited = new boolean[v];
        int[] currMinDisArr = new int[v];

        //start作为第一个点,然后根据当前节点广度优先遍历其后继节点
        Vertx vertx0 = new Vertx(start, 0);
        queue.add(vertx0);
        preNodeArr[start] = -1;
        visited[start] = true;
        while(!queue.isEmpty()){
            Vertx currMinDisVertx = queue.poll();
            //看它的后继节点
            LinkedList<Edge> succNode = adj[currMinDisVertx.nodeValue];
            for(int i=0;i<succNode.size();i++){
                if(!visited[succNode.get(i).end]){
                    //该节点之前没有进入过队列，说明是第一次遍历到它
                    Vertx vertx = new Vertx(succNode.get(i).end, currMinDisVertx.distance+succNode.get(i).weight);
                    queue.add(vertx);
                    preNodeArr[succNode.get(i).end] = currMinDisVertx.nodeValue;
                    visited[succNode.get(i).end] = true;
                    currMinDisArr[succNode.get(i).end] = currMinDisVertx.distance+succNode.get(i).weight;
                } else {
                    //非第一次遍历到它，需要比较下currMinDisVertx.distance+succNode.get(i).weight与succNode.get(i).end对应的distance
                    //这里不能每次都去队列中查对应的数据比较，太慢了，构建一个辅助数组
                    if(currMinDisArr[succNode.get(i).end] > currMinDisVertx.distance+succNode.get(i).weight){
                        //需要更新一波
                        queue.update(new Vertx(succNode.get(i).end, currMinDisVertx.distance+succNode.get(i).weight));
                        preNodeArr[succNode.get(i).end] = currMinDisVertx.nodeValue;
                        currMinDisArr[succNode.get(i).end] = currMinDisVertx.distance+succNode.get(i).weight;
                    }
                }
            }
        }

        //根据preNodeArr回溯
        List<Integer> resultList = new ArrayList<>();
        int index = end;
        while(index != -1){
            resultList.add(index);
            index = preNodeArr[index];
        }
        return resultList;
    }

}
