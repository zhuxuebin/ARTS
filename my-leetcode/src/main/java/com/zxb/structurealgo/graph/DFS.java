package com.zxb.structurealgo.graph;

import java.util.LinkedList;

/**
 * @ClassName DFS
 * @Description 深度优先搜索
 * @Author xuery
 * @Date 2019/1/15 18:07
 * @Version 1.0
 */
public class DFS {

    public static void main(String[] args) {
        Graph graph = GraphUtil.generateGraph(8);
        DFS dfs = new DFS();
        dfs.DFS(graph,0,7);
    }

    private boolean find = false;

    /**
     * 深度优先遍历
     * 不一定是最短路径
     * @param graph
     * @param s     寻找的起点
     * @param t     寻找的终点
     */
    public void DFS(Graph graph, int s, int t) {

        boolean[] visited = new boolean[graph.v];

        int[] pre = new int[graph.v];
        for (int i = 0; i < graph.v; i++) {
            pre[i] = -1;
        }

        recursion(graph, s, t, pre, visited);
    }

    private void recursion(Graph graph, int currV, int t, int[] pre, boolean[] visited){

        if(find){
            //找到一条路径就停止（1）
            return;
        }

        //一个一个遍历currV的后续节点，后续节点也同理--递归
        LinkedList<Integer> currList = graph.adj[currV];
        visited[currV] = true;
        for (int i = 0; i < currList.size(); i++) {
            int postV = currList.get(i);

            //上面的find可能挡不住，因为可能已经进到for循环里面来了，比如从某个数开始for循环，第一次循环后一直往下递归找到了，第二次循环还没递归就可能找到了
            /**
             *  0--1--2
             *  |  |  |
             *  3--4--5
             *     |  |
             *     6--7
             * 比如遍历到5时，然后for循环，第一次：0-1-2-5-4-6-7经过多次递归找到了，第二次5->7直接就找了，都没来得及递归；
             * 所以架上!find防止最上面的（1）没法挡
             */
            if(postV == t && !find){
                //找到了回溯法打印
                pre[postV] = currV;
                print(postV, pre);
                //这里return只会终结掉本层循环，上一层的for循环还是会继续执行，所以需要加个全局标志让所有递归停止（1）
                find = true;
                return;
            }

            if(!visited[postV]){
                //这一句不能放在外面，如果postV已经被访问过了继续设置，可能会导致环，print永远就停止不了了
                pre[postV] = currV;
                recursion(graph, postV, t, pre, visited);
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
