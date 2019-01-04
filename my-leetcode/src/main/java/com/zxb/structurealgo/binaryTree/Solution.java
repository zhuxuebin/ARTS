package com.zxb.structurealgo.binaryTree;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author xuery
 * @Date 2019/1/4 19:42
 * @Version 1.0
 */
class Solution {

    public static void main(String[] args) {
        TreeNode root = GenerateBinaryTreeUtil.generateBinarySearchTree();

        Solution solution = new Solution();
        solution.deleteNode(root,3);
        LevelOrder.levelOrder(root);
    }

    /**
     *三种场景：先不考虑重复元素
     *1. 删除的节点没有左右子树，则直接将其父节点对应的指针置为空，所以这里遍历的时候要记录父节点
     *2. 删除的节点只有一个子树，则将其父节点指向它的这个子树就可以了
     *3. 删除的节点有两个子树，有两种方法：一种是找到删除节点左子树中的最右子树，或者找到节点右子树的最左子树，
     *比如找到最右子树之后，用它代替要删除的元素，然后删除最右子树（删除的方法为上述的1,2，不可能是3，是3则与我们所要找的不符合）
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }

        //先找到key
        TreeNode p = root;
        TreeNode parent = null; //删除节点的父节点,初始值为root的父节点null
        while(p != null){
            if(p.val == key){
                break;
            } else if(p.val > key){
                parent = p;
                p = p.left;
            } else {
                parent = p;
                p = p.right;
            }
        }

        //没找到
        if(p == null){
            return root;
        }

        //有一个特例，当删除的刚好是root节点时，要特殊处理下
        //如果此时root有左右子树则不用管，只有一个子树或者没有子树则要更新root节点
        if(parent == null){
            if(root.left != null && root.right != null){
                //do nothing, continue to below operation
            } else {
                //root指向其非空的左或者右子树或者空
                TreeNode notNullChild = root.left != null ? root.left:root.right;
                root = notNullChild;
                return root;
            }
        }


        if(p.left != null && p.right != null){
            //3.1找到p左侧的最右子树且记录它的父节点，后续删除要用
            TreeNode leftMaxParent = p;
            TreeNode leftMax = p.left;
            if(leftMax.right != null){
                leftMaxParent = leftMax;
                leftMax = leftMax.right;
            }
            //3.2用找到的左侧的最右子树的值代替当前删除节点p的值
            p.val = leftMax.val;
            //3.3 删除leftMax 回到1,2两种场景
            parentNodePointToChildNode(leftMaxParent, leftMax);
        } else {
            parentNodePointToChildNode(parent,p);
        }

        return root;
    }

    //parent为p的父节点
    private void parentNodePointToChildNode(TreeNode parent, TreeNode p){
        if(p.left != null || p.right != null){
            //2. 将父节点对应指针指向p.left或者p.right完毕
            TreeNode notNullNode = p.left != null ? p.left:p.right;
            if(parent.left == p){
                parent.left = notNullNode;
            } else {
                parent.right = notNullNode;
            }
        } else {
            //1. 将父节点对应指针指向null即可
            if(parent.left == p){
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
    }
}