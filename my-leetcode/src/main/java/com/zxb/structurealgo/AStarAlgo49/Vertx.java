package com.zxb.structurealgo.AStarAlgo49;

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

    int x,y; //节点在xy坐标系中的坐标

    int f; //f=distance+节点距离终点的曼哈顿距离(|x1-x2|+|y1-y2|)

    public Vertx(int nodeValue, int x, int y){
        this.nodeValue = nodeValue;
        this.x = x;
        this.y = y;
    }

}
