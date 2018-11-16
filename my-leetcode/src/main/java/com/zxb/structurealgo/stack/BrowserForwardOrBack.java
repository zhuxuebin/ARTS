package com.zxb.structurealgo.stack;

import java.util.Stack;

/**
 * 描述：
 *  栈实现浏览器前进、后退、跳转新页面中的操作
 * @author xuery
 * @date 2018/11/14
 */
public class BrowserForwardOrBack {

    private Stack<Integer> stackX = new Stack<>();

    private Stack<Integer> stackY = new Stack<>();

    public static void main(String[] args) throws Exception{
        BrowserForwardOrBack bfob = new BrowserForwardOrBack();
        bfob.newPage(1);
        bfob.newPage(2);
        bfob.newPage(3);
        bfob.currentPage();//3

        bfob.back();
        bfob.back();
        bfob.forward();
        bfob.currentPage(); //2

        bfob.newPage(4);
        bfob.back();
        bfob.back();
        bfob.forward();
        bfob.currentPage();//2
    }

    public void currentPage(){
        if(!stackX.empty()){
            System.out.println(stackX.peek());
            return;
        }
        System.out.println("current page is not exist");
    }

    /**
     * Y-->X
     * @return
     * @throws Exception
     */
    public boolean forward() throws  Exception{

        if(stackY.empty()){
            throw new Exception("cannot forward");
        }

        stackX.push(stackY.pop());
        return true;
    }

    /**
     * X-->Y
     * @return
     */
    public boolean back() throws Exception{
        if(stackX.empty()){
            throw new Exception("cannot back");
        }

        stackY.push(stackX.pop());
        return true;
    }

    /**
     *
     * @param pageIndex 新页面的标识，这里直接用数字表示
     * @return
     */
    public void newPage(int pageIndex){

        //清空stackY
        stackY.clear();

        //入栈stackX
        stackX.push(pageIndex);
    }
}
