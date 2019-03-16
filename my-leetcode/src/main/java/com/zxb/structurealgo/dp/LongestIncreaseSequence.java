package com.zxb.structurealgo.dp;

/**
 * @Description 给定一个一维数字组成的数组，且数字都不相同
 * 求最长递增子序列长度
 * {2,9,3,6,5,1,7}
 * 最长子序列为{2,3,6,7}或者{2,3,5,7}
 * Created by xuery on 2019/3/16.
 */
public class LongestIncreaseSequence {

    public static void main(String[] args) {
        int[] arr = new int[]{2,9,3,6,5,1,7,6,7};
        LongestIncreaseSequence lis = new LongestIncreaseSequence();
        System.out.println(lis.LISByDp(arr));
    }

    /**
     * 思路分析
     * dp[i]表示以arr[i]结尾的最长子序列（注意必须包含arr[i]且arr[i]为递增序列的最后一个元素）
     * dp[i]可能由dp[0...i-1]中的任何一个得来，当arr[i] > arr[j]（j<i）时,dp[i]=Max(dp[j])+1
     *
     * 时间复杂度O(n^2)，有没有O（n）时间复杂度的，可以再想想？？？
     *
     * 当没有思路时，举例找递推关系
     *
     * 如果采用暴力破解的话时间复杂度非常高
     * @param arr
     * @return
     */
    public int LISByDp(int[] arr){

        if(arr == null || arr.length == 0){
            return 0;
        }

        int n = arr.length;
        int[] dp = new int[arr.length];
        for(int i=0;i<n;i++){
            dp[i] = 1;
        }
        int maxLen = 1;
        for(int i=1;i<n;i++){
            //计算dp[i]
            for(int j=0;j<i;j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
}
