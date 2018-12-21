package com.zxb.structurealgo.hashtable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author xuery
 * @date 2018/12/21
 */
public class LinkedHashMapSample {

    public static void main(String[] args) {

        //true表示启用LRU算法
        Map<Integer, Integer> linkedHashMap = new LinkedHashMap<Integer, Integer>(10,0.75f, true);

        linkedHashMap.put(3, 11);
        linkedHashMap.put(1, 12);
        linkedHashMap.put(5, 23);
        linkedHashMap.put(2, 22);

        linkedHashMap.put(3, 26);

        linkedHashMap.get(5);

        for (Map.Entry entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
