package com.zxb.structurealgo.topologSort;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName TopologSortByKahn
 * @Description kahn算法实现拓扑排序
 *
 * 前提：假设图中s->t,代表s要先于t执行
 * 基本算法思路：当图中节点入度为0时就可以输出了，说明这时候它没有依赖谁
 * 当输出之后，要将该节点指向其他节点的关系断开，以便下一次统计
 *
 *
 * @Author xuery
 * @Date 2019/3/21 10:47
 * @Version 1.0
 */
public class TopologSortByKahn {

    public static void main(String[] args) {
        DirectedGraph directedGraph = DirectGraphGenUtil.directedGraphGen1();

        TopologSortByKahn topologSortByKahn = new TopologSortByKahn();
        topologSortByKahn.topologSortByKahn(directedGraph);
    }


    public void topologSortByKahn(DirectedGraph directedGraph){

        //先统计下每个节点的入度个数
        int v = directedGraph.v;
        LinkedList<Integer>[] adj =  directedGraph.adj;
        Queue<Integer> queue = new LinkedList<>(); //采用队列和栈都可以，多个入度为0的节点先输出哪一个都可以
        int[] indegrees = new int[v];
        for(int i=0;i<v;i++){
            LinkedList<Integer> currList = adj[i];
            for(int j=0;j<currList.size();j++){
                indegrees[currList.get(j)]++;
            }
        }
        //找出入度为0的节点入队列优先输出
        for(int i=0;i<v;i++){
            if(indegrees[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            Integer currNodeValue = queue.poll();
            System.out.print(currNodeValue+" ");
            //删除掉该节点与后继节点的关系,在这里等价于将后继节点的入度减1
            LinkedList<Integer> currList = adj[currNodeValue];
            for(int j=0;j<currList.size();j++) {
                indegrees[currList.get(j)]--;
                if(indegrees[currList.get(j)] == 0){
                    //说明对应后继节点的入度为0，没有要先于它加载的节点了，直接入队列输出
                    queue.add(currList.get(j));
                }
            }
        }
    }
}
