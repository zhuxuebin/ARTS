package com.zxb.structurealgo.heapsort.mergeSmallFiles;

/**
 * @ClassName Element
 * @Description 合并多个文件的元素
 * @Author xuery
 * @Date 2019/1/11 20:48
 * @Version 1.0
 */
public class Element {

    private int fileNum; //哪个文件

    private int value;  //对应文件中的值

    public int getFileNum() {
        return fileNum;
    }

    public void setFileNum(int fileNum) {
        this.fileNum = fileNum;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
