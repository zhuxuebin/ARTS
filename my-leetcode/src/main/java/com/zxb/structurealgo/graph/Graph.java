package com.zxb.structurealgo.graph;

import java.util.LinkedList;

/**
 * @ClassName Graph
 * @Description 图的数据结构 采用邻接表存储-采用链表存储
 * @Author xuery
 * @Date 2019/1/15 17:38
 * @Version 1.0
 */
public class Graph {

    int v; //总顶点数

    LinkedList<Integer>[] adj; //邻接表

    public Graph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for(int i=0;i<v;i++){
            adj[i] = new LinkedList<>();
        }

    }

    //无向图采用
    public void addEdge(int s, int t){
        adj[s].add(t);
        adj[t].add(s);
    }
}
