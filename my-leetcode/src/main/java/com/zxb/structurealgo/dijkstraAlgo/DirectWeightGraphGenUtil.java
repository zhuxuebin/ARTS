package com.zxb.structurealgo.dijkstraAlgo;


/**
 * @ClassName DirectWeightGraphGenUtil
 * @Description 拓扑图生成类
 * @Author xuery
 * @Date 2019/3/21 10:56
 * @Version 1.0
 */
public class DirectWeightGraphGenUtil {


    /**
     *
     * @return
     */
    public static DirectedWeightGraph directedWeightGraphGen1(){

        DirectedWeightGraph graph = new DirectedWeightGraph(6);

        graph.addEdge(0,1,1);
        graph.addEdge(0,2,3);
        graph.addEdge(1,2,1);
        graph.addEdge(1,3,2);
        graph.addEdge(2,3,1);
        graph.addEdge(3,4,2);
        graph.addEdge(3,5,5);
        graph.addEdge(4,5,2);

        return graph;
    }
}
