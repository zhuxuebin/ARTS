package com.zxb.structurealgo.stack;

import java.util.*;

/**
 * 描述：
 * 各种括号组成的字符串：[],(),{}, 判断字符串的括号使用是否是合法格式?
 * 举例：{[()]}是合法的，{[}()]不合法
 *
 * @author zzz
 * @date 2018/11/14
 */
public class BracketMatch {

    private static final List<Character> leftBracket = Arrays.asList('(','{','[');

    private static final Map<Character,Character> pairs = new HashMap<>();

    static {
        pairs.put('(',')');
        pairs.put('[',']');
        pairs.put('{','}');
    }

    public static void main(String[] args) {
//        String s = "{[()]}[]";
        String s = "{[}()]";
        BracketMatch bracketMatch = new BracketMatch();
        System.out.println(bracketMatch.isBracketMatch(s));
    }

    public boolean isBracketMatch(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(leftBracket.contains(c)){
                stack.push(c);
            } else {
                if(stack.empty()){
                    return false;
                } else {
                    char cPop = stack.pop();
                    boolean isMatch = isLeftRightMatch(cPop, c);
                    if(!isMatch){
                        return false;
                    }
                }
            }
        }

        return stack.empty();
    }

    private boolean isLeftRightMatch(char left, char right){
        if(pairs.get(left) == right){
            return true;
        }
        return false;
    }
}
