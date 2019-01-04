package com.zxb.structurealgo.binaryTree;

import java.util.Stack;

/**
 * @ClassName PreOrder
 * @Description 二叉树前序遍历
 * @Author xuery
 * @Date 2019/1/4 9:48
 * @Version 1.0
 */
public class PreOrder {

    public static void main(String[] args) {
        TreeNode root = GenerateBinaryTreeUtil.generateTreeNode();

        preOrderRecursion(root);
        System.out.println();

        preOrderLoop(root);
    }

    /**
     * 递归法:递归公式+截止条件
     * @param root
     */
    public static void preOrderRecursion(TreeNode root){

        if(root == null){
            return;
        }

        System.out.print(root.val+" ");
        preOrderRecursion(root.left);
        preOrderRecursion(root.right);
    }

    /**
     * 非递归法：根->[左->右]
     * 根直接打印，然后看左右，如果有左可以直接打印左并将遍历指针指向左，
     * 但是有一个问题这样会丢失掉右子树的遍历，所以需要想办法将右子树先保存下来，但是不打印
     * 可以借助栈保存：每次当前父节点打印完，先将右子树入栈，左子树再入栈（保证左子树比右子树先出栈，先打印）
     * 然后每次取栈顶元素作为当前父节点重复上述步骤，直至栈为空，完毕
     *
     * 分析思路很重要：这里的关键点在于左子树先于右子树打印，所以左子树需要后入栈，右子树先入栈
     * @param root
     */
    public static void preOrderLoop(TreeNode root){
        if(root == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        TreeNode p;
        while(!stack.isEmpty()){
            p = stack.pop();
            System.out.print(p.val+" ");
            if(p.right != null){
                stack.push(p.right);
            }
            if(p.left != null){
                stack.push(p.left);
            }
        }
        System.out.println();
    }
}
