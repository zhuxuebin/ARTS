package com.zxb.structurealgo.dijkstraAlgo;

/**
 * @ClassName Edge
 * @Description TODO
 * @Author xuery
 * @Date 2019/3/21 16:47
 * @Version 1.0
 */
public class Edge {

    int start;
    int end;
    int weight;
    public Edge(int start, int end,int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
