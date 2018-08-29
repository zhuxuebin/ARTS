package com.zxb.leetcode.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 描述：
 * 315. Count of Smaller Numbers After Self
 * 给定一个数组arr[0...n-1], 求一个数组counts[0...n-1], counts[i]表示数组arr中在arr[i]之后小于它的元素个数
 * 如 in:[5,2,6,1]
 * out:[2,1,1,0]
 * <p>
 * 一般的解法直接：直接遍历o(n^2),这个显然不是我们想要的结果
 * 在arr[i]之后小于它的元素个数，这不就是二叉搜索树的左子数个数吗？==>从右往左构建二叉树，每次插入的时候就知道当前元素的左子树个数了
 * 这里还要注意一点：元素有可能是重复的，而二叉搜索树是无重复元素的，这点要考虑好==>
 *
 * @author 01368080
 * @date 2018/8/21
 */
public class CountOfSmallerNumbersAfterSelf315 {

    public static void main(String[] args) {
        int[] arr = new int[]{7,5,2,6,1};
        CountOfSmallerNumbersAfterSelf315 countOfSmallerNumbersAfterSelf315 = new CountOfSmallerNumbersAfterSelf315();
        List<Integer> result = countOfSmallerNumbersAfterSelf315.countOfSmallerNumbersAfterSelf(arr);
        System.out.println(result);
    }

    //二叉搜索树
    private BST root;

    public List<Integer> countOfSmallerNumbersAfterSelf(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new ArrayList<>();
        }
        int n = arr.length;
        Integer[] tmp = new Integer[n];
        for(int i=n-1;i>=0;i--){
            tmp[i] = insert(arr[i]);
        }
        return Arrays.asList(tmp);
    }

    private int insert(int val){
        if(root == null){
            root = new BST(val);
            return 0;
        }
        BST curr = root; //从头开始遍历
        int resCnt = 0;
        while(true){
            if(curr.val > val){
                //1.说明要往左走
                //1.1 更新当前节点的左子数个数
                curr.leftCnt++;
                //1.2 继续还是终结
                if(curr.left != null){
                    curr = curr.left;
                } else {
                    curr.left = new BST(val);
                    return resCnt;
                }
            } else if(curr.val < val){
                //2.说明要往右走
                //2.1 统计
                resCnt += curr.selfCnt + curr.leftCnt;
                //2.2 继续还是终结
                if(curr.right != null){
                    curr = curr.right;
                } else {
                    curr.right = new BST(val);
                    return resCnt;
                }

            } else {
                //3.相等则需更新selfCnt
                //统计，更新selfCnt
                resCnt += curr.leftCnt;//相等时本身不包含哦
                curr.selfCnt++;
                return resCnt;
            }
        }

    }


    //改进版二叉搜索树
    static class BST {
        BST left;
        BST right;
        int val;
        //左子树个数
        int leftCnt;
        //等于当前节点值的元素个数--主要是数组中可能存在重复元素
        int selfCnt;

        public BST(int val) {
            this.val = val;
            this.selfCnt = 1; //第一次初始化为1
        }
    }
}
