package com.zxb.structurealgo.dijkstra;

import java.util.LinkedList;

/**
 * @ClassName DirectedWeightGraph
 * @Description 带边权重的图
 * @Author xuery
 * @Date 2019/3/21 16:37
 * @Version 1.0
 */
public class DirectedWeightGraph {

    int v;
    LinkedList<Edge>[] adj;
    public DirectedWeightGraph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for(int i=0;i<v;i++){
            adj[i] = new LinkedList<>();
        }
    }

    //s->t，权重为weight
    public void addEdge(int s, int t, int weight){
        adj[s].add(new Edge(s,t,weight));
    }
}
