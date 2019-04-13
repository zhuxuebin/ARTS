package com.zxb.structurealgo.practice.day01.array;

/**
 * @ClassName SortedArray
 * @Description 实现一个大小固定的有序数组，支持动态增删改查
 * <p>
 * 考察二分法
 * @Author xuery
 * @Date 2019/4/12 10:07
 * @Version 1.0
 */
public class SortedArray {

    private int maxSize = 4; //数组最大长度

    private int length = 0;     //数组当前元素个数

    private int[] arr;

    public static void main(String[] args) {
        SortedArray sortedArray = new SortedArray(16);
        sortedArray.add(4);sortedArray.add(3);sortedArray.add(3);sortedArray.add(3);
        sortedArray.add(2);sortedArray.add(1);sortedArray.add(2);

        sortedArray.printSortedArray();

        sortedArray.remove(3);

        sortedArray.printSortedArray();

        sortedArray.modify(2,5);

        sortedArray.printSortedArray();
    }

    public SortedArray() {
        arr = new int[maxSize];
    }

    public SortedArray(int size) {
        maxSize = size;
        arr = new int[size];
    }

    public void add(int newValue) {
        add(newValue, 1);
    }

    /**
     * @param newValue
     * @param cnt      插入几个newValue值
     */
    public void add(int newValue, int cnt) {
        if(cnt <= 0){
            throw new RuntimeException("cnt cannot be smaller than 1");
        }
        if (length == 0) {
            arr[length++] = newValue;
            return;
        }

        if (length + cnt - 1 == maxSize) {
            throw new ArrayIndexOutOfBoundsException("array cannot add");
        }
        //先利用二分查找查询插入的位置,找到等于或者第一个大于newValue的元素对应的下标
        int begin = 0, end = length - 1;
        int mid = begin + (end - begin) / 2;
        int targetIndex = -1;
        while (begin <= end) {
            if (arr[mid] > newValue) {
                //这个时候有可能找到了
                if (mid - 1 < 0 || mid - 1 >= 0 && arr[mid - 1] <= newValue) {
                    targetIndex = mid;
                    break;
                } else {
                    end = mid - 1;
                }
            } else if (arr[mid] == newValue) {
                targetIndex = mid;
                break;
            } else {
                begin = mid + 1;
            }
            mid = begin + (end - begin) / 2;
        }

        if (targetIndex == -1) {
            //说明所有元素都比newValue小
            while (cnt-- > 0) {
                arr[length++] = newValue;
            }
        } else {
            for (int i = length - 1; i >= targetIndex; i--) {
                arr[i + cnt] = arr[i];
            }
            length += cnt;
            while (cnt-- > 0) {
                arr[targetIndex++] = newValue;
            }
        }
    }

    /**
     * @param removeValue
     * @return 返回删除了几个元素
     */
    public int remove(int removeValue) {

        //分别找到第一个removeValue、最后一个removeValue, 然后全部一起删除
        int begin = 0, end = length - 1;
        int mid = begin + (end - begin) / 2;
        int firstIndex = -1;
        while (begin <= end) {
            if (arr[mid] > removeValue) {
                end = mid - 1;
            } else if (arr[mid] == removeValue) {
                if (mid - 1 < 0 || mid - 1 >= 0 && arr[mid - 1] != removeValue) {
                    //找到了第一个removeValue
                    firstIndex = mid;
                    break;
                } else {
                    end = mid - 1;
                }
            } else {
                begin = mid + 1;
            }
            mid = begin + (end - begin) / 2;
        }

        begin = 0;
        end = length - 1;
        mid = begin + (end - begin) / 2;
        int lastIndex = -1;
        while (begin <= end) {
            if (arr[mid] > removeValue) {
                end = mid - 1;
            } else if (arr[mid] == removeValue) {
                if (mid + 1 > length - 1 || mid + 1 <= length - 1 && arr[mid + 1] != removeValue) {
                    //找到了第一个removeValue
                    lastIndex = mid;
                    break;
                } else {
                    begin = mid + 1;
                }
            } else {
                begin = mid + 1;
            }
            mid = begin + (end - begin) / 2;
        }

        if (firstIndex == -1 || lastIndex == -1) {
            //没有需要删除的
            return 0;
        }

        //删除firstIndex--lastIndex之间的数据
        for (int i = firstIndex; i <= lastIndex; i++) {
            arr[i] = arr[i + lastIndex - firstIndex + 1];
        }

        length -= (lastIndex - firstIndex + 1);

        return lastIndex - firstIndex + 1;
    }

    public void modify(int oldValue, int newValue) {
        //先删除oldValue（可能多个）,再插入newValue（可能多个）
        int removeNum = remove(oldValue);
        if(removeNum > 0) {
            add(newValue, removeNum);
        }
    }

    public void printSortedArray(){
        if(length == 0){
            return;
        }

        for(int i=0;i<length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
