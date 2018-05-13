package com.mino.mdiary.exercise.java.algorithm;

import org.apache.commons.lang3.ArrayUtils;

public class MajorElementOfArray {

    public static void main(String[] args) {
        int[] array ={3,2,2,5,2,3,2,4,2};
        System.out.println(getMajorElementOfArray(array));
    }

    /**
     * 返回数组的主元素，如果无主元素，则返回负无穷
     * @param array
     * @return
     */
    public static int getMajorElementOfArray(int[] array) {
        int res = Integer.MIN_VALUE;
        if (ArrayUtils.isEmpty(array)) {
            return res;
        }
        int count = 1;
        res = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] == res) {
                count++;
            } else {
                count--;
            }
            if (count == 0 && i < array.length-1) {
                res = array[i+1];
                count = 1;
                i++;
            } else if (count == 0 && i == array.length-1) {
                return Integer.MIN_VALUE;
            }
        }
        return res;
    }
}
