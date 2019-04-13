package com.zxb.structurealgo.practice.day01.array;

/**
 * @ClassName DynamicArray
 * @Description 实现一个动态扩容的数组-其实就是List的底层实现
 *
 * 简单：先初始化一个数组，长度固定；当不够用时，新增一个长度为原数组长度两倍的数组，将原数组数据迁移到新数组并回收原数组
 *
 * @Author xuery
 * @Date 2019/4/12 9:41
 * @Version 1.0
 */
public class DynamicArray {

    private int maxSize = 4; //数组最大长度，不一定全部写满了

    private int length = 0; //数组当前元素长度

    int[] arr;

    public static void main(String[] args) {

        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.add(0);
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(3);
        dynamicArray.add(4);
        dynamicArray.add(5);

        dynamicArray.remove(5);
        int value = dynamicArray.get(4);
        System.out.println(value);
    }

    public DynamicArray(){
        arr = new int[maxSize];
    }

    public DynamicArray(int size){
        this.maxSize = size;
        arr = new int[maxSize];
    }

    public void add(int newValue){
        //先判断是否需要扩容
        if (length == maxSize){
            int[] newArr = new int[2*maxSize];
            for(int i=0;i<maxSize;i++){
                newArr[i] = arr[i];
            }
            arr = newArr; //指向新的数组
            maxSize = 2*maxSize;
        }

        arr[length++] = newValue;
    }

    public void remove(int index){
        if(index >= length){
            throw new ArrayIndexOutOfBoundsException("index:"+index+" is out of bounds");
        }
        for(int i=index+1;i<length;i++){
            arr[i-1] = arr[i];
        }
        length--;
    }

    public int get(int index){
        if(index >= length){
            throw new ArrayIndexOutOfBoundsException("index:"+index+" is out of bounds");
        }
        return arr[index];
    }

    public int getLength() {
        return length;
    }
}
