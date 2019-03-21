package com.zxb.structurealgo.dijkstra;

/**
 * @ClassName Vertx
 * @Description 记录当前节点离起点的最短距离
 * @Author xuery
 * @Date 2019/3/21 16:57
 * @Version 1.0
 */
public class Vertx {

    int nodeValue; //图中节点值
    int distance;  //节点距离起点的最小值

    public Vertx(int nodeValue, int distance){
        this.nodeValue = nodeValue;
        this.distance = distance;
    }


}
