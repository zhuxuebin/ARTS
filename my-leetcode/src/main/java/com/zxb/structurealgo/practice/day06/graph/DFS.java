package com.zxb.structurealgo.practice.day06.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName DFS
 * @Description 图的深度递归
 *
 * 基本思路：在for循环中递归
 * @Author xuery
 * @Date 2019/6/10 15:30
 * @Version 1.0
 */
public class DFS {

    public static void main(String[] args) {
        Graph graph = GraphUtil.generateGraph(8);

        DFS dfs = new DFS();
        dfs.DFS(graph,0,7);
    }

    /**
     *
     * @param graph
     * @param s 起点
     * @param t 终点
     */
    public void DFS(Graph graph,int s, int t){

        LinkedList<Integer>[] nodes = graph.nodes;
        int v = graph.v;
        boolean[] visited = new boolean[v]; //记录是否已经遍历过
        int[] preArr = new int[v]; //记录当前节点是由哪个前继节点遍历而来
        for(int i=0;i<v;i++){
            preArr[i] = -1;
        }

        recursion(nodes, s, t, visited, preArr);
    }

    private void recursion(LinkedList<Integer>[] nodes, int s, int t, boolean[] visited, int[] preArr){

        LinkedList<Integer> currNode = nodes[s];
        visited[s] = true;
        for(int i=0;i<currNode.size();i++){
            int next = currNode.get(i);
            if(visited[next]){
                //访问过了
                continue;
            }

            preArr[next] = s;
            if(next == t){
                //说明找到了
                printPath(t, preArr);
                return;
            }
            recursion(nodes, next, t, visited, preArr);
        }
    }

    private void printPath(int t,int[] preArr){
        List<Integer> pathList = new ArrayList<>();
        pathList.add(t);
        int index = t;
        while(preArr[index] != -1){
            pathList.add(preArr[index]);
            index = preArr[index];
        }

        for(int i=pathList.size()-1;i>=0;i--){
            System.out.print(pathList.get(i)+" ");
        }
        System.out.println();
    }
}
