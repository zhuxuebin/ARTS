package com.zxb.structurealgo.stack;

import java.util.Stack;
/**
 * 描述：
 * 给定一个运算式，计算其结果
 * 这里只是为了演示栈的使用，所以可以将输入简化为一个字符串数组，假定字符串要么是数字，要么是操作运算符（加减乘除）,要么是小括号。不用自己去解析了
 *
 * @author zzz
 * @date 2018/11/14
 */
public class CalculateOperator {

    public static void main(String[] args) {
//        String[] input = new String[]{"(", "(", "1", "-", "2", ")", "*", "(", "3", "-", "5", ")", "+", "4", ")", "/", "2"};

        String[] input = new String[]{"(","5","+","(","1","+","2",")","*","(","3","-","5",")",")","*","2"};

        System.out.println(input.length);

        CalculateOperator co = new CalculateOperator();
        System.out.println(co.calculateOperator(input));
    }

    public int calculateOperator(String[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }

        Stack<String> operateStack = new Stack<>();
        Stack<Integer> digitStack = new Stack<>();

        for (String s : input) {
            if (isDigit(s)) {
                //数字直接入数字栈
                digitStack.push(Integer.parseInt(s));
            } else if ("(".equals(s)) {
                operateStack.push(s);
            } else {
                while (true) {
                    if (!operateStack.empty()) {
                        int levelMinus = operateLevel(s) - operateLevel(operateStack.peek());
                        if (levelMinus > 0) {
                            //当前操作符优先级高于栈顶操作符则直接入栈
                            operateStack.push(s);
                            break;
                        } else {
                            //否则，将栈顶操作符出栈并取两个数字运算，结果入数字栈；循环此过程
                            popCalculatePush(operateStack, digitStack);
                        }
                    } else {
                        operateStack.push(s);
                        break;
                    }
                }
            }
        }

        //处理栈中残余的操作符
        while (!operateStack.empty()) {
            popCalculatePush(operateStack, digitStack);
        }

        return digitStack.pop();
    }

    private void popCalculatePush(Stack<String> operateStack, Stack<Integer> digitStack) {
        String operator = operateStack.pop();
        if (")".equals(operator)) {
            //需要再出栈一个操作符和(左括号 bug
            //注意：不一定一个操作符连着一个左括号，右括号与左括号中间可能隔着多个运算符，自己运算下上面的第二个input就知道了
            //需要一直出栈知道遇到左括号(
            operator = operateStack.pop();
            while(!"(".equals(operator) && !operateStack.empty()){
                int y = digitStack.pop();
                int x = digitStack.pop();
                int calRes = calculate(x, y, operator);
                digitStack.push(calRes);

                operator = operateStack.pop();
            }

        } else {
            int y = digitStack.pop();
            int x = digitStack.pop();
            int calRes = calculate(x, y, operator);
            digitStack.push(calRes);
        }
    }

    private int calculate(int x, int y, String operator) {
        switch (operator) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                return x / y;
            default:
                return 0;
        }
    }

    private boolean isDigit(String str) {
        try {
            int digit = Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private int operateLevel(String s) {
        switch (s) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case ")":
                return 3;
            default:
                return 0;
        }
    }

}
