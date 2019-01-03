package com.zxb.structurealgo.binaryTree;

/**
 * @ClassName CatalanVerify
 * @Description 卡特兰数：h(n)=C(2n,n)/n+1
 * https://blog.csdn.net/Hackbuteer1/article/details/7450250
 * 自己写代码验证下结果是否正确
 * 12个人从低到高，排成两排：要求每一排右边的人比左边的人高，第二排比第一排对应的人高
 * 分析：用一个数组表示对应的人站在第一排（0）还是第二排（1），
 * 比如000000111111：表示第1个到第6个人站在第一排，第7个到12个人站在第二排，符合条件
 * 所以转化为有多少种这样的6个0，6个1组合符合条件，共有C(12,6)中情况，关键是要排除其中不符合条件的
 * 这就是典型的卡特兰数C(12,6)/(6+1)=132
 * <p>
 * 排除过程：关注每个1，对于当前某个1，它的正前方，也就是第一排一定有个0，也就是说在它之前一定有一个0与它对应，所以它前面的0个数一定大于等于1的个数
 * 这样可以转换下6个数字按顺序进栈，请问有多少种出栈方式？ --- 一个数字一定是要先进栈（0表示）才会出栈（1表示）
 * 参考程序设计的艺术中的证明：X：0，S：1，XXXXXXSSSSSS,判断是否符合条件时，依次遍历S，找到第一个它之前的X的个数小于S的个数（包括自身），
 * 将它以及它之前的X和S互换，最后得到的序列是n-1个X，n+1个S==>反推出这样的序列个数就是不符合条件的个数
 * <p>
 * 所以：h(n) = C(2n,n)-C(2n,n-1)=C(2n,n)/(n+1)
 * <p>
 * <p>
 * 下面暴力破解验证下结论吧
 * @Author xuery
 * @Date 2019/1/3 19:16
 * @Version 1.0
 */
public class CatalanVerify {

    public static void main(String[] args) {
        CatalanVerify catalanVerify = new CatalanVerify();
        System.out.println(catalanVerify.catalan(6));
    }

    public int catalan(int n) {

        int[] X = new int[n]; //第一排的人对应的序列号
        int[] S = new int[n]; //第二排的人对应的序列号
        int x = 0, s = 0, currSeq = 0;

        //遍历000000111111-111111000000范围内6个0，6个1符合条件的个数
        int min = (int) Math.pow(2, n) - 1, max = (int) (Math.pow(2, 2 * n) - Math.pow(2, n));

        int resultCount = 0;
        for (int i = min; i <= max; i++) {
            if (findOneCount(i) == n) {
                x = 0;
                s = 0;
                currSeq = i;
                for(int k=0;k<2*n;k++){
                    if((currSeq & 1) == 0) {
                        X[x++] = k;
                    } else {
                        S[s++] = k;
                    }
                    currSeq >>= 1;
                }

                int valid = 1;
                for(int k =0;k<n;k++){
                    if(X[k] > S[k]){
                        //说明存在前面的人高于后面的人，排除掉
                        valid = 0;
                        break;
                    }
                }
                resultCount += valid;
            }
        }

        return resultCount;
    }

    private int findOneCount(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}
