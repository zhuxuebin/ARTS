package com.zxb.structurealgo.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BFS
 * @Description 广度优先搜索
 * @Author xuery
 * @Date 2019/1/15 17:50
 * @Version 1.0
 */
public class BFS {

    public static void main(String[] args) {
        Graph graph = GraphUtil.generateGraph(8);

        BFS bfs = new BFS();
        bfs.BFS(graph, 0,7);
    }

    /**
     * 广度优先遍历 类似于树的层次遍历 可以借助队列
     * 这里构造Graph简化了，根据输入v，则对应的顶点值为0--v-1
     * <p>
     * 这样查找出来的是最短路径
     *
     * @param graph
     * @param s     寻找的起点
     * @param t     寻找的终点
     */
    public void BFS(Graph graph, int s, int t) {

        //无向图是双向的，可能导致一个顶点被多次访问，但我们要保证一个顶点只访问一次
        boolean[] visited = new boolean[graph.v];

        //记录当前节点是由前面哪一个点遍历而来的
        int[] pre = new int[graph.v];
        for (int i = 0; i < graph.v; i++) {
            pre[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s); //从起点开始遍历

        while (!queue.isEmpty()) {
            //如果不是起点，则它一定是由它之前的相邻的一个点遍历而来的
            int currV = queue.poll();
            if(visited[currV]){
                continue;
            }
            visited[currV] = true;

            //找到了，则打印出访问路径-递归
            if (currV == t) {
                print(currV, pre);
                return;
            }

            //将currV的邻接未访问顶点入队列
            for (Integer adjValue : graph.adj[currV]) {
                if (!visited[adjValue]) {
                    queue.add(adjValue);
                    pre[adjValue] = currV;
                }
            }
        }
    }

    private void print(int currV, int[] pre){

        if(currV == -1){
            return;
        }

        int currVpre = pre[currV];
        if(currVpre != -1){
            print(currVpre, pre);
        }

        System.out.println(currV + " ");
    }
}
