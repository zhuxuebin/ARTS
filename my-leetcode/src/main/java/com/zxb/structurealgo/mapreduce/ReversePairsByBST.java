package com.zxb.structurealgo.mapreduce;

/**
 * @ClassName ReversePairsByBST
 * @Description 493. Reverse Pairs
 *
 * 题目描述：求重要逆序对个数，nums[i] > 2*nums[j], (i < j),则nums[i]与nums[j]互为重要逆序对
 *
 * 通过构造二叉搜索树的方法来求重要逆序对
 *
 * 主要思路：
 * 将数组中的元素按照顺序一个一个的插入BST树中，再插入之前可以通过已有节点上的某些属性，求出与当前元素互为重要逆序对的个数，
 * 所以分为两步：1. 遍历到某个元素X时，在已有二叉树中查找与X互为逆序对的个数（也就是X之前的元素有哪些大于它的两倍）
 *              2. 然后将X入BST，并更新相应节点的属性
 *
 * 问题的关键就是节点上需要新增计数属性，查找的时候，
 *              1. 如果遍历到的当前节点比（2*X+1）小，则往右走，这时候与X互为逆序对的一定在右边
 *              2. 如果遍历到的当前节点比（2*X+1）大，则往左走，这时候当前节点及其右边的节点与X都互为逆序对
 *              重复上面两个过程，直到遍历到叶子节点
 *所以，计数属性是指当前节点右边有多少个节点
 * @Author xuery
 * @Date 2019/3/4 10:34
 * @Version 1.0
 */
public class ReversePairsByBST {


    public static void main(String[] args) {
        int[] nums = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};

        ReversePairsByBST reversePairsByBST = new ReversePairsByBST();
        System.out.println(reversePairsByBST.reversePairs(nums));
    }

    int sum = 0;
    BSTNode root = null;
    public int reversePairs(int[] nums){
        if(nums == null || nums.length <= 1){
            return 0;
        }

        sum = 0;
        for(int i=0;i<nums.length;i++){
            sum += search((long)2*nums[i]+1); //这里要注意下
            insert(nums[i]);
        }
        return sum;
    }

    /**
     * 在BST中查找有多少个元素大于等于currVal
     * @param currVal
     * @return
     */
    public int search(long currVal){

        if(root == null){
            return 0;
        }

        BSTNode p = root;
        int cnt = 0;
        while(p!=null){
            if(p.val >= currVal){
                cnt += (1+p.cnt);
                p = p.left;
            } else{
                p = p.right;
            }
        }
        return cnt;
    }

    /**
     * 将当前元素插入到BST中，并更新相关节点的cnt值
     * @param currVal
     */
    public void insert(long currVal){
        if(root == null){
            root = new BSTNode(currVal);
            return;
        }

        BSTNode p = root;
        while(p!=null){
            if(p.val >= currVal){
                //插入到左边
                if(p.left == null){
                    p.left = new BSTNode(currVal);
                    break;
                } else {
                    p = p.left;
                }
            } else{
                //插入到右边, 先更新p的cnt
                p.cnt++;
                if(p.right == null){
                    p.right = new BSTNode(currVal);
                    break;
                } else {
                    p = p.right;
                }
            }
        }
    }


    class BSTNode{
        private long val;
        private int  cnt; //cnt代表当前节点右边有多少个非空节点
        private BSTNode left, right;

        public BSTNode(long val){
            this.val = val;
        }
    }
}
