package com.zxb.structurealgo.string.pattern;

/**
 * @ClassName BFPattern
 * @Description 字符串匹配之暴力匹配算法-Brute Force
 * @Author xuery
 * @Date 2019/2/17 16:58
 * @Version 1.0
 */
public class BFPattern {

    public static void main(String[] args) {
        String mainStr = "adsadsabcddasdas";
        String patternStr = "abcd";
        BFPattern bfPattern = new BFPattern();
        int patternStartIndex = bfPattern.bFPattern(mainStr, patternStr);
        System.out.println(patternStartIndex);
    }

    /**
     *
     * @param mainStr 主串
     * @param patternStr 模式串
     * @return
     */
    public int bFPattern(String mainStr, String patternStr){
        if(mainStr == null || mainStr.length() == 0 || patternStr == null || patternStr.length() == 0 || mainStr.length() < patternStr.length()){
            return -1;
        }

        int n = mainStr.length(), m = patternStr.length();

        for(int i=0;i<=n-m;i++){
            //mainStr取i--i+m-1与patternStr匹配
            if(mainStr.substring(i,i+m).equals(patternStr)){
                return i;
            }
        }

        return -1;
    }
}
