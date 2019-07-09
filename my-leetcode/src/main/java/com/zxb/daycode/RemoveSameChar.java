package com.zxb.daycode;

/**
 * @ClassName RemoveSameChar
 * @Description 给定两个字符串：s1,s2,从s1删除掉s2中存在的字符
 * @Author xuery
 * @Date 2019/7/9 19:36
 * @Version 1.0
 */
public class RemoveSameChar {

    public static void main(String[] args) {
        String s1 = "i like the window";
        String s2 = "inw";
        System.out.println(removeSameChar(s1.toCharArray(), s2.toCharArray()));
    }


    public static String removeSameChar(char[] s1, char[] s2) {

        //用s1不在s2中的字符覆盖s1本身，需要一个下标指针index来标识
        //比暴力破解快，暴力破解每次删除一个元素需要将后面的元素整体前移，时间复杂度O(n^3)
        int index = 0;
        for (int i = 0; i < s1.length; i++) {
            boolean shouldRemove = false;
            for (int j = 0; j < s2.length; j++) {
                if(s1[i] == s2[j]){
                   shouldRemove = true;
                }
            }

            if(!shouldRemove){
                s1[index++] = s1[i];
            }
        }

        if(index == 0){
            return "";
        }
        return new String(s1,0,index);
    }
}
