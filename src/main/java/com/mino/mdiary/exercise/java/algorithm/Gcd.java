package com.mino.mdiary.exercise.java.algorithm;

public class Gcd {

    public static void main(String[] args) {
        System.out.println(recursiveGcd(32, 8));
    }

    /**
     * 保证 a >= b
     * @param a     number a
     * @param b     number b
     * @return      gcd of a and b
     */
    public static int recursiveGcd(int a, int b) {
        if (b == 1) {
            return 1;
        }
        if (a % 2 == 0 && b % 2 == 0) {
            return 2 * recursiveGcd(a / 2, b / 2);
        } else if (a % 2 == 0 && b % 2 == 1) {
            return recursiveGcd(a / 2, b);
        } else if (a % 2 == 1 && b % 2 == 0) {
            return recursiveGcd(a, b / 2);
        } else {
            return recursiveGcd((a+b)/2, (a-b)/2);
        }
    }
}
