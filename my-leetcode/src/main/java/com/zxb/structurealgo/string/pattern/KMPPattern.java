package com.zxb.structurealgo.string.pattern;

/**
 * @ClassName KMPPattern
 * @Description 思路很简单，最大化利用"好前缀"，根据"好前缀"的前缀与后缀的最大公共子串来加速移动速度
 * @Author xuery
 * @Date 2019/2/20 13:58
 * @Version 1.0
 */
public class KMPPattern {

    public static void main(String[] args) {
        String main = "BBC ABCDAB ABCDABCDABDE ABCDABD";
        String pattern = "ABCDABD";

        KMPPattern kmpPattern = new KMPPattern();
        System.out.println(kmpPattern.kMPPattern(main,pattern));
    }


    /**
     * 先计算next数组，下标表示"好前缀"的尾字符下标（"好前缀"字符长度-1），值为"好前缀"最长公共前缀的尾字符下标(公共前缀长度-1)
     * 这里直接采用暴力破解法，极客时间上的还没看懂???
     */
    private int[] generateNext(String pattern) {

        int m = pattern.length();
        int[] next = new int[m];

        for (int i = 0; i < m; i++) {
            next[i] = -1;

            //考察每个"好前缀"pattern[0,i],求其后缀与前缀的公共子串的最大长度，即看pattern[j,i]（j=1,...i）是否为pattern[0,i]的前缀
            for (int j = 1; j <= i; j++) {
                if (pattern.substring(j, i + 1).equals(pattern.substring(0, i + 1 - j))) {
                    next[i] = i - j;
                    break;
                }
            }
        }

        return next;
    }

    public int kMPPattern(String main, String pattern) {

        int[] next = generateNext(pattern);

        int n = main.length(), m = pattern.length();

        int j = 0;
        for (int i = 0; i < n; i++) {

            while(j > 0 && main.charAt(i) != pattern.charAt(j)){
                //则考虑"好前缀" pattern[0..j-1]==>next[j-1]=k-1, 令j=k;
                j = next[j-1]+1; //todo 这里一定要记得加1

            }

            if(main.charAt(i) == pattern.charAt(j)){
                j++;
            }

            if(j == m){
                //找到了
                return i-m+1;
            }
        }

        return -1;
    }
}
