package com.zxb.structurealgo.graph;

/**
 * @ClassName GraphUtil
 * @Description TODO
 * @Author xuery
 * @Date 2019/1/15 17:50
 * @Version 1.0
 */
public class GraphUtil {

    /**
     *  0--1--2
     *  |  |  |
     *  3--4--5
     *     |  |
     *     6--7
     * @return
     */
    public static Graph generateGraph(int v){
        Graph graph = new Graph(v);

        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(0,3);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);

        return graph;
    }
}
