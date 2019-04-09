package com.zxb.structurealgo.AStarAlgo49;

import java.util.LinkedList;

/**
 * @ClassName AStarGraph
 * @Description 带边权重的A*图
 * @Author xuery
 * @Date 2019/3/21 16:37
 * @Version 1.0
 */
public class AStarGraph {

    int v;
    LinkedList<Edge>[] adj; //邻接表
    Vertx[] vertxes;
    public AStarGraph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for(int i=0;i<v;i++){
            adj[i] = new LinkedList<>();
        }

        vertxes = new Vertx[v];
    }

    //s->t，权重为weight
    public void addEdge(int s, int t, int weight){
        adj[s].add(new Edge(s,t,weight));
    }

    //添加顶点的坐标
    public void addVertx(int id, int x, int y){
        vertxes[id] = new Vertx(id,x,y);
    }
}
