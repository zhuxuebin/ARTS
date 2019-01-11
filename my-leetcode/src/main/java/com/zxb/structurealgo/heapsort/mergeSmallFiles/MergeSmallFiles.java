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

        MergeSmallFiles mergeSmallFiles = new MergeSmallFiles();
        mergeSmallFiles.mergeSmallFiles(fileList);
    }

    public void mergeSmallFiles(List<String> fileNames) throws Exception {
        if (CollectionUtils.isEmpty(fileNames)) {
            return;
        }

        int n = fileNames.size();
        InputStream[] inputStreams = new FileInputStream[n];
        for (int i = 0; i < n; i++) {
            inputStreams[i] = new FileInputStream(new File(fileNames.get(i)));
        }

        //初始化堆数组
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = inputStreams[i].read();
        }

        //构建最小堆, 且要知道堆顶元素来自哪个文件
        HeapSortUtil.buildMinHeap(nums,n);

    }
}
