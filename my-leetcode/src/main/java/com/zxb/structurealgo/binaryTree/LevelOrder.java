package com.zxb.structurealgo.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LevelOrder
 * @Description 二叉树按层遍历
 * @Author xuery
 * @Date 2019/1/4 10:10
 * @Version 1.0
 */
public class LevelOrder {

    public static void main(String[] args) {
        TreeNode root = GenerateBinaryTreeUtil.generateTreeNode();
        levelOrder(root);
    }

    /**
     * 按层遍历
     * 思路分析：先遍历根节点，再遍历它的左右子树，再遍历它的左右子树的左右子树
     * 借助队列可以实现，遍历根节点并将左右子树入队列，再从队列中取左右子树并将它的左右子树的左右子树入队列
     * 关键要记录每一层有多少个节点，这样才知道每次要从队列取多少个数作为一层
     * @param root
     */
    public static void levelOrder(TreeNode root){

        if(root == null){
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode p;
        int count = 1; //第一层节点个数初始值为1
        while(!queue.isEmpty()){
            int countTemp = 0;
            while(count-- > 0) {
                p = queue.poll();
                System.out.print(p.val+" ");
                if(p.left != null){
                    queue.add(p.left);
                    countTemp++;
                }
                if(p.right != null){
                    queue.add(p.right);
                    countTemp++;
                }
            }
            System.out.println();
            count = countTemp;
        }
    }
}
