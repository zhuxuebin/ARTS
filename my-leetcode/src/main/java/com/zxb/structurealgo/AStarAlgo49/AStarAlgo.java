package com.zxb.structurealgo.AStarAlgo49;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName AStarAlgo
 * @Description A*搜索算法，快速找出一条次优路径解，参考49讲
 *
 * 与Dijkstra算法的区别：
 * 1. 节点引入(x,y)属性，出队列顺序要综合顶点离起点的距离+顶点离终点的距离=f
 * 2. 距离等于1中两者之和，新增属性f=g+h;
 * 3. 遍历到终点就结束，所以不保证最优解
 * @Author xuery
 * @Date 2019/4/9 15:27
 * @Version 1.0
 */
public class AStarAlgo {

    public static void main(String[] args) {

        AStarGraph graph = AStarGraphGenUtil.aStarGraphGen2();

        AStarAlgo aStarAlgo = new AStarAlgo();
        List<Integer> result = aStarAlgo.aStarAlgo(graph,2,5);
        System.out.println(result);
    }

    public List<Integer> aStarAlgo(AStarGraph graph, int start, int end){

        //优先级队列，用于每次取距离最短的哪个节点,这里java自带的优先级队列没有提供update接口，只能自己实现一个了
        int v = graph.v;
        LinkedList<Edge>[] adj = graph.adj;
        Vertx[] vertxes = graph.vertxes;
        AStarPriorityQueue queue = new AStarPriorityQueue(v);

        //preNode数组，这里节点取值是0-v-1, 所以直接用数组就可以了；如果不是的话就要用map了
        int[] preNodeArr = new int[v];
        boolean[] visited = new boolean[v];
        int[] currMinDisArr = new int[v];

        //start作为第一个点,然后根据当前节点广度优先遍历其后继节点
        Vertx vertx0 = vertxes[start];
        queue.add(vertx0);
        preNodeArr[start] = -1;
        visited[start] = true;
        while(!queue.isEmpty()){
            Vertx currMinFVertx = queue.poll();
            //看它的后继节点
            LinkedList<Edge> nextEdges = adj[currMinFVertx.nodeValue];
            for(int i=0;i<nextEdges.size();i++){
                Edge nextEdge = nextEdges.get(i);
                if(!visited[nextEdge.end]){
                    //该节点之前没有进入过队列，说明是第一次遍历到它
                    Vertx vertx = vertxes[nextEdge.end];
                    vertx.distance = currMinFVertx.distance + nextEdge.weight;
                    vertx.f = vertx.distance + Math.abs(vertx.x-vertxes[end].x)+Math.abs(vertx.y-vertxes[end].y);
                    queue.add(vertx);

                    preNodeArr[nextEdge.end] = currMinFVertx.nodeValue;
                    visited[nextEdge.end] = true;
                    currMinDisArr[nextEdge.end] = vertx.distance;
                } else {
                    //非第一次遍历到它，需要比较下currMinFVertx.distance+nextEdge.weight与nextEdge.end对应的distance
                    //这里不能每次都去队列中查对应的数据比较，太慢了，构建一个辅助数组
                    int currDis = currMinFVertx.distance+nextEdge.weight;
                    if(currMinDisArr[nextEdge.end] > currDis){
                        //需要更新一波
                        Vertx vertx = vertxes[nextEdge.end];
                        vertx.distance = currDis;
                        vertx.f = vertx.distance + Math.abs(vertx.x-vertxes[end].x)+Math.abs(vertx.y-vertxes[end].y);
                        queue.update(vertx);

                        preNodeArr[nextEdge.end] = currMinFVertx.nodeValue;
                        currMinDisArr[nextEdge.end] = currDis;
                    }
                }

                //截止条件：遍历到end节点就结束
                if(nextEdge.end == end){
                    queue.clear();
                    break;
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
