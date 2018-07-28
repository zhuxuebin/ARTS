package com.zxb.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 思路：其实与UniqueBST是类似的思路，确定基准i,然后双层循环其左右，区别就是一个是求个数，一个是要把结果也返回
 *
 * 递归两个重要条件：
 * 1.基线条件：截止条件
 * 2.子问题分解
 * @author 01368080
 * @date 2018/7/28
 */
public class UniqueBST2 {

    public static void main(String[] args) {
        List<TreeNode> resList = generateTrees(3);
        System.out.println();
    }

    /**
     * 递归算法,类似Unique BST
     * 一分为2，左右分别采用相同的算法
     */
    public static List<TreeNode> generateTrees(int n) {
        if(n < 1){
            return new ArrayList<>();
        }
        return genTrees(1,n);
    }

    /**
     * tart--end范围求构成的UniqueBST, 子问题分解
     * @param start
     * @param end
     * @return
     */
    public static List<TreeNode> genTrees(int start, int end){

        List<TreeNode> list = new ArrayList<>();

        //1.递归的基线条件
        //这么做的好处在于后续递归处理不用做特殊处理
        if(start > end){
            list.add(null);
            return list;
        }

        if(start == end){
            list.add(new TreeNode(start));
            return list;
        }

        //2.递归的子问题分解,i表示当前的root节点，可以从start到end
        for(int i=start;i<=end;i++){

            //一分为二
            List<TreeNode> left = genTrees(start,i-1);
            List<TreeNode> right = genTrees(i+1,end);

            //这里之所以可以这么写，就是基线条件可以保证left，right一定是非空的
            for(TreeNode lnode : left){
                for(TreeNode rnode : right){
                    TreeNode currRoot = new TreeNode(i);
                    currRoot.left = lnode;
                    currRoot.right = rnode;
                    list.add(currRoot);
                }
            }
        }
        return list;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        this.val = x;
    }
}