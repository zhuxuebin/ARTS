package com.zxb.tmp;

import java.util.*;

/**
 * @ClassName ListSample
 * @Description TODO
 * @Author xuery
 * @Date 2019/4/15 18:19
 * @Version 1.0
 */
public class ListSample {

    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>(Arrays.asList("1","2"));

        List<String> list2 = new ArrayList<>(Arrays.asList("1","2","3"));

        list2.retainAll(list1);
        System.out.println(list2);

        Deque<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.peekLast();
        queue.poll();
        queue.pollLast();
        System.out.println(queue.size());

        System.out.println(System.currentTimeMillis());
    }
}
