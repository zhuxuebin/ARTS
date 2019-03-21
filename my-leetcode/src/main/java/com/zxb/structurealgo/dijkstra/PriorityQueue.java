package com.zxb.structurealgo.dijkstra;

/**
 * @ClassName PriorityQueue
 * @Description 优先级队列，根据Vertex.distance构建小顶堆
 * @Author xuery
 * @Date 2019/3/21 20:43
 * @Version 1.0
 */
public class PriorityQueue {

    Vertx[] nodes; //堆数组
    int count;     //当前队列实际存在多少个值

    public PriorityQueue(int v){
        /**
         * 多加1，从下标为1的位置开始存数据，方便计算堆的节点的左右子树
         * 下标为i的左右子树为:2*i,2*i+1,父节点为i/2
         */
        nodes = new Vertx[v+1];
        count = 0; //专栏里面应该是写错了
    }

    public Vertx poll(){
        return null;
    }

    public void add(Vertx vertx){

    }

    public void update(Vertx vertx){

    }

    public boolean isEmpty(){
        return false;
    }
}
