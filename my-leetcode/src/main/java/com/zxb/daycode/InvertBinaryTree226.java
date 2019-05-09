package com.zxb.daycode;

/**
 * @ClassName InvertBinaryTree226
 * @Description https://leetcode.com/problems/invert-binary-tree/
 * @Author xuery
 * @Date 2019/5/9 20:13
 * @Version 1.0
 */
public class InvertBinaryTree226 {


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }

        invertTreeRecursion(root);

        return root;
    }

    private void invertTreeRecursion(TreeNode root){
        if(root == null || root.left == null && root.right == null){
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if(root.left != null){
            invertTreeRecursion(root.left);
        }
        if(root.right != null){
            invertTreeRecursion(root.right);
        }
    }
}
