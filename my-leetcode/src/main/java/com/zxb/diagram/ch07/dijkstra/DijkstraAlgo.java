package com.zxb.diagram.ch07.dijkstra;

import java.util.*;

/**
 * 描述：
 * 狄克斯卡拉算法：Dijkstra
 * 解决的问题：最短路径-有权重的图
 * <p>
 * 算法使用步骤：
 * 1. 找出最便宜的节点，即可在最短时间内到达的节点
 * 2. 更新该节点的邻居节点的开销
 * 3. 重复上述过程直至对图中每个节点都这样做了
 * 4. 计算最终路径（回溯法-利用节点的父子关系，不断往回找父节点，直至没有父节点）
 * <p>
 * 具体实现：三个表
 * - 原始图信息
 * - 图中每个点离起点最近的距离值表
 * - 图中每个点离起点最近的距离路径的上一个节点（用于回溯找路径）
 * <p>
 * - 原始图信息
 * 这里做个简单处理将节点编号为0,1,2，..., 每个节点包含当前节点的编号及其相邻节点列表，另外用一个二维数组arr[i][j]代表节点到i到j的距离，Integer.MAX_VALUE代表无穷远
 * - 存储最短距离表：一维数组
 * - 存储当前节点的上一节点：map
 *
 * @author 01368080
 * @date 2018/8/10
 */
public class DijkstraAlgo {

    public static void main(String[] args) {
        Node start = new Node(0); //起点
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node end = new Node(3);
        start.postNodes = Arrays.asList(node1, node2);
        node1.postNodes = Arrays.asList(end);
        node2.postNodes = Arrays.asList(node1, end);

        int[][] distances = new int[4][4];
        distances[0][1] = 6;
        distances[0][2] = 2;
        distances[1][3] = 1;
        distances[2][1] = 3;
        distances[2][3] = 5;

        List<Node> nodes = Arrays.asList(start, node1, node2, end);

        System.out.println(dijkstraAlgo(nodes, distances));
    }

    /**
     * 默认第一个点为起点，最后一个点为终点
     *
     * @param nodes
     * @return
     */
    public static List<Node> dijkstraAlgo(List<Node> nodes, int[][] distances) {
        //0：初始化
        int[] minDistances = new int[nodes.size()];
        Set<Integer> hasHandlerSet = new HashSet<>();
        minDistances[0] = 0;
        for (int i = 1; i < nodes.size(); i++) {
            minDistances[i] = Integer.MAX_VALUE;
        }
        Map<Integer, Integer> parentNodeMap = new HashMap<>();

        int minValueIndex = 0;
        int minValue;
        while (minValueIndex != -1) {
            //1. 在minDistance中找到最小的未被处理的
            minValueIndex = -1;
            minValue = Integer.MAX_VALUE;
            for (int i = 0; i < minDistances.length; i++) {
                if (!hasHandlerSet.contains(i) && minValue > minDistances[i]) {
                    minValue = minDistances[i];
                    minValueIndex = i;
                }
            }
            //2. 找出最小的未被处理的节点的所有后继节点并判断是否要更新，要更新则还需保留父子节点关系
            if (minValueIndex != -1) {
                hasHandlerSet.add(minValueIndex); //标记该下标对应的节点已经处理完了
                Node currMinNode = nodes.get(minValueIndex);
                List<Node> postNodes = currMinNode.postNodes;
                if (postNodes != null && postNodes.size() > 0) {
                    for (Node postNode : postNodes) {
                        if (minDistances[minValueIndex] + distances[minValueIndex][postNode.index] < minDistances[postNode.index]) {
                            minDistances[postNode.index] = minDistances[minValueIndex] + distances[minValueIndex][postNode.index];
                            parentNodeMap.put(postNode.index, minValueIndex);
                        }
                    }
                }
            }
            //3. 重复1,2直至所有节点都被处理完
        }
        //根据map回溯统计出路径,从end节点开始
        List<Node> resultNodes = new ArrayList<>();
        int index = nodes.size() - 1;
        while (parentNodeMap.get(index) != null){
            resultNodes.add(nodes.get(index));
            index = parentNodeMap.get(index);
        }
        resultNodes.add(nodes.get(index)); //第一个节点
        Collections.reverse(resultNodes);

        System.out.println("min distance:"+minDistances[nodes.size()-1]);
        return resultNodes;
    }

    static class Node {
        private int index;
        List<Node> postNodes;

        public Node(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"index\":")
                    .append(index);
            sb.append(",\"postNodes\":")
                    .append(postNodes);
            sb.append('}');
            return sb.toString();
        }
    }
}
