package com.zxb.structurealgo.AStarAlgo49;


/**
 * @ClassName AStarGraphGenUtil
 * @Description 拓扑图生成类
 * @Author xuery
 * @Date 2019/3/21 10:56
 * @Version 1.0
 */
public class AStarGraphGenUtil {


    /**
     *
     * @return
     */
    public static AStarGraph aStarGraphGen1(){

        AStarGraph graph = new AStarGraph(6);

        graph.addEdge(0,1,1);
        graph.addEdge(0,2,3);
        graph.addEdge(1,2,1);
        graph.addEdge(1,3,2);
        graph.addEdge(2,3,1);
        graph.addEdge(3,4,2);
        graph.addEdge(3,5,5);
        graph.addEdge(4,5,2);

        graph.addVertx(0,0,0);
        graph.addVertx(1,0,0);
        graph.addVertx(2,0,0);
        graph.addVertx(3,0,0);
        graph.addVertx(4,0,0);
        graph.addVertx(5,0,0);

        return graph;
    }

    public static AStarGraph aStarGraphGen2(){

        AStarGraph graph = new AStarGraph(6);

        graph.addEdge(2,0,4);
        graph.addEdge(2,1,2);
        graph.addEdge(1,0,1);
        graph.addEdge(0,5,2);
        graph.addEdge(2,3,4);
        graph.addEdge(2,4,5);
        graph.addEdge(3,5,6);
        graph.addEdge(4,5,7);

        graph.addVertx(0,0,0);
        graph.addVertx(1,1,1);
        graph.addVertx(2,2,2);
        graph.addVertx(3,3,3);
        graph.addVertx(4,4,4);
        graph.addVertx(5,5,5);

        return graph;
    }
}
