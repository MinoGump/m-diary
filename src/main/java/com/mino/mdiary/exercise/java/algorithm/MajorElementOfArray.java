package com.mino.mdiary.exercise.java.algorithm;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MajorElementOfArray {

    private static long startTime = 0L;
    private static long endTime = 0L;
    private static long startMem = 0L;
    private static long endMem = 0L;

    public static void main(String[] args) {
        List<Integer> array = new Random().ints(100, 0, 200).boxed().collect(Collectors.toList());
        array.addAll(new Random().ints(100, array.get(0), array.get(0) + 1).boxed().collect(Collectors.toList()));
        Collections.shuffle(array);
        System.out.println(array);

        Runtime run = Runtime.getRuntime();
        startTime = System.nanoTime();
        startMem = run.totalMemory() - run.freeMemory();
        System.out.println("start Method 1, time : " + startTime + " , mem : " + startMem);
        System.out.println(getMajorElementOfArray(array.toArray(new Integer[array.size()])));
        endTime = System.nanoTime();
        endMem = run.totalMemory() - run.freeMemory();
        System.out.println("end Method 1, time : " + endTime + " , mem : " + endMem);
        System.out.println("cost : " + (endTime - startTime) + " , " + (endMem - startMem));

        System.out.println("");

        startTime = System.nanoTime();
        startMem = run.totalMemory() - run.freeMemory();
        System.out.println("start Method 2, time : " + startTime + " , mem : " + startMem);
        System.out.println(getMajorElementOfArrayRecursively(array.toArray(new Integer[array.size()]), array.size()));
        endMem = run.totalMemory() - run.freeMemory();
        endTime = System.nanoTime();
        System.out.println("end Method 2, time : " + endTime + " , mem : " + endMem);
        System.out.println("cost : " + (endTime - startTime) + " , " + (endMem - startMem));
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

    public static int getMajorElementOfArrayRecursively(Integer[] array, int length) {
        if (length == 1) {
            return array[0];
        }
        Integer[] newArray = new Integer[length/2 + 1];
        int j = 0;
        for (int i = 0; i < length; i += 2) {
            if (i + 1 < length && array[i].equals(array[i + 1])) {
                newArray[j++] = array[i];
            } else if (i + 1 == length) {
                newArray[j++] = array[i];
            }
        }
        return getMajorElementOfArrayRecursively(newArray, j);
    }
}
