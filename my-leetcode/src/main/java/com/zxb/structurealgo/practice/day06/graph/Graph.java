package com.zxb.structurealgo.practice.day06.graph;

import java.util.LinkedList;

/**
 * @ClassName Graph
 * @Description 图的数据结构
 * @Author xuery
 * @Date 2019/6/10 15:32
 * @Version 1.0
 */
public class Graph {

    LinkedList<Integer>[] nodes;
    int v;

    public Graph(int v){
        this.v = v;
        nodes = new LinkedList[v];

        for(int i=0;i<v;i++){
            nodes[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int s, int t){
        nodes[s].add(t);
    }
}
