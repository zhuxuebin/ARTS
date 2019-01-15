package com.zxb.structurealgo.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName DegreeOfNFriend
 * @Description 求N度好友列表
 * @Author xuery
 * @Date 2019/1/15 18:08
 * @Version 1.0
 */
public class DegreeOfNFriend {

    public static void main(String[] args) {
        Graph graph = GraphUtil.generateGraph(8);
        DegreeOfNFriend degreeOfNFriend = new DegreeOfNFriend();
        degreeOfNFriend.BFS(graph, 0, 2);
    }



    /**
     * 广度优先遍历 类似于树的层次遍历 可以借助队列
     * 这里构造Graph简化了，根据输入v，则对应的顶点值为0--v-1
     * <p>
     * 这样查找出来的是最短路径
     *
     * @param graph
     * @param s     目标顶点
     * @param k     k度好友-指离目标顶点相距k条边
     */
    public void BFS(Graph graph, int s, int k) {

        //无向图是双向的，可能导致一个顶点被多次访问，但我们要保证一个顶点只访问一次
        boolean[] visited = new boolean[graph.v];

        //记录当前节点是由前面哪一个点遍历而来的
//        int[] pre = new int[graph.v];
//        for (int i = 0; i < graph.v; i++) {
//            pre[i] = -1;
//        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s); //从起点开始遍历

        int currCount = 1; //当前层有几个元素，初始值为1
        int currDistance = 0; //记录当前层距离目标顶点的距离
        while (!queue.isEmpty()) {
            //如果不是起点，则它一定是由它之前的相邻的一个点遍历而来的

            //将currV的邻接未访问顶点入队列
            int currCountTmp = 0;
            while(currCount-- > 0) {
                int currV = queue.poll();
                //!visited[currV],因为同一层的后续节点可能存在相同的，导致这个相同的会被访问两次
                if(currDistance == k && !visited[currV]){
                    //当前层未遍历的就是k度好友
                    System.out.println(currV);
                }
                visited[currV] = true;
                for (Integer adjValue : graph.adj[currV]) {
                    if (!visited[adjValue]) {
                        queue.add(adjValue);
//                        pre[adjValue] = currV;
                        currCountTmp++;
                    }
                }
            }
            if(currDistance == k){
                return; //结束
            }
            currDistance++;
            currCount = currCountTmp;
        }
    }
}
