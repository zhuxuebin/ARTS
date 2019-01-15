package com.zxb.structurealgo.heapsort.medium;

/**
 * @ClassName MediumNumber
 * @Description 求中位数
 *
 * 方法1：直接将所有的数据排序，然后取中间那个数
 * 只适用于静态数据
 *
 * 方法2： 通过大小顶堆实现，大顶堆的堆顶元素元素小于等于小顶堆的堆顶元素，
 * 插入数据时如果小于等于大顶堆堆顶元素，则插入大顶堆，否则插入小顶堆
 * 每次插入完成后，保证大顶堆元素个数与小顶堆元素个数相等，或者大顶堆元素个数比小顶堆多1，不满足则要调整
 *
 * 最后中位数就是大顶堆的堆顶元素
 *
 * 适用于动态数据
 * @Author xuery
 * @Date 2019/1/15 11:41
 * @Version 1.0
 */
public class MediumNumber {

    public static void main(String[] args) {

        //todo xuery 暂时不写了，思路类似，有时间再补回来
    }
}
