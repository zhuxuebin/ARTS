package com.zxb.structurealgo.heapsort.mergeSmallFiles;

import com.zxb.common.ArrayUtil;
import com.zxb.structurealgo.heapsort.HeapSort;
import com.zxb.structurealgo.heapsort.HeapSortUtil;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName MergeSmallFiles
 * @Description 合并n个有序小文件到一个大文件
 *
 * 注意点:当最小堆堆顶写入大文件后，其对应的文件中没有数据了该怎么处理？ 将最后一个元素替换掉堆顶元素并堆化再将堆顶元素写入大文件，
 * 而不是从其余小文件中再读一个数据放入堆中，因为我们始终要保证堆中最多只能包含某个文件中的一个元素，如果之前有一个，你再写入一个，
 * 也不会改变堆中最小元素的大小
 * @Author xuery
 * @Date 2019/1/11 10:33
 * @Version 1.0
 */
public class MergeSmallFiles {

    public static void main(String[] args) throws Exception{

        List<String> fileList = Arrays.asList("1.txt","2.txt","3.txt");
        String outFile = "out.txt";

        MergeSmallFiles mergeSmallFiles = new MergeSmallFiles();
        mergeSmallFiles.mergeSmallFiles(fileList, outFile);
    }

    public void mergeSmallFiles(List<String> fileNames, String outFile) throws Exception {
        if (CollectionUtils.isEmpty(fileNames)) {
            return;
        }

        int n = fileNames.size();
        BufferedReader[] readers = new BufferedReader[n];
        for (int i = 0; i < n; i++) {
            readers[i] = new BufferedReader(new InputStreamReader(new FileInputStream(fileNames.get(i))));
        }

        //初始化堆数组, 假设每个文件一定有数据
        Element[] elements = new Element[n];
        for (int i = 0; i < n; i++) {
            elements[i] = new Element(i, Integer.parseInt(readers[i].readLine()));
        }

        //构建最小堆, 且要知道堆顶元素来自哪个文件
        HeapSortUtil.buildMinHeap(elements,n);

        //取堆顶元素写入大文件中
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));

        boolean[] fileReadEmpty = new boolean[n]; //true表示对应的文件已经读空

        int currN = n; //当前堆中实际存在的元素个数，0--currN-1
        while(currN > 0) {
            br.write(String.valueOf(elements[0].value));
            currN--;
            br.newLine();

            int fileIndex = elements[0].fileNum; //继续从这个元素中取一个数据替换堆顶元素，并从堆顶往下堆化
            String newValue;
            if ((newValue = readers[fileIndex].readLine()) != null) {
                elements[0] = new Element(fileIndex, Integer.parseInt(newValue));
                currN++;
                HeapSortUtil.minHeapDownHeapify(elements, currN, 0);
            } else {
                //说明fileIndex对应的文件已经读空了 todo xuery
                //(1)如果最小堆数组中还有元素，则重新构建下最小堆（将最后一个元素放到0下标位置并向下堆化）；
                //(2)如果数组中没有元素了，则重新从剩余还有元素的文件中取出元素构建最小堆，重复上述步骤
                fileReadEmpty[fileIndex] = true;
                if(currN > 0){
                    //(1)相当于删除堆顶元素, currN已经提前减1，所以当前最后一个值下标为currN
                    ArrayUtil.swap(elements, currN, 0);
                    HeapSortUtil.minHeapDownHeapify(elements, currN, 0);
                } else {
                    //(2)从还有元素的文件中各取一个值，并建立最小堆
                    int restartIndex = 0; //重新建立最小堆的下标初始值
                    for(int i=0;i<n;i++){
                        String initValue;
                        if(!fileReadEmpty[i] && (initValue = readers[i].readLine()) != null){
                            currN++;
                            elements[restartIndex++] = new Element(i, Integer.parseInt(initValue));
                        }
                    }

                    HeapSortUtil.buildMinHeap(elements, currN);
                }
            }
        }

        br.flush(); //将数据从缓存刷入磁盘
    }
}
