package com.zxb.structurealgo.skiplist;

/**
 * 描述：SkipList测试一下
 *
 * @author xuery
 * @date 2018/12/21
 */
public class SkipListSample {

    public static void main(String[] args) {
        SkipList skipList = new SkipList();

        for (int i = 1; i <= 1024; i++) {
            skipList.insert(i);
        }

        skipList.printAll();
        skipList.printAllValueByLevel();

        //测试发现基本可以达到logn的查找速度
        SkipList.Node findNode = skipList.find(512);
        System.out.println(skipList.findCount);
    }
}
