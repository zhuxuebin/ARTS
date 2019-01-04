package com.zxb.structurealgo.binaryTree;

/**
 * @ClassName BinaryTreeHeight
 * @Description 求二叉树的高度
 *
 * 递归法
 * @Author xuery
 * @Date 2019/1/4 17:30
 * @Version 1.0
 */
public class BinaryTreeHeight {

    public static void main(String[] args) {
        TreeNode root = GenerateBinaryTreeUtil.generateTreeNode();
        System.out.println(binaryTreeHeight(root));
    }

    /**
     * 递归法求树高
     * 或者也可以按层遍历求
     * @param root
     * @return
     */
    public static int binaryTreeHeight(TreeNode root){
        if(root == null){
            return -1;
        }

        return Math.max(binaryTreeHeight(root.left), binaryTreeHeight(root.right))+1;
    }
}
