package com.zxb.extraalgo;

import com.zxb.leetcode.sort.ArrayUtil;

/**
 * 描述：对应leetcode:189.Rotate Array,不过仔细读题发现是有区别的
 * /**
 * 指定index，将index前后的char数组内容进行交换<br />
 * e.g.
 * <ul>
 * <li>char[] = 'a','l','i','b','a','b','a' index = 4, 返回 'b','a','a','l','i','b','a'</li>
 * <li>char[] = 'a','l','i','b','a','b','a' index = 2, 返回 'b','a','b','a','a','l','i'</li>
 * </ul>
 * <p>
 * 要求时间复杂度O（n）, 空间复杂度O（1）
 * <p>
 * 思路就很简单了，以index为界，分成左右两部分，两部分从末尾开始(从头开始也是ok的)交换直到一端没有元素，
 * 之后缩小范围又分为两部分，继续直至不需要再交换了
 * 有点减治法的意思，时间复杂度为O（n）
 * 不过要好好确定终止条件，多验证几个
 *
 * @author xuery
 * @date 2018/11/28
 */
public class SwapHalfArray {

    public static void main(String[] args) {
        char[] input = {'a','l','i','b','a','b','a'};
        swap(input, 2);
        ArrayUtil.printCharArray(input);
    }

    /**
     * @param input
     * @param index
     */
    public static void swap(char[] input, int index) {
        if (input == null || input.length == 0 || index < 0 || index >= input.length - 1){
            return;
        }

        /**
         * 一分为2: begin...index, index+1...end， begin，end表示边界；这里采用末尾末尾交换
         *   举几个例子就发现了规律：先交换，然后更新范围：begin=begin,end=end-loop
         *   index=index-loop 当前面元素多
         *   index=index      当后面元素多
         *   终止             当前后元素一样多
         *  根据上述分析就可以直接写出代码了
         */
        int begin = 0, end = input.length - 1;
        while(begin < end){
            int leftLen = index-begin+1;
            int rightLen = end-index;
            int loop = Math.min(leftLen, rightLen);
            for(int i=0;i<loop;i++){
                swap(input, index-i, end-i);
            }
            //更新
            if(leftLen == rightLen){
                break;
            } else if(leftLen < rightLen){
                end = end-loop;
            } else{
                end = end - loop;
                index = index - loop;
            }
        }
    }

    private static void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
