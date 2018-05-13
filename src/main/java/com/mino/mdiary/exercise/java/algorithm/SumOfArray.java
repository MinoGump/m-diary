package com.mino.mdiary.exercise.java.algorithm;


import org.apache.commons.lang3.ArrayUtils;

import static org.apache.commons.lang3.math.NumberUtils.max;
import static org.apache.commons.lang3.math.NumberUtils.min;

public class SumOfArray {

    public static void main(String[] args) {
        int[] array = {1, 3, -6, 2, -5, 10, 7, -1, 3, 0, -10, -1, 6, 9};
        System.out.println(minimumSubsetOfArray(array));
        System.out.println(minimumPositiveSubsetOfArray(array));
        System.out.println(maximumProductSubsetOfArray(array));
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

    public static int minimumPositiveSubsetOfArray(int[] array) {
        if (ArrayUtils.isEmpty(array)) {
            return 0;
        }
        int thisSum = 0, minSum = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; ++i) {
            thisSum = 0;
            for (int j = i; j < array.length; ++j) {
                thisSum += array[j];
                if (thisSum < minSum && thisSum > 0)
                    minSum = thisSum;
            }
        }
        return minSum;
    }

    public static int maximumProductSubsetOfArray(int[] array) {
        if (ArrayUtils.isEmpty(array)) {
            return 0;
        }
        int maxPositiveProduct = array[0], minNegativeProduct = array[0], ret = array[0];
        for (int i = 1; i < array.length; ++i) {
            int tempMaxPosProduct = maxPositiveProduct;
            int tempMinNegProduct = minNegativeProduct;
            maxPositiveProduct = max(array[i], max(tempMaxPosProduct*array[i], tempMinNegProduct*array[i]));
            minNegativeProduct = min(array[i], min(tempMaxPosProduct*array[i], tempMinNegProduct*array[i]));
            if (maxPositiveProduct > ret) {
                ret = maxPositiveProduct;
            }
        }
        return ret;
    }

}
