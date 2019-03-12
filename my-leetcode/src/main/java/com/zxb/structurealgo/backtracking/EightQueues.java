package com.zxb.structurealgo.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName EightQueues
 * @Description 八皇后问题  leetcode:51. N-Queens
 * 问题描述：8*8的矩阵，每行放一个数，保证同一行、同一列、同一对角线不能存在两个数及以上，只能存在一个数
 *
 * 回溯法有点类似于将所有情况都试一遍，不过在试的过程中发现条件不符的情况可以马上停止，及时止损
 *
 * 回溯算法的核心：枚举+适当减枝
 * @Author xuery
 * @Date 2019/3/4 12:51
 * @Version 1.0
 */
public class EightQueues {

    public static void main(String[] args) {
        EightQueues eightQueues = new EightQueues();
        int[] rows = new int[8];
        System.out.println(rows.length);
        eightQueues.eightQueues(0,rows);

    }

    /**
     * 思路：一行一行放，每次放的时候8个位置都放，并判断是否符合条件，符合条件的继续往下走，
     * 不符合条件的就放弃，知道8个位置全部放完后找到满足条件的
     * @param row 当前放第几行
     * @param rows 下标代表第几行，值代表对应的行哪一列放了数
     */
    public void eightQueues(int row, int[] rows){

        if(row >=8){
            //说明已经找到符合条件的了,打印出来
            printRows(rows);
        }

        //当前行row的每个位置都放一下
        for(int col=0; col < 8;col++){
            if(isValid(row,col,rows)){
                rows[row] = col; //把当前在第row行符合条件放入的列，都试一下
                eightQueues(row+1,rows);
            }
        }
    }

    /**
     * 判断在第row行第col列放入一个数是否符合条件
     * @param row
     * @param col
     * @param rows
     * @return
     */
    public boolean isValid(int row, int col, int[] rows){

        //看列, 看对角线
        int k = 1;
        for(int i=row-1;i>=0;i--){
            if(rows[i] == col){
                return false;
            }

            if(col-k >= 0 && rows[i] == col-k ){
                return false;
            }

            if(col+k < 8 && rows[i] == col + k){
                return false;
            }

            k++;
        }

        return true;
    }

    public void printRows(int[] rows){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(rows[i] == j){
                    System.out.print("a ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }
}
