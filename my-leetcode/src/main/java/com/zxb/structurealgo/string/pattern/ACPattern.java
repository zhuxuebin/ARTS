package com.zxb.structurealgo.string.pattern;

import java.util.*;

/**
 * @ClassName ACPattern
 * @Description AC自动机，用于在一组模式字符串中匹配某个主串的所有连续子串是否存在于模式字符串组中
 * <p>
 * 这里还是假设只有a-z字符
 * <p>
 * 两个主要步骤：
 * 1. 将多个模式串构成AC自动机
 * - 构建Trie字典树
 * - 构建fail指针
 * 2. 在AC自动机上匹配主串
 * @Author xuery
 * @Date 2019/2/22 13:51
 * @Version 1.0
 */
public class ACPattern {

    class AcNode {
        char data;
        AcNode[] children = new AcNode[26];
        boolean isLastChar;
        int len = -1; //isLastChar为true的时候，记录当前模式串的长度
        AcNode fail; //失败指针

        public AcNode(char data) {
            this.data = data;
        }
    }

    private AcNode root = new AcNode('/');

    public static void main(String[] args) {
        ACPattern acPattern = new ACPattern();

        List<String> strArr = Arrays.asList("abcd", "bcd", "c");
        for (int i = 0; i < strArr.size(); i++) {
            acPattern.buildTrie(strArr.get(i));
        }

        acPattern.buildFailurePointer();

        acPattern.match("bcd");

        System.out.println();
    }

    /**
     * 1. 构建Trie树
     */
    public void buildTrie(String newStr) {
        AcNode p = root;
        for (int i = 0; i < newStr.length(); i++) {
            AcNode matchChild = p.children[newStr.charAt(i) - 'a'];
            if (matchChild == null) {
                AcNode newNode = new AcNode(newStr.charAt(i));
                matchChild = p.children[newStr.charAt(i) - 'a'] = newNode;
            }
            p = matchChild;
        }
        p.isLastChar = true;
        p.len = newStr.length();
    }

    /**
     * 2. 构建fail指针
     * 定义：以当前字符结尾的字符串的后缀与所有模式串的前缀的最大公共子串，则当前字符的fail指针指向模式串最大公共前缀子串的尾字符
     * 按层构建，下一层的fail指针由上一层得来：
     * 遍历到某一层的字符时，它的fail指针一定指向上一层或者上上层直到root
     * <p>
     * 规律：当前为p，假设它的下一节点为pc=p.children[data-'a'], 若pc != null求pc的fail指针？
     * 则pc.fail依赖q=p.fail,看q.children[data-'a']是否存在，存在则pc.fail = q.children[data-'a'],
     * 不存在则继续递归q=q.fail,直到q为null，最后没找到则pc.fail = root;
     */
    public void buildFailurePointer() {

        root.fail = null;

        //按层遍历，借助队列
        Queue<AcNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.poll();
            for (int i = 0; i < 26; i++) {
                AcNode pc = p.children[i];
                if (pc == null) {
                    continue;
                }

                AcNode q = p.fail;
                if (q == null) {
                    pc.fail = root;
                } else {
                    while (q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                }

                if (q == null) {
                    pc.fail = root;
                }

                queue.add(pc);
            }
        }
    }

    /**
     * 3. 开始匹配
     */
    public void match(String text) {
        AcNode p = root;

        for (int i = 0; i < text.length(); i++) {
            int idx = text.charAt(i) - 'a';
            while(p.children[idx] == null && p != root){
                p = p.fail; //失败指针发挥作用的地方
            }

            p = p.children[idx];
            if(p == null){
                p = root; //没有匹配到则从头开始匹配
            }
            AcNode tmp = p;
            while(tmp != root){
                if(tmp.isLastChar){
                    int pos = i-tmp.len+1;
                    System.out.println("匹配起始下标 "+pos+";长度 "+tmp.len);
                }
                tmp = tmp.fail;
            }

        }
    }
}
