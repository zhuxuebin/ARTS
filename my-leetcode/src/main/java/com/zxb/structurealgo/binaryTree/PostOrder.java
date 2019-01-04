package com.zxb.structurealgo.binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName PostOrder
 * @Description 二叉树后续遍历
 * @Author xuery
 * @Date 2019/1/4 9:48
 * @Version 1.0
 */
public class PostOrder {

    public static void main(String[] args) {
        TreeNode root = GenerateBinaryTreeUtil.generateTreeNode();

        postOrderRecursion(root);
        System.out.println();

        postOrderLoop(root);
    }

    /**
     * 递归
     * @param root
     */
    public static void postOrderRecursion(TreeNode root){
        if(root == null){
            return;
        }

        postOrderRecursion(root.left);
        postOrderRecursion(root.right);
        System.out.print(root.val+" ");
    }

    /**
     * 循环解法：左右根
     * 思路分析：父节点先入栈，然后右节点再入栈，最后左节点入栈；思路很清晰，但是实现很困难，自己举个列子发现当入完栈之后，并不能直接出"左节点"(这里是相对的)，
     * 因为"左节点"可能有左右子树，那就先不出栈，让右子树入栈，左子树再入栈，什么时候出栈呢？当栈顶元素没有左右子树时出栈，这样没问题。但是这样无法判断
     * 刚才的"左节点"什么时候出栈。。。
     * 推翻，上述思路不行
     *
     * 灵机一动，"左右根"与"根右左"遍历顺序应该是完全相反的，所以可以先求"根右左"再逆序
     * "根右左"可以类比前序遍历的"根左右"，完毕，牛逼,天才
     * @param root
     */
    public static void postOrderLoop(TreeNode root){
        if(root == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode p;
        List<Integer> reverseList = new ArrayList<>();
        while(!stack.isEmpty()){
            p = stack.pop();
            reverseList.add(p.val);

            if(p.left != null){
                stack.push(p.left);
            }
            if(p.right != null){
                stack.push(p.right);
            }
        }

        for(int i=reverseList.size()-1;i>=0;i--){
            System.out.print(reverseList.get(i)+" ");
        }
        System.out.println();
    }
}
