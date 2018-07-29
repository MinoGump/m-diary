package com.mino.mdiary.exercise.java.algorithm;

public class JosephusProblem {


    /**
     * N 个人坐圈进行游戏，从1号开始传递热土豆，经M次传递后拿着热土豆的人离座，最后剩下的人获得胜利
     */


    /**
     * http://maskray.me/blog/2013-08-27-josephus-problem-two-log-n-solutions
     *     f(n) = (f(n-1) + m) mod n
     * @param n
     * @param m
     * @return
     */
    public static int getWinner1(int n, int m) {
        int s = 0;
        for (int i = 2; i <= n; ++i) {
            s = (s + m + 1) % i;
        }
        return s+1;
    }

    public static void main(String[] args) {
        System.out.println(getWinner1(5, 0));
    }
}
