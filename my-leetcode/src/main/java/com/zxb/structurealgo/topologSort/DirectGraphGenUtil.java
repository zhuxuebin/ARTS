package com.zxb.structurealgo.topologSort;

/**
 * @ClassName DirectGraphGenUtil
 * @Description 拓扑图生成类
 * @Author xuery
 * @Date 2019/3/21 10:56
 * @Version 1.0
 */
public class DirectGraphGenUtil {


    /**
     * 拓扑结构图生成 s->t代表s要先于t执行
     * 箭头从上指向下
     *          2
     *         / \
     *        0   1
     *        |   |
     *        3   5
     *        \  /
     *          4
     * @return
     */
    public static DirectedGraph directedGraphGen1(){

        DirectedGraph directedGraph = new DirectedGraph(6);

        directedGraph.addEdge(2,0);
        directedGraph.addEdge(2,1);
        directedGraph.addEdge(0,3);
        directedGraph.addEdge(1,5);
        directedGraph.addEdge(3,4);
        directedGraph.addEdge(5,4);

        return directedGraph;
    }
}
