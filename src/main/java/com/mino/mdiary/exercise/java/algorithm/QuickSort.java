package com.mino.mdiary.exercise.java.algorithm;


public class QuickSort {


    public static void main(String[] args) {
        int[] array = {1, 2, 5, 8, 10, 3, 4, 1};
        quicksort(array, 0, array.length-1);

        for (int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " ");
        }
    }

    public static void quicksort(int[] array, int left, int right) {
        int index;
        if (left < right) {
            index = partition(array, left, right);
            quicksort(array, left, index - 1);
            quicksort(array, index + 1, right);
        }
    }

    private static int partition(int[] array, int left, int right) {
        int pivot = array[left];
        while (left < right) {
            while (left < right && array[right] >= pivot) {
                right--;
            }
            if (left < right) {
                array[left++] = array[right];
            }
            while (left < right && array[left] <= pivot) {
                left++;
            }
            if (left < right) {
                array[right--] = array[left];
            }
        }
        array[left] = pivot;
        return left;
    }
}
