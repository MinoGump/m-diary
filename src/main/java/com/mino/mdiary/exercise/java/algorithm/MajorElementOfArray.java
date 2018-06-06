package com.mino.mdiary.exercise.java.algorithm;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MajorElementOfArray {

    public static void main(String[] args) {
        List<Integer> array = new Random().ints(100, 0, 200).boxed().collect(Collectors.toList());
        array.addAll(new Random().ints(100, array.get(0), array.get(0) + 1).boxed().collect(Collectors.toList()));
        Collections.shuffle(array);
        System.out.println(array);

        long starttime = System.nanoTime();
        System.out.println("start Method 1 : " + starttime);
        System.out.println(getMajorElementOfArray(array.toArray(new Integer[array.size()])));
        long endtime = System.nanoTime();
        System.out.println("end Method 1 : " + endtime + "  and cost : " + (endtime - starttime));

        starttime = System.nanoTime();
        System.out.println("start Method 2 : " + starttime);
        System.out.println(getMajorElementOfArrayRecursively(array));
        endtime = System.nanoTime();
        System.out.println("end Method 2 : " + endtime + "  and cost : " + (endtime - starttime));
    }

    /**
     * 返回数组的主元素，如果无主元素，则返回负无穷
     * @param array
     * @return
     */
    public static int getMajorElementOfArray(Integer[] array) {
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

    public static int getMajorElementOfArrayRecursively(List<Integer> array) {
        if (array.size() == 1) {
            return array.get(0);
        }
        List<Integer> newArray = new ArrayList<>();
        for (int i = 0; i < array.size(); i += 2) {
            if (i + 1 < array.size() && array.get(i).equals(array.get(i + 1))) {
                newArray.add(array.get(i));
            } else if (i + 1 == array.size()) {
                newArray.add(array.get(i));
            }
        }
        return getMajorElementOfArrayRecursively(newArray);
    }
}
