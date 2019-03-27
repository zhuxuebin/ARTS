package com.zxb.structurealgo.bitmap;

/**
 * @ClassName BitMap
 * @Description 位图实现
 * <p>
 * 基本思路：用一个很大的bit数组存储，对应位置为1则其对应的下标数值存在
 * @Author xuery
 * @Date 2019/3/27 10:22
 * @Version 1.0
 */
public class BitMap {

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(1000000000);
        int hundredMillion = 100000000;
        int tenMillion = 10000000;
        int million = 1000000;
        bitMap.set(hundredMillion);
        bitMap.set(tenMillion);
        bitMap.set(million);

        System.out.println(bitMap.get(hundredMillion));
        System.out.println(bitMap.get(tenMillion));
        System.out.println(bitMap.get(million));

        System.out.println(bitMap.get(hundredMillion+1));
    }

    int nbits;     //总共申请多少bit的空间，如果每个数据的范围为0-1亿，则一般申请10亿bit
    char[] bitmap; //这里用char数组，由于char为两个字节=16bit，一个元素就可以16个数据；
    // 当然也可以用int，或者boolean更直接，但是某些编程语言boolean类型不一定只占1位

    public BitMap(int nbits) {
        this.nbits = nbits;
        bitmap = new char[nbits / 16 + 1]; //多加1，因为nbits/16可能存在余数
    }

    public void set(int k) {
        int dividerNum = k / 16;
        int modNum = k % 16;
        //锁定下标为dividerNum的char数组下标,将其中的数的二进制表示法的第modNum置为1
        bitmap[dividerNum] |= 1 << modNum;
    }

    public boolean get(int k) {
        int dividerNum = k / 16;
        int modNum = k % 16;
        //判断bitmap[dividerNum]的第modNum位是否为1
        return ((bitmap[dividerNum] >> modNum) & 0x01) != 0;
    }
}
