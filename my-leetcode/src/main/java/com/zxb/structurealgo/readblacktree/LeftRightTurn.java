package com.zxb.structurealgo.readblacktree;

import com.zxb.structurealgo.binaryTree.TreeNode;

/**
 * @ClassName LeftRightTurn
 * @Description 左右旋操作
 * 这里直接写个左旋，左旋会了右旋也是一样的
 * @Author xuery
 * @Date 2019/1/7 19:15
 * @Version 1.0
 */
public class LeftRightTurn {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        root.left = node1;
        node1.left=node3;
        node1.right = node2;
        node2.left = node4;
        node2.right = node5;

        //这里node2为node1的right,针对这两个节点做左旋，一般会新增一个父节点指针，这里就简化了，直接已知node的父节点
        leftTurn(root, node1);
        System.out.println();
    }

    /**
     *画图就很好理解了
     * @param parent
     * @param node1 针对node1做左旋，这里假设node1存在右子树，实际可能需要判断
     */
    public static void leftTurn(TreeNode parent, TreeNode node1){
        TreeNode rightNode = node1.right;
        //1.父节点直接指向node1的右子树
        if(parent.left == node1){
            parent.left = rightNode;
        } else {
            parent.right = rightNode;
        }

        //2.保存下右子树的左子树,将右子树的左子树指针指向node1
        TreeNode rightLeftChildNode = rightNode.left;
        rightNode.left = node1;

        //3. node1的右子树指针指向右子树的左子树
        node1.right = rightLeftChildNode;
    }
}
