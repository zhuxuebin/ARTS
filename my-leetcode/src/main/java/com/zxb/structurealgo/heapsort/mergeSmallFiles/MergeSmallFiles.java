package com.zxb.structurealgo.heapsort.mergeSmallFiles;

import com.zxb.structurealgo.heapsort.HeapSortUtil;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName MergeSmallFiles
 * @Description 合并n个有序小文件到一个大文件
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
            br.write(elements[0].value);
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
                //如果最小堆数组中还有元素，则重新构建下最小堆（将最后一个元素放到0下标位置并向下堆化）；
                // 如果数组中没有元素了，则重新从剩余还有元素的文件中取出元素构建最小堆，重复上述步骤
                fileReadEmpty[fileIndex] = true;
            }
        }
    }
}
