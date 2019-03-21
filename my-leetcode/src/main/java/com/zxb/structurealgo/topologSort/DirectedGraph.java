package com.zxb.structurealgo.topologSort;

import java.util.LinkedList;

/**
 * @ClassName DirectedGraph
 * @Description 有向图的数据结构 采用邻接表存储-采用链表存储
 * @Author xuery
 * @Date 2019/1/15 17:38
 * @Version 1.0
 */
public class DirectedGraph {

    int v; //总顶点数

    LinkedList<Integer>[] adj; //邻接表，存储每个节点的后续节点

    public DirectedGraph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for(int i=0;i<v;i++){
            adj[i] = new LinkedList<>();
        }

    }

    //有向图采用 拓扑结构中：s->t代表s要先与t执行
    public void addEdge(int s, int t){
        adj[s].add(t);
    }
}
