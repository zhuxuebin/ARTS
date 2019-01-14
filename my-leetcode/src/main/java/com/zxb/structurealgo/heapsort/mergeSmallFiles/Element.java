package com.zxb.structurealgo.heapsort.mergeSmallFiles;

/**
 * @ClassName Element
 * @Description 合并多个文件的元素
 * @Author xuery
 * @Date 2019/1/11 20:48
 * @Version 1.0
 */
public class Element {

    public int fileNum; //哪个文件

    public int value;  //对应文件中的值

    public Element(int fileNum, int value) {
        this.fileNum = fileNum;
        this.value = value;
    }
}
