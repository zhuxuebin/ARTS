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
 * 其实还可以使用马拉车算法，但是真是记不住啊--搞定，思路最重要
 *
 * @author 01368080
 * @date 2018/10/30
 */
public class LongestPalindromicSubstring5 {

    private static int count = 0;

    public static void main(String[] args) {
        LongestPalindromicSubstring5 lps = new LongestPalindromicSubstring5();
        //String s = "ibvjkmpyzsifuxcabqqpahjdeuzaybqsrsmbfplxycsafogotliyvhxjtkrbzqxlyfwujzhkdafhebvsdhkkdbhlhmaoxmbkqiwiusngkbdhlvxdyvnjrzvxmukvdfobzlmvnbnilnsyrgoygfdzjlymhprcpxsnxpcafctikxxybcusgjwmfklkffehbvlhvxfiddznwumxosomfbgxoruoqrhezgsgidgcfzbtdftjxeahriirqgxbhicoxavquhbkaomrroghdnfkknyigsluqebaqrtcwgmlnvmxoagisdmsokeznjsnwpxygjjptvyjjkbmkxvlivinmpnpxgmmorkasebngirckqcawgevljplkkgextudqaodwqmfljljhrujoerycoojwwgtklypicgkyaboqjfivbeqdlonxeidgxsyzugkntoevwfuxovazcyayvwbcqswzhytlmtmrtwpikgacnpkbwgfmpavzyjoxughwhvlsxsgttbcyrlkaarngeoaldsdtjncivhcfsaohmdhgbwkuemcembmlwbwquxfaiukoqvzmgoeppieztdacvwngbkcxknbytvztodbfnjhbtwpjlzuajnlzfmmujhcggpdcwdquutdiubgcvnxvgspmfumeqrofewynizvynavjzkbpkuxxvkjujectdyfwygnfsukvzflcuxxzvxzravzznpxttduajhbsyiywpqunnarabcroljwcbdydagachbobkcvudkoddldaucwruobfylfhyvjuynjrosxczgjwudpxaqwnboxgxybnngxxhibesiaxkicinikzzmonftqkcudlzfzutplbycejmkpxcygsafzkgudy";
        String s= "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        //        String s = "babad";
        System.out.println(lps.longestPalindromicSubstring(s));
        System.out.println(s.length());
        System.out.println(count);

    }

    int maxStartIndex = 0, maxLen = 0;

    public String longestPalindromicSubstring(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        for (int i = 0; i < s.length()-1; i++) {
            extendsString(s,i,i);
            extendsString(s,i,i+1);
        }
        return s.substring(maxStartIndex, maxStartIndex+maxLen);
    }

    private void extendsString(String s, int begin, int end){
        count++;
        while(begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)){
            begin--;
            end++;
            count++;
        }
        //注意下这时候：此时begin和end要么越界了，要么指向的字符不相等
        if(maxLen < end - begin - 1){
            maxStartIndex = begin+1;
            maxLen = end-begin-1;
        }
    }
}
