package com.zxb.structurealgo.string.pattern;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName TriePattern
 * @Description 前缀树/字典树
 * 用于快速检索字符串集合中是否存在某个字符串或者前缀包含该字符串
 * <p>
 * <p>
 * 1. 构建Trie树
 * <p>
 * 2. 查询(全匹配和前缀匹配上都返回)--实际中这里可能涉及到一个排序算法，只返回最匹配的一些数据
 * <p>
 * 使用场景：搜索框提示功能，idea的提示功能等等
 *
 * 删除也相对简单，先看能否找到该字符串，找到了直接将它尾字符对应的TrieNode.isLastChar置为false即可
 * @Author xuery
 * @Date 2019/2/21 9:52
 * @Version 1.0
 */
public class TriePattern {

    private final TrieNode root = new TrieNode('/');

    class TrieNode {
        char data;
        /**
         * 这里假设值包含26个小写字母a-z，所以可以采用数组,
         * 如果字符集是不确定的，则可能需要采用：链表，动态有序数组，跳表，红黑树（主要考虑要加速查找速度）
         */
        TrieNode[] children = new TrieNode[26];

        //是否是结尾字符, 结尾字符指的是一个字符串的结尾，由于某些字符串可能是某些字符串的前缀
        // todo 所以结尾字符不一定是叶子节点
        boolean isLastChar = false;

        public TrieNode(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        TriePattern triePattern = new TriePattern();
        List<String> strArr = Arrays.asList("abc","dabc","abcder","abcdf","abcg","abdfe","abcdsfdssd","sadasabcde");
        String target = "abc";
        for(int i=0;i<strArr.size();i++){
            triePattern.buildTrie(strArr.get(i));
        }

        List<String> result = triePattern.findMatchString(target);

        System.out.println(result);
    }

    /**
     * @param newStr
     */
    public void buildTrie(String newStr) {
        TrieNode p = root;
        for (int i = 0; i < newStr.length(); i++) {
            TrieNode matchChild = p.children[newStr.charAt(i) - 'a'];
            if (matchChild == null) {
                TrieNode newNode = new TrieNode(newStr.charAt(i));
                matchChild = p.children[newStr.charAt(i) - 'a'] = newNode;
            }
            p = matchChild;
        }
        p.isLastChar = true;
    }

    private List<String> resultList = new ArrayList();

    /**
     * 去前缀树中查找字符串，并返回符合条件的精确匹配或者前缀为查找字符串的字符串列表
     * 其实就是给定一组字符串，查出前缀是目标字符串的字符串集合
     * @param target
     * @return
     */
    public List<String> findMatchString(String target) {
        TrieNode p = root;

        TrieNode child;
        for (int i = 0; i < target.length(); i++) {
            child = p.children[target.charAt(i) - 'a'];
            if (child == null) {
                return null;
            }
            p = child;
        }
        //说明找到了target的最后一个字符所在的Node
        //1. 是结尾字符，说明存在精确匹配
        if (p.isLastChar) {
            resultList.add(target);
        }
        //2. 下面开始遍历所有前缀为target的字符串,从p的children一直往下遍历，只要遇到isLastChar==true的就是符合条件的
        getAllPrefixString(p, target);

        return resultList;
    }

    /**
     * @param currNode 当前node,遍历它的children
     * @param currStr  已经确定了的字符串前缀
     */
    private void getAllPrefixString(TrieNode currNode, String currStr) {

        for (int i = 0; i < 26; i++) {
            TrieNode child = currNode.children[i];
            if (child != null) {
                if(child.isLastChar){
                    resultList.add(currStr+child.data);
                }
                getAllPrefixString(child, currStr + child.data);
            }
        }
    }
}
