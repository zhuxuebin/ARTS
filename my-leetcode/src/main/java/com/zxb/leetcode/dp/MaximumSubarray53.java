package com.zxb.leetcode.dp;

/**
 * 描述：
 * ================================================
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * ================================================
 * 思路：找到dp[i]与dp[i-1]的递推关系，其中dp[i]代表以arr[i]结尾的最大数据串之和
 * dp[i] = arr[i], dp[i-1] <= 0
 * dp[i] = dp[i-1] + arr[i], dp[i-1] > 0
 *
 * 优化点：dp[i-1]计算出来之后在计算dp[i]用完之后就没用了，所以可以采用两个变量来节约dp数组的空间
 *
 * @author 01368080
 * @date 2018/10/30
 */
public class MaximumSubarray53 {

    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        MaximumSubarray53 maximumSubarray53 = new MaximumSubarray53();
        System.out.println(maximumSubarray53.calMaximumSubarray(arr));
    }

    public int calMaximumSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //将采用数组优化为两个临时变量
        int predp = arr[0], curdp;
        int max = predp;
        for (int i = 1; i < arr.length; i++) {
            curdp = Math.max(predp+arr[i], arr[i]);
            if(curdp > max){
                max = curdp;
            }
            predp = curdp;
        }
        return max;
    }
}
