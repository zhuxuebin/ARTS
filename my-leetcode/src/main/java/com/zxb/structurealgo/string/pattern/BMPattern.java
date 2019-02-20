package com.zxb.structurealgo.string.pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BMPattern
 * @Description BM字符串匹配算法，两个重要原则：坏字符规则和好后缀规则
 * <p>
 * 两个规则的作用：加速模式串后移的脚步
 * 取两者移动步数的最大值
 * <p>
 * 坏字符规则必须和好后缀规则一起配合使用：因为某些场景下坏字符规则移动的步数可能为0或者负数，负数时因为在模式串
 * 中找到的坏字符串可能位于当前比较字符的后面
 * 好后缀规则可以单独使用
 * <p>
 * 还是假设主串长度为n, 模式串长度为m
 * <p>
 * （1）坏字符规则：模式串与主串的某个长度为m的子串对齐比较时，从后往前比较，找到第一个匹配不上的字符，主串中对应的字符就是坏字符,假设在模式串中的下标为i
 * 将该坏字符在模式串中从右往左找到第一个坏字符（从右往左找第一个可以保证尽量少的移动，防止错过）
 * 如果找到，假设在模式串中下标为j,则移动(i-j)步; 没找到则j=-1,也是移动(i-j)步，让模式串中的坏字符与主串中的坏字符对齐
 * <p>
 * （2）好后缀规则：
 * 按照坏字符的规则，找到坏字符，则在模式串中坏字符的子字符串就是好后缀
 * 用好后缀{u}去模式串中从右往左找与其第一个匹配的{u*}
 * 找到则将{u*}移动到与主串相匹配的位置
 * 没找到则需要看好后缀的后缀与模式串的前缀是否有重合{v},
 * 有则将模式串滑到与主串{v}对齐的位置
 * 两者都没有则移动m
 * <p>
 * 取上述两步的最大值移动
 * <p>
 * 上面思路还算简单，实际实现有很多技巧，具体参考极客时间-数据结构与算法之美第32讲
 * @Author xuery
 * @Date 2019/2/19 10:00
 * @Version 1.0
 */
public class BMPattern {

    public static void main(String[] args) {
        BMPattern bmPattern = new BMPattern();
        String main = "ecbcbcdcbc";
        String pattern = "dcbc";
        int startIndex = bmPattern.bMPattern(main, pattern);
        System.out.println(startIndex);
    }

    /**
     * 坏字符匹配的时候，可以预处理模式串，采用一个hashMap， k-代表当前字符,v-在模式串中的下标
     * 从前往后遍历，这样可以保证多个相同字符存储的下标是最后一个
     */
    private Map<Character, Integer> generateBc(String pattern) {
        Map<Character, Integer> bcMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            bcMap.put(pattern.charAt(i), i);
        }

        return bcMap;
    }

    /**
     * 构建suffix和prefix数组
     * suffix[i]代表模式串中长度为i的后缀子串与模式串中的相匹配的子串的起始下标（多个匹配时取靠右边的）
     * 举个例子：比如模式串为：ssabcdabc, 则长度为3的后缀字串abc在模式串中匹配的子串起始下标为2, 即suffix[3]=2
     * <p>
     * prefix[i]=true代表模式串中长度为i的后缀子串刚好也是模式串的前缀子串，当suffix[i]==0时，prefix[i]=true
     * 比如abcdabc,则长度为3的后缀子串abc是模式串的前缀子串
     * <p>
     * suffix的构建技巧：pattern[0...i] (i=0,1,...,m-2) 与 pattern[0...m-1]求公共后缀子串
     * i要从0开始求，这样可以保证存在多个相同的公共后缀子串时，会取靠后的那一个
     */
    private int[] generateSuffix(String pattern) {

        int m = pattern.length();
        int[] suffix = new int[m];
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
        }

        for (int i = 0; i < m - 1; i++) {
            //pattern[0..i]与pattern[0...m-1]求公共后缀子串，存在且长度为len, 则suffix[len]=i-len+1
            int j = i, k = m - 1, len = 0;
            while (j >= 0 && pattern.charAt(j--) == pattern.charAt(k--)) {
                len++;
            }
            if (len > 0) {
                suffix[len] = i - len + 1;
            }
        }

        return suffix;
    }


    public int bMPattern(String main, String pattern) {
        int n = main.length(), m = pattern.length();

        Map<Character, Integer> bcMap = generateBc(pattern);

        int[] suffix = generateSuffix(pattern);

        boolean[] prefix = new boolean[m]; //这个其实可以不需要
        for (int i = 0; i < m; i++) {
            prefix[i] = suffix[i] == 0 ? true : false;
        }

        int j = 0; //主串开始匹配位置

        /**
         * 根据bcMap求出坏字符在模式串中的位置决定移动几位
         * 好后缀根据suffix+prefix求出移动几位
         *
         * 上述两者求最大值
         */
        while (j <= n - m) {
            //从右往左匹配pattern[0..m-1]与main[j..j+m-1]
            int badIndex = -1; //坏字符在主串中的下标

            int i = m - 1;
            for (; i >= 0; i--) {
                if (pattern.charAt(i) != main.charAt(i + j)) {
                    //找到了坏字符,记录在主串中的下标
                    badIndex = i + j;
                    break;
                }
            }

            if (badIndex == -1) {
                //说明找到了
                return j;
            }

            int moveStep = 0; //移动步数

            //用坏字符去模式串中匹配，确定移动步数
            Integer badIndexInPattern = bcMap.get(main.charAt(badIndex));
            if (badIndexInPattern == null) {
                moveStep = i+1;  //todo 这里别搞成m了
            } else {
                moveStep = i - badIndexInPattern; //可能<=0
            }

            //根据好后缀pattern[i+1...m-1]确定移动步数
            if (i == m - 1) {
                //说明没有好后缀，则直接采用坏字符规则即可(moveStep一定大于0)
                j += moveStep;
                continue;
            }
            int len = m - 1 - i;
            if (suffix[len] == -1) {
                //说明没有匹配的{u},则找好后缀{u}的后缀子串与模式串的前缀子串的最值
                for (int k = len - 1; k >= 0; k--) {
                    if (prefix[k]) {
                        moveStep = Math.max(moveStep, m - k);
                        break;
                    }
                }
                //todo 如果好后缀的后缀字串没有与模式串的前缀子串所匹配的，则移动m位
                moveStep = m;
            } else {
                //匹配到{u*}，则移动i+1-suffix[len]--画图画图画图
                moveStep = Math.max(moveStep, i + 1 - suffix[len]);
            }

            j += moveStep;
        }

        return -1;
    }
}
