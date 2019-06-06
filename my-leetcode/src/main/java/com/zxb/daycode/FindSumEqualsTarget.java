package com.zxb.daycode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FindSumEqualsTarget
 * @Description 给定一个二叉树和一个值，查找出二叉树中的路径值之和等于给定值的序列并打印
 * <p>
 * 可能存在多条路径
 * @Author xuery
 * @Date 2019/6/6 9:26
 * @Version 1.0
 */
public class FindSumEqualsTarget {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(8);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        FindSumEqualsTarget findSumEqualsTarget = new FindSumEqualsTarget();
        findSumEqualsTarget.findSumEqualsTarget(root, 11, new ArrayList<>());
        System.out.println(findSumEqualsTarget.result);
    }

    private List<List<Integer>> result = new ArrayList<>();

    public void findSumEqualsTarget(TreeNode root, int target, List<Integer> list) {
        if (root.left == null && root.right == null) {
            //到了叶子节点
            if (target == root.value) {
                list.add(root.value);
                result.add(list);
            }
            return;
        }

        list.add(root.value);
        if (root.left != null) {
            findSumEqualsTarget(root.left, target - root.value, new ArrayList<>(list));
        }
        if (root.right != null) {
            findSumEqualsTarget(root.right, target - root.value, new ArrayList<>(list));
        }
    }
}


class TreeNode {

    int value;

    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }
}
