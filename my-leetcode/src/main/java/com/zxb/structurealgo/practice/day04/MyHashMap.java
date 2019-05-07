package com.zxb.structurealgo.practice.day04;

/**
 * @ClassName MyHashMap
 * @Description 自己实现一个基于链表法解决冲突的散列表
 * @Author xuery
 * @Date 2019/5/6 10:23
 * @Version 1.0
 */
public class MyHashMap<K,V> {

    public static void main(String[] args) {
        MyHashMap<String, String> myMap = new MyHashMap<>();
        myMap.put("1","1");
        myMap.put("2","2");
        myMap.put("3","3");
        myMap.put("4","3");
        myMap.put("5","5");
        myMap.put("5","55");
        myMap.put("6","6");

        System.out.println(myMap.get("2"));

    }


    int N = 4; //这里假设最多只能N个hash槽，实际参考hashMap还要考虑扩容等操作

    MyNode[] myNodes;

    public MyHashMap(){
        myNodes = new MyNode[N];
    }

    //单个节点类，类似于链表
    class MyNode<K,V> {
        int hash;
        K key;
        V value;
        MyNode<K,V> next;
        public MyNode(K key, V value, int hash, MyNode<K,V> next){
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

    }

    public V get(K key){
        int h = key == null ? 0: key.hashCode() % N;
        if(myNodes[h] != null){
            //说明槽中已经有了数据，则遍历链表看能否找到对应数据
            MyNode p = myNodes[h];
            while(p != null){
                if(p.key.equals(key)){
                    return (V)p.value;
                }
                p = p.next;
            }
        }
        return null;
    }

    public void put(K key, V value){
        int h = key == null ? 0:key.hashCode()%N;
        if(myNodes[h] != null){
            //说明槽中已经有了数据，则先看下是否已经有该值了，有了则更新value；没有则插入链表头
            MyNode p = myNodes[h];
            boolean exist = false;
            while(p != null){
                if(p.key.equals(key)){
                    p.value = value;
                    exist = true;
                    break;
                }
                p = p.next;
            }

            if(!exist){
                MyNode q = myNodes[h];
                MyNode newNode = new MyNode(key,value,h,q);
                myNodes[h] = newNode;
            }

        } else{
            myNodes[h] = new MyNode(key,value,h,null);
        }
    }
}
