package com.mino.mdiary.exercise.java.algorithm;


import org.apache.commons.lang3.ArrayUtils;

public class SumOfArray {

    public static void main(String[] args) {
        int[] array = {1, 3, -6, 2, -5, 10, 7, -1, 3, 0, -10, -1, 6, 9};
        System.out.println(minimumSubsetOfArray(array));
    }

    public static int minimumSubsetOfArray(int[] array) {
        if (ArrayUtils.isEmpty(array)) {
            return 0;
        }
        int minSum = 0, thisSum = 0;
        for (int i = 0; i < array.length; ++i) {
            thisSum += array[i];
            if (thisSum < minSum) {
                minSum = thisSum;
            }
            if (thisSum > 0) {
                thisSum = 0;
            }
        }
        return minSum;
    }
}
