package com.zxb.structurealgo.topologSort;

import java.util.LinkedList;

/**
 * @ClassName TopologSortByDFS
 * @Description 深度优先搜索遍历，基本思想：递归思路，先求出逆邻接表，逆邻接表中s->t表示s依赖于t，t要先输出
 * 按照这种思路，我们先输出递归输出当前节点的后继节点，再输出当前节点即可
 * @Author xuery
 * @Date 2019/3/21 12:44
 * @Version 1.0
 */
public class TopologSortByDFS {

    public static void main(String[] args) {

        DirectedGraph directedGraph = DirectGraphGenUtil.directedGraphGen1();

        TopologSortByDFS topologSortByDFS = new TopologSortByDFS();
        topologSortByDFS.topologSortByDFS(directedGraph);
    }

    public void topologSortByDFS(DirectedGraph directedGraph){

        //求逆邻接表，实际中如果我们打算使用DFS，则构建图时直接让s->t表示s依赖于t即可，而不用先求逆邻接表了
        int v = directedGraph.v;
        LinkedList<Integer>[] adj = directedGraph.adj;
        LinkedList<Integer>[] reverseAdj = new LinkedList[v];
        for(int i=0;i<v;i++){
            reverseAdj[i] = new LinkedList<>();
        }
        for(int i=0;i<v;i++){
            LinkedList<Integer> currNode = adj[i];
            for(int j=0;j<currNode.size();j++){
                reverseAdj[currNode.get(j)].add(i);
            }
        }

        //在reverseAdj中:s->t代表s依赖t，要先输出t,再输出s  递归输出
        //图上的每个节点都遍历一遍
        boolean[] visited = new boolean[v];//记录节点是否遍历过，遍历过就不用再遍历了
        for(int i=0;i<v;i++){
            if(!visited[i]) {
                dfsRecursion(reverseAdj[i], i, reverseAdj, visited);
            }
        }
    }

    private void dfsRecursion(LinkedList<Integer> node,int nodeIndex, LinkedList<Integer>[] reverseAdj, boolean[] visited){
        if(visited[nodeIndex]){
            return;
        }
        visited[nodeIndex] = true;
        for(int i=0;i<node.size();i++){
            dfsRecursion(reverseAdj[node.get(i)],node.get(i), reverseAdj,visited);
        }
        System.out.print(nodeIndex+" ");
    }
}
