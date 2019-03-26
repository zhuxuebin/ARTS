package com.zxb.structurealgo.dijkstraAlgo.dijkstra;


import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.PriorityQueue;

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

        List<WordScore> listA = new ArrayList<>(Arrays.asList(new WordScore("A0",8),new WordScore("A1",7),new WordScore("A2",6)));
        List<WordScore> listB = new ArrayList<>(Arrays.asList(new WordScore("B0",8),new WordScore("B1",6)));
        List<WordScore> listC = new ArrayList<>(Arrays.asList(new WordScore("C0",7),new WordScore("C1",5)));
        List<WordScore>[] transIn = new ArrayList[3];
        transIn[0] = listA;
        transIn[1] = listB;
        transIn[2] = listC;

        topKTranslateScore(transIn,5);

    }

    /**
     * 这里只是计算句子的topK分数demo例子，实际中还需要打印出topK分数对应的句子
     * 算了还是打印下对应的句子吧
     * @param transIn 数组的每个元素为一个List<Integer>,代表第某个单词的多个取值
     * @return
     */
    public static void topKTranslateScore(List<WordScore>[] transIn, int k){

        PriorityQueue<SentenceScore> priorityQueue = new PriorityQueue<>(new Comparator<SentenceScore>() {
            @Override
            public int compare(SentenceScore o1, SentenceScore o2) {
                //按照分数从大到小排序
                return o2.getSentenceScore() - o1.getSentenceScore();
            }
        });

        //获取第一个入队列的对象，这里假设每个单词至少有一个分数
        List<WordIndex> firstWordIndices = new ArrayList<>();
        for(int i=0;i<transIn.length;i++){
            WordIndex wordIndex = new WordIndex(i,0);
            firstWordIndices.add(wordIndex);
        }
        SentenceScore sentenceScore = new SentenceScore(transIn, firstWordIndices);
        priorityQueue.add(sentenceScore);
        int count = 0;
        while(!priorityQueue.isEmpty() && count < k){
            count++;
            SentenceScore poll = priorityQueue.poll();
            poll.printSentence();
            List<WordIndex> wordIndices = poll.wordIndices;
            //每次将poll中替换一个单词翻译为下一个
            List<WordIndex> preWordIndices = new ArrayList<>();//记录poll中当前wordIndex之前的WordIndex集合
            for(int i=0;i<wordIndices.size();i++){
                //对于当前的wordIndex，重新构建一个SentenceScore
                List<WordIndex> nextWordIndices = new ArrayList<>();
                nextWordIndices.addAll(preWordIndices);//补之前的
                WordIndex wordIndex = wordIndices.get(i);
                preWordIndices.add(wordIndex);
                if(wordIndex.wordScoreIndex < transIn[wordIndex.wordIndex].size()-1){
                    //说明不是当前单词的最后一个分数，则可以构建
                    WordIndex nextWordIndex = new WordIndex(wordIndex.wordIndex, wordIndex.wordScoreIndex+1);
                    nextWordIndices.add(nextWordIndex);
                } else {
                    continue;
                }
                //补之后的
                for(int j=i+1;j<wordIndices.size();j++){
                    nextWordIndices.add(wordIndices.get(j));
                }

                SentenceScore nextSentenceScore = new SentenceScore(transIn,nextWordIndices);
                priorityQueue.add(nextSentenceScore);
            }

        }
    }

    static class WordScore {
        //###初始化要用到
        String transWord; //对应的翻译结果
        int score;        //对应的分数

        public WordScore(String transWord, int score){
            this.transWord = transWord;
            this.score = score;
        }
    }

    static class WordIndex {

        //###哪个单词的哪个翻译下标，入队列的时候要用到
        int wordIndex; //第几个单词
        int wordScoreIndex; //第wordIndex个单词的第wordScoreIndex分数

        public WordIndex(int wordIndex, int wordScoreIndex){
            this.wordIndex = wordIndex;
            this.wordScoreIndex = wordScoreIndex;
        }
    }

    static class SentenceScore{
        List<WordScore>[] transIn;
        List<WordIndex> wordIndices;

        public SentenceScore(List<WordScore>[] transIn, List<WordIndex> wordIndices){
            this.transIn = transIn;
            this.wordIndices = wordIndices;
        }

        public void printSentence(){
            if(CollectionUtils.isNotEmpty(wordIndices)){
                for(WordIndex wordIndex : wordIndices){
                    System.out.print(transIn[wordIndex.wordIndex].get(wordIndex.wordScoreIndex).transWord+" ");
                }
                System.out.println();
            }
        }

        public int getSentenceScore(){
            int sum = 0;
            if(CollectionUtils.isNotEmpty(wordIndices)){
                for(WordIndex wordIndex : wordIndices){
                    sum += transIn[wordIndex.wordIndex].get(wordIndex.wordScoreIndex).score; //加上对应单词的相应的分值
                }
            }
            return sum;
        }
    }
}
