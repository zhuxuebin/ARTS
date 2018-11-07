package com.zxb.leetcode.dp;

/**
 * 描述：5. Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * <p>
 * Example 2:
 * Input: "cbbd"
 * Output: "bb"
 * <p>
 * 解题思路：简单粗暴的算法，以每一位为中心向两边扩展，O(n^2)时间复杂度
 * 注意点：以一位为中心向两边扩展则为奇数个字母，但也可能以两位相等的往外扩展则为偶数个字母
 * <p>
 * <p>
 * 马拉车算法：参考：https://www.jianshu.com/p/2062ae43d79a
 *
 * @author 01368080
 * @date 2018/10/30
 */
public class LongestPalindromicSubstring5Manchester {

    private static int count = 0;

    public static void main(String[] args) {
        LongestPalindromicSubstring5Manchester lps = new LongestPalindromicSubstring5Manchester();
        //1、对于下面这个字符串manchester算法没什么优势，因为回文字符串普遍较短
        //String s = "ibvjkmpyzsifuxcabqqpahjdeuzaybqsrsmbfplxycsafogotliyvhxjtkrbzqxlyfwujzhkdafhebvsdhkkdbhlhmaoxmbkqiwiusngkbdhlvxdyvnjrzvxmukvdfobzlmvnbnilnsyrgoygfdzjlymhprcpxsnxpcafctikxxybcusgjwmfklkffehbvlhvxfiddznwumxosomfbgxoruoqrhezgsgidgcfzbtdftjxeahriirqgxbhicoxavquhbkaomrroghdnfkknyigsluqebaqrtcwgmlnvmxoagisdmsokeznjsnwpxygjjptvyjjkbmkxvlivinmpnpxgmmorkasebngirckqcawgevljplkkgextudqaodwqmfljljhrujoerycoojwwgtklypicgkyaboqjfivbeqdlonxeidgxsyzugkntoevwfuxovazcyayvwbcqswzhytlmtmrtwpikgacnpkbwgfmpavzyjoxughwhvlsxsgttbcyrlkaarngeoaldsdtjncivhcfsaohmdhgbwkuemcembmlwbwquxfaiukoqvzmgoeppieztdacvwngbkcxknbytvztodbfnjhbtwpjlzuajnlzfmmujhcggpdcwdquutdiubgcvnxvgspmfumeqrofewynizvynavjzkbpkuxxvkjujectdyfwygnfsukvzflcuxxzvxzravzznpxttduajhbsyiywpqunnarabcroljwcbdydagachbobkcvudkoddldaucwruobfylfhyvjuynjrosxczgjwudpxaqwnboxgxybnngxxhibesiaxkicinikzzmonftqkcudlzfzutplbycejmkpxcygsafzkgudy";
        //2、下面这个字符串manchester算法优势就很大，可以很好的利用已有的回文字符串信息
        String s= "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        //        String s = "cabadabae";
        System.out.println(lps.longestPalindromicSubstring(s));
        System.out.println(s.length());
        System.out.println(count);
    }

    public String longestPalindromicSubstring(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        /**
         * 第一步：特殊处理下字符串,处理成奇数个字符串
         */
        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append("#");
        }
        String calS = sb.toString();

        int maxLen = 0;
        int maxIndex = 0;

        /**
         * 第二步：一个新数组记录以i为中心的最大字符字串的长度--dp[i]
         * 一个变量记录当前最右延伸的下标i--index
         * dp[i]向右延伸到i+dp[i]
         */
        int[] dp = new int[calS.length()];
        int index = 0;
        for (int i = 1; i < calS.length() - 1; i++) {

            //先看i是否在dp[index]+index范围内
            int rightStart, leftStart;
            if(dp[index] + index > i){
                //则可以利用关于index对称的左侧，j=2*index-i,dp[j],看j-dp[j]与index-dp[index]的大小关系
                int j = 2*index - i;
                if(j- dp[j] > index -dp[index]){
                    //未超过index延伸的最左边
                    dp[i] = dp[j];
                    count++;
                } else{
                    //dp[i] = dp[j]; //【bug1】：dp[i]初始值不是dp[j],dp[j]也是可能超越index左边界往外延伸的
                    dp[i] = dp[index]+index-i; //i到index最右边的距离
                    //dp[i]需要从index延伸的最右边开始（不用包括最右边界）匹配
                    rightStart = dp[index] + index + 1;
                    leftStart = 2*i - rightStart;
                    count++;
                    while(rightStart < calS.length() && leftStart >= 0 && calS.charAt(leftStart) == calS.charAt(rightStart)){
                        dp[i]++;
                        rightStart++;
                        leftStart--;
                        count++;
                    }
                    //更新index
                    index = i;
                }
            } else{
                //暴力往两边扩展
                rightStart = i+1;
                leftStart = i-1;
                count++;
                while(rightStart < calS.length() && leftStart >= 0 && calS.charAt(leftStart) == calS.charAt(rightStart)){
                    dp[i]++;
                    rightStart++;
                    leftStart--;
                    count++;
                }
                //更新index
                index = i;
            }
            //更新最大长度
            if(dp[i] > maxLen){
                maxIndex = i;
                maxLen = dp[i];
            }
        }
        /**
         * 因为做了特殊处理，所以需要特殊处理回到原字符串,假设起点为下标为x,则x=maxIndex-maxLen/2, 举例子maxLen等于奇数或者偶数分别验证下
         */
        int startIndex = (maxIndex - maxLen)/2;

        return s.substring(startIndex, startIndex + maxLen);
    }

}
