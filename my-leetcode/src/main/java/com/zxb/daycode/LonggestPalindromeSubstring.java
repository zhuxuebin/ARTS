package com.zxb.daycode;

/**
 * @ClassName LonggestPalindromeSubstring
 * @Description TODO
 * @Author xuery
 * @Date 2019/6/19 11:31
 * @Version 1.0
 */
public class LonggestPalindromeSubstring {

    public static void main(String[] args) {
        LonggestPalindromeSubstring lps = new LonggestPalindromeSubstring();
        System.out.println(lps.longestPalindrome("babadada"));
    }

    public String longestPalindrome(String s) {

        if(s == null || s.length() == 0){
            return s;
        }

        //特殊处理下,保证偶数长度和奇数长度处理一致
        StringBuilder sb = new StringBuilder("#");
        for(int i=0;i<s.length();i++){
            sb.append(s.charAt(i)).append('#');
        }

        int n = sb.length();
        int[] dp = new int[sb.length()];

        //sb的首尾不用就是0
        int index = 0; //index代表dp[index]是当前往右延伸最多的下标
        int maxIndex = 0;
        for(int i=1;i<sb.length()-1;i++){
            if(i == 7){
                System.out.println();
            }

            //判断i是否在dp[index]的范围内
            int j = 2*index - i;
            if(i < dp[index]){
                //1. 在，分两种场景
                //1.1 对称点的dp[j]已经抵达index边缘
                if(2*j-dp[j] <= 2*index - dp[index]){
                    dp[i] = dp[index]; //todo bugs
                    int offset = dp[index]-i+1;
                    while(i-offset >= 0 && i+offset < sb.length() && sb.charAt(i-offset) == sb.charAt(i+offset)){
                        dp[i]++;
                        offset++;
                    }
                } else {
                    //1.2 对称点dp[j]未抵达index边缘
                    dp[i] = i+(dp[j]-j);
                }
            } else{
                //2. 不在，则暴力往两边
                dp[i] = i;
                int offset = 1;
                while(i-offset >= 0 && i+offset < sb.length() && sb.charAt(i-offset) == sb.charAt(i+offset)){
                    dp[i]++;
                    offset++;
                }
            }

            //更新index,maxIndex
            if(dp[i] > dp[index]){
                index= i;
            }

            if(dp[i] - i > dp[maxIndex] - maxIndex){
                maxIndex = i;
            }
        }

        //得出结果 2*maxIndex-dp[maxIndex]--dp[maxIndex]
        StringBuilder result = new StringBuilder();
        for(int i=2*maxIndex-dp[maxIndex];i<=dp[maxIndex];i++){
            if(sb.charAt(i) != '#'){
                result.append(sb.charAt(i));
            }
        }

        return result.toString();
    }
}
