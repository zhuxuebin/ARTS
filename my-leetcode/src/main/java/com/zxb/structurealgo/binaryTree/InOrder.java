package com.zxb.structurealgo.binaryTree;

import java.util.Stack;

/**
 * @ClassName InOrder
 * @Description 二叉树中序遍历
 * @Author xuery
 * @Date 2019/1/4 9:48
 * @Version 1.0
 */
public class InOrder {

    public static void main(String[] args) {
        TreeNode root = GenerateBinaryTreeUtil.generateTreeNode();

        inOrderRecursion(root);
        System.out.println();

        inOrderLoop(root);
    }



    /**
     * 递归法：递归公式+截止条件
     *
     * @param root
     */
    public static void inOrderRecursion(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrderRecursion(root.left);
        System.out.print(root.val + " ");
        inOrderRecursion(root.right);
    }

    /**
     * 非递归法
     * <p>
     * 思路分析：左根右，则每次左子树最先，先找到当前节点的最左子树，经过的左子树不能丢失，所以要进栈
     * 找到当前的最左子树后打印，关键是后续处理：首先当前子树的左子树按照上述逻辑一定是已经遍历完了，当前子树的右子树还没遍历，
     * 将右子树入栈，按照上述方法继续找右子树的最左子树，循环此过程，直至栈为空
     *
     * @param root
     */
    public static void inOrderLoop(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();

        //初始化栈
        TreeNode p = root;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }

        TreeNode q;
        while (!stack.isEmpty()) {
            //出栈栈顶元素-为当前最左子树（相对的概念）
            p = stack.pop();
            System.out.print(p.val+" ");

            //判断当前节点是否有右子树，有则入栈并循环将其左子树入栈
            if(p.right != null){
                stack.push(p.right);
                q = p.right;
                while(q.left != null){
                    stack.push(q.left);
                    q = q.left;
                }
            }
        }
        System.out.println();
    }
}
