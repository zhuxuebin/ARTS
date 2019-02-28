package com.zxb.structurealgo.greedy;

import java.util.*;

/**
 * @ClassName HuffmanCoding
 * @Description 哈弗曼编码的实现
 * 借助优先级队列，构造出二叉树，然后遍历二叉树得到编码（怎么遍历是个问题）
 * @Author xuery
 * @Date 2019/2/25 21:09
 * @Version 1.0
 */
public class HuffmanCoding {

    public static void main(String[] args) {
        HuffmanNode nodea = new HuffmanNode("a", 450);
        HuffmanNode nodeb = new HuffmanNode("b", 350);
        HuffmanNode nodec = new HuffmanNode("c", 90);
        HuffmanNode noded = new HuffmanNode("d", 60);
        HuffmanNode nodee = new HuffmanNode("e", 30);
        HuffmanNode nodef = new HuffmanNode("f", 20);

        List<HuffmanNode> nodes = Arrays.asList(nodea, nodeb, nodec, noded, nodee, nodef);
        huffmanCoding(nodes);

        int sum = 0;
        for (int i = 0; i < nodes.size(); i++) {
            HuffmanNode node = nodes.get(i);
            System.out.println(node.key + ":" + node.code);
        }



    }

    static class HuffmanNode {
        String key;
        int value;
        String code;
        HuffmanNode leftNode;
        HuffmanNode rightNode;

        public HuffmanNode(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void huffmanCoding(List<HuffmanNode> nodes) {
        //以value从小到大构建优先级队列
        Queue<HuffmanNode> queue = new PriorityQueue<>(nodes.size(), new Comparator<HuffmanNode>() {
            @Override
            public int compare(HuffmanNode o1, HuffmanNode o2) {
                return o1.value - o2.value;
            }
        });

        for (int i = 0; i < nodes.size(); i++) {
            queue.add(nodes.get(i));
        }

        //两两合并，并将合并结果放入队列
        HuffmanNode root = null;
        while (!queue.isEmpty()) {
            //每次取两个value最小的node,合并成一个节点，并重新入队列；直到只有一个值说明完毕
            HuffmanNode leftNode = queue.poll();
            if (queue.isEmpty()) {
                root = leftNode;
                break;
            }
            HuffmanNode rightNode = queue.poll();
            HuffmanNode parentNode = new HuffmanNode(leftNode.key + rightNode.key, leftNode.value + rightNode.value);
            parentNode.leftNode = leftNode;
            parentNode.rightNode = rightNode;

            queue.add(parentNode);
        }

        //遍历Huffman树，获取每个叶子节点的编码
        root.code = "";
        setNodeCode(root);
    }

    private static void setNodeCode(HuffmanNode node) {
        if (node == null) {
            return;
        }

        if (node.leftNode != null) {
            node.leftNode.code = node.code + "0";
        }

        if (node.rightNode != null) {
            node.rightNode.code = node.code + "1";
        }

        setNodeCode(node.leftNode);
        setNodeCode(node.rightNode);
    }
}
