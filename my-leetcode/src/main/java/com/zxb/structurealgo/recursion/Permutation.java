package com.zxb.structurealgo.recursion;

import com.zxb.leetcode.sort.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Permutation
 * @Description 全排列
 * @Author xuery
 * @Date 2019/1/7 19:17
 * @Version 1.0
 */
public class Permutation {

    private List<List<Integer>> permuteResult = new ArrayList<>();

    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        int[] nums = new int[]{1,2};

        permutation.permute(nums);

        for(List<Integer> list : permutation.permuteResult){
            System.out.println(list);
        }
    }


    /**
     * 这里假设nums中的元素没有重复
     * 全排列思路分析：
     * 递归式：f(n) = n个不同的f(n-1)，代表第n位保持不变时（n种可能），求解剩余n-1位的全排列
     * 递归截止条件f(1), 实际写代码可能是f(0),下标从0开始
     * <p>
     * 每次交换第n位与前面某位，然后计算剩余的f(n-1)，对于f(n-1)同理递归
     *
     *
     * todo List<Integer> list1 = Arrays.asList(nums); 因为nums是一个对象，所以后续nums改变的时候，list1也会跟着改变，指针指向内存了解下
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        permuteRecursion(nums, nums.length - 1);
        return permuteResult;
    }

    /**
     * @param nums 数组
     * @param k    考虑前k位，k从nums.length-1开始到0
     */
    public void permuteRecursion(int[] nums, int k) {
        if (k == 0) {
            List<Integer> oneResult = new ArrayList<>();
            for(int i=0;i<nums.length;i++){
                oneResult.add(nums[i]);
            }
            permuteResult.add(oneResult);
            return;
        }

        //以第k位为基准,第k位的前面的数都与第k位交换一次，然后递归求f(k-1)
        for (int i = 0; i <= k; i++) {
            swap(nums, i, k);

            permuteRecursion(nums, k - 1);
            //复原，保证下一次循环时其他数与原来的第k位交换
            swap(nums, i, k);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
