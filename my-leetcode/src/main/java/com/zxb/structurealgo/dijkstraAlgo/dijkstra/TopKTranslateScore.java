package com.zxb.structurealgo.dijkstraAlgo.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName TopKTranslateScore 极客时间课后44讲课后习题
 * @Description  每个句子是由多个单词组成的，针对每个单词翻译系统会返回一组可选翻译列表，
 * 列表中的分数都不一样，根据多个单词返回的多个列表组合得出分数靠前的k个组合?
 *
 * 比如一个句子 A B两个单词构成
 * A有三种翻译结果得分：A0(8),A1(6),A2(4)
 * B有两种翻译结果得分：B0(7),B1(5)
 *
 * 求top2，共有A0B0(15),A1B0(13),A0B1(13),A1B1(11),A2B0(11),A1B1(9),所以top2为A0B0，A1B0
 * 有技巧，从A0B0开始遍历，每次改变其中一个单词为它的下一个分数最高的翻译入优先队列；然后出队列，按照同样的方法改变组合入队列
 * 这样可以保证之后分值最高的组合一定在队列中
 * @Author xuery
 * @Date 2019/3/22 17:59
 * @Version 1.0
 */
public class TopKTranslateScore {

    public static void main(String[] args) {

        List<Integer> listA = Arrays.asList(8,7,6);
        List<Integer> listB = Arrays.asList(8,6);
        List<Integer> listC = Arrays.asList(9,6,3);
        List<Integer>[] transIn = new ArrayList[3];
        transIn[0] = listA;
        transIn[1] = listB;
//        transIn[2] = listC;

        topKTranslateScore(transIn,3);
    }

    /**
     *
     * @param transIn 数组的每个元素为一个List<Integer>,代表第某个单词的多个取值
     * @return
     */
    public static List<String> topKTranslateScore(List<Integer>[] transIn, int k){

        int[] currIndex = new int[transIn.length]; //记录每个单词当前已经遍历到第几个位置了，初始值都是0
        return null;
    }
}
