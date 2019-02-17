package com.zxb.structurealgo.string.pattern;

/**
 * @ClassName RKPattern
 * @Description 引入hash算法，在hash算法选择合理且碰撞概率小的情况下，将时间复杂度降为O（n）
 * @Author xuery
 * @Date 2019/2/17 17:54
 * @Version 1.0
 */
public class RKPattern {

    public static void main(String[] args) {
        String mainStr = "adsadsabcddasdas";
        String patternStr = "abcd";

        RKPattern rkPattern = new RKPattern();
        int patternIndex = rkPattern.rKPattern(mainStr, patternStr);
        System.out.println(patternIndex);
    }

    private static final int PATTERN_MAX_LEN = 10; //匹配串的最大长度

    private static final int[] powArr = new int[PATTERN_MAX_LEN];

    static {
        powArr[0] = 1;
        for (int i = 1; i < PATTERN_MAX_LEN; i++) {
            powArr[i] = powArr[i - 1] * 26;
        }
    }

    /**
     * 参见32将中的RK hash算法设计思路
     * 假设只有a-z的字符串，则采用26进制对m长度的字符串求值hash算法
     * 比如：cbd=2*26^2+1*26^1+3*26^0
     * <p>
     * h(i)表示主串中以i下标开始的m长度的子串的hash值
     * 则相邻m长度子串的hash值有如下递归关系：h(i)={h(i-1)-(mainStr[i]-'a')*26^m-1}*26 + (mainStr[i+m-1]-'a')
     * <p>
     * <p>
     * 思路比结论更重要
     * <p>
     * 还有一个优化点26^m可以先计算出来，然后查表即可
     *
     * @param mainStr    主串
     * @param patternStr 模式串
     * @return
     */
    public int rKPattern(String mainStr, String patternStr) {
        //非法输入略过

        int n = mainStr.length(), m = patternStr.length();

        long patternHash = 0L;
        long preHash = 0L, currHash = 0L;
        for(int i=0;i<m;i++){
            patternHash += (patternStr.charAt(i)-'a')*powArr[m-1-i];
            currHash += (mainStr.charAt(i)-'a')*powArr[m-1-i];
        }

        for (int i = 0; i <= n - m; i++) {
            if(currHash == patternHash && patternStr.equals(mainStr.substring(i,i+m))){
                return i;
            }

            if(i == n-m){
                continue;
            }
            preHash = currHash;
            currHash = (preHash-(mainStr.charAt(i)-'a')*powArr[m-1])*26+(mainStr.charAt(i+m)-'a');
        }

        return -1;
    }
}
