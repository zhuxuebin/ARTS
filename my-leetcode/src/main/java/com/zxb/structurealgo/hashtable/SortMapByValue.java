package com.zxb.structurealgo.hashtable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：
 *
 * @author xuery
 * @date 2018/12/21
 */
public class SortMapByValue {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("4",2);
        map.put("1", 3);
        map.put("2", 2);
        map.put("3", 1);

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Map<String, Integer> sortedByValueMap = entrySet
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        Map<String, Integer> sortedByKeyMap = entrySet
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2)->e2, LinkedHashMap::new));

        System.out.println(sortedByValueMap);
        System.out.println(sortedByKeyMap);

    }
}
