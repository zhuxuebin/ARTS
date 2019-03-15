package com.zxb.structurealgo.dp;

/**
 * @ClassName LevenshteinDistance
 * @Description 求两个字符串的最短编辑距离，只一个字符串要变成另外一个字符串所需的最小编辑次数（编辑操作包括删除，新增、替换）
 *求差异度
 * @Author xuery
 * @Date 2019/3/15 18:18
 * @Version 1.0
 */
public class LevenshteinDistance {

    public static void main(String[] args) {
        String s1 = "mitcmu";
        String s2= "mtacnu";
        LevenshteinDistance ld = new LevenshteinDistance();
        ld.minDistBacktracking(0,0,s1,s2,0);
        System.out.println(ld.minDist);
    }

    /**
     * 回溯法先搞一波
     * minDistBacktracking(0,0,s1,s2,0);
     * @param i
     * @param j
     * @param s1
     * @param s2
     * @param currDist 当前编辑距离
     */
    int minDist = Integer.MAX_VALUE;
    public void minDistBacktracking(int i, int j, String s1, String s2, int currDist){

        int n1 = s1.length(), n2 = s2.length();
        if(i == n1 - 1){
            currDist += (n2-1-j);
            minDist = Math.min(minDist, currDist);
            return;
        } else if(j == n2-1){
            currDist += (n1-1-i);
            minDist = Math.min(minDist, currDist);
            return;
        }

        if(s1.charAt(i) == s2.charAt(j)){
            minDistBacktracking(i+1,j+1, s1, s2, currDist);
        } else {
            //s1.charAt(i) != s2.charAt(j)
            //(1) 删除s1[i] 或者在s2[j]前面新增s1[i]
            minDistBacktracking(i+1,j,s1,s2,currDist+1);
            //(2) 删除s2[j] 或者在s1[i]前面新增s2[j]
            minDistBacktracking(i,j+1, s1,s2,currDist+1);
            //(3) 替换s1[i]为s2[j] 或者替换s2[j]为s1[i]
            minDistBacktracking(i+1,j+1,s1,s2,currDist+1);
        }
    }

    
}
