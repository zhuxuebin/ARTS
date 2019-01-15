package com.zxb.diagram.ch06.bfs;

import sun.reflect.generics.tree.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 描述：
 *  BFS:广度优先搜索，无权重的有向图
 *  应用场景：最少换乘，不考虑每次乘车的时间权重
 *  先遍历与当前节点的相邻节点，然后再遍历所有相邻节点的相邻节点，以此类推
 *  实现方法：队列
 *
 *  这里我想了一下：直接采用二叉树的广度优先遍历对该算法举例即可
 * @author xuery
 * @date 2018/8/10
 */
public class BrandFindSearch {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        brandFindSearch(root);
    }

    public static void brandFindSearch(TreeNode root){
        if(root == null){
            return;
        }
        Deque<TreeNode> treeNodeDeque = new LinkedList<>();
        treeNodeDeque.add(root);
        //代表每行的元素总个数
        int count = 1;
        while(!treeNodeDeque.isEmpty()){
            int nextCount = 0;
            while(count-- > 0){
                //当前层有count个节点
                TreeNode p = treeNodeDeque.poll();
                System.out.print(p.val+"  ");
                if(p.left != null){
                    treeNodeDeque.add(p.left);
                    nextCount++;
                }
                if(p.right != null){
                    treeNodeDeque.add(p.right);
                    nextCount++;
                }
            }
            System.out.println();
            count = nextCount;
        }
    }

    static class TreeNode{
        private TreeNode left;
        private TreeNode right;
        private int val;
        public TreeNode(int val){
            this.val = val;
        }

    }
}
