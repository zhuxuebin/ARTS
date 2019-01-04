package com.zxb.structurealgo.binaryTree;

/**
 * @ClassName GenerateBinaryTreeUtil
 * @Description 实例化一个二叉树公共类
 * @Author xuery
 * @Date 2019/1/4 16:15
 * @Version 1.0
 */
public class GenerateBinaryTreeUtil {

    private GenerateBinaryTreeUtil(){

    }

    public static TreeNode generateTreeNode() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        root.left = node2;
        root.right = node3;
        node2.right = node4;
        node3.left = node5;
        node3.right = node6;
        return root;
    }

    public static TreeNode generateBinarySearchTree(){
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        return root;
    }
}
