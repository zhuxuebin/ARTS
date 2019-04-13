package com.zxb.structurealgo.practice.day02.recursion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName FullPermutation
 * @Description 全排列
 *
 * 重新实现下
 *
 * 典型的回溯思想
 *
 * 递归思想：a b c
 * 固定住第一个数a，求后面b c的排列组合数（b,c也是类似的方法，固定住b）
 * 然后a与b交换，固定住b
 * 或者a与c交换，固定住c
 *
 *
 * @Author xuery
 * @Date 2019/4/13 15:44
 * @Version 1.0
 */
public class FullPermutation {

    public static void main(String[] args) {
        FullPermutation fullPermutation = new FullPermutation();

        char[] arr = {'a','b','c'};

        fullPermutation.permutation(arr,0);

        System.out.println(fullPermutation.resultSet);
    }

    Set<String> resultSet = new HashSet<>();
    public void permutation(char[] arr, int begin){
        if(arr == null || arr.length == 0){
            return;
        }
        //截止条件,说明到最后一个元素了
        int n = arr.length;
        if(begin >= n){
            resultSet.add(Arrays.toString(arr));
            return;
        }

        for(int i=begin;i<n;i++){
            swap(arr,begin,i);

            permutation(arr,begin+1);

            swap(arr,begin,i);
        }
    }

    private void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
