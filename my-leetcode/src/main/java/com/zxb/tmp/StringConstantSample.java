package com.zxb.tmp;

/**
 * @ClassName StringConstantSample
 * @Description TODO
 * @Author xuery
 * @Date 2019/6/19 20:31
 * @Version 1.0
 */
public class StringConstantSample {

    public static void main(String[] args) {
        String str1 = "abc"; //引用指向的是常量池中的"abc"
        String str2 = new String("abc"); //指向堆中的对象
        String str3 = str2.intern(); //指向常量池中的"abc"

        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str1 == str3);

        //你只要记住intern就是指向常量池中的对象，即使在堆上new了一个对象，也会因为没有指向它而被回收
        String s1 = new String("a").intern();
        String s2 = new String("a").intern();
        System.out.println(s1 == s2);
    }
}
