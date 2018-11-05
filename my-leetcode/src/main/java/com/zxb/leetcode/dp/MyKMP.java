package com.sf.algo.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 01368080 on 2017/11/16.
 */
public class MyKMP {

    public static void main(String[] args) {
        MyKMP myKMP = new MyKMP();
        System.out.println(myKMP.kmp("BBC ABCDAB ABCDABCDABDE ABCDABD","ABCDABD"));
    }

    /**如果有多个则查找多个
     * @param source 原字符串
     * @param dest   需要匹配的字符串
     * @return
     */
    public List<Integer> kmp(String source, String dest) {
        //做非空判断 todo
        List<Integer> result = new ArrayList<>();
        int sourceLen = source.length();
        int destLen = dest.length();
        int[] partMatchArr = maxPrefixSuffix(dest);
        int start = 0; //source开始匹配的位置
        while(start+destLen <= sourceLen) {
            int matchLen = 0;
            for (int i = start; i < start+destLen; i++) {
                if(dest.charAt(i-start) != source.charAt(i)){
                    break;
                }
                matchLen++;
            }
            if(matchLen != destLen){
                if(matchLen != 0) {
                    start = start + (matchLen - partMatchArr[matchLen - 1]);
                }else{
                    start++; //一个都没匹配到
                }
            } else {
                //继续找，还是找到一个就可以了
                result.add(start);
                start += destLen;
            }
        }
        return result;
    }

    /**
     * 计算“部分匹配值”
     *
     * @param
     * @return
     */
    private static int[] maxPrefixSuffix(String s) {
        int[] result = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            String subStr = s.substring(0, i + 1);
            //计算subStr的最大前缀和后缀相同数
            for (int j = 1; j < subStr.length(); j++) {
                String prefix = subStr.substring(0, j);
                String suffix = subStr.substring(subStr.length() - j, subStr.length());
                if (prefix.equals(suffix)) {
                    result[i] = j;
                }
            }
        }
        return result;
    }
}
