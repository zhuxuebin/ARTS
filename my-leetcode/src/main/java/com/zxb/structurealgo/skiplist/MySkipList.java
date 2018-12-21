package com.zxb.structurealgo.skiplist;

import java.util.Arrays;
import java.util.Random;

/**
 * 描述：自己参考思路重新实现一个跳表
 * <p>
 * 前提：跳表里面是没有重复数据的--如redis的zset
 *
 * @author xuery
 * @date 2018/12/21
 */
public class MySkipList {

    //跳表最大层数
    private int MAX_LEVEL = 16;

    //跳表当前最大层数
    private int levelCount = 1;

    //跳表虚拟头节点，方便操作
    private Node head = new Node();

    Random random = new Random();

    int findCount = 0;

    /**
     * 查找node
     * 核心操作，插入和删除都依赖它
     * 从最高层开始找，每次都找最后一个小于当前值的Node，直至最低一层，然后看后继节点的值是否等于，等于就找到，否则不存在
     *
     * @param value
     * @return
     */
    public Node find(int value) {

        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].value < value) {
                p = p.forwards[i];
                findCount++;
            }
            //继续往下一层找
        }
        if (p.forwards[0] != null && p.forwards[0].value == value) {
            return p.forwards[0];
        }
        return null;
    }

    /**
     * 插入操作
     *
     * @param value
     */
    public void insert(int value) {
        //确定要在从哪一层开始插入
        int insertMaxLevelCount = insertMaxLevelCount();
        Node newNode = new Node();
        newNode.value = value;
        newNode.maxLevel = insertMaxLevelCount;

        //确定每一层插入的位置的前一个节点,和查找类似
        Node[] update = new Node[insertMaxLevelCount];
        Node p = head;
        for (int i = insertMaxLevelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].value < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        //开始一层一层插入
        for (int i = insertMaxLevelCount - 1; i >= 0; i--) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        //更新当前最大层数
        levelCount = Math.max(levelCount, insertMaxLevelCount);
    }

    private int insertMaxLevelCount() {
        int insertMaxLevelCount = 1;
        for (int i = 1; i < MAX_LEVEL; i++) {
            int randomVal = random.nextInt();
            if (randomVal % 2 == 1) {
                insertMaxLevelCount++;
            }
        }
        return insertMaxLevelCount;
    }


    /**
     * 删除node
     *
     * @param value
     * @return
     */
    public void delete(int value) {
        //查找每一层要删除的节点的前一个节点，也可能并不需要删除
        Node[] update = new Node[levelCount];
        Node p = head;
        for(int i=levelCount-1;i>=0;i--){
            while(p.forwards[i] != null && p.forwards[i].value < value){
                p = p.forwards[i];
            }
            update[i] = p;
        }

        //开始一层一层删除, 这个if可以大大减少遍历的时间，提高速度
        if(p.forwards[0] != null && p.forwards[0].value == value) {
            for (int i = levelCount - 1; i >= 0; i--) {
                if (update[i].forwards[i] != null && update[i].forwards[i].value == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

    }

    public void printAll(){
        Node p = head.forwards[0];
        while(p != null){
            System.out.print(p+" ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    public void printAllValueByLevel(){

        for (int i=levelCount-1;i>=0;i--){
            Node p = head.forwards[i];
            while(p!= null){
                System.out.print(p.value+" ");
                p = p.forwards[i];
            }
            System.out.println();
        }
    }


    //跳表结构
    class Node {
        private int value = -1;
        //每一层的后继节点，可能为空
        private Node[] forwards = new Node[MAX_LEVEL];
        //当前节点有值的最高层
        private int maxLevel = 1;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"value\":")
                    .append(value);
            sb.append(",\"maxLevel\":")
                    .append(maxLevel);
            sb.append('}');
            return sb.toString();
        }
    }
}
