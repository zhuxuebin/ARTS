package com.zxb.structurealgo.skiplist;

/**
 * 描述：
 *
 * @author xuery
 * @date 2018/12/21
 */
public class MySkipListSample {

    public static void main(String[] args) {
        MySkipList mySkipList = new MySkipList();

        for(int i=1;i<=1024;i++) {
            mySkipList.insert(i);
        }

//        mySkipList.printAll();
//        mySkipList.printAllValueByLevel();
//
//        mySkipList.delete(2);
//        mySkipList.printAll();
//        mySkipList.printAllValueByLevel();

        MySkipList.Node findNode = mySkipList.find(512);
        System.out.println(findNode.toString());
        System.out.println(mySkipList.findCount);
    }
}
