package com.mino.mdiary.exercise.java.dsaa.chapter1;

import org.junit.Test;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Test1_15 {

    @Test
    public void test() {
        test_1_15();
    }

    public String test_1_15() {
        Rectangle a = new Rectangle(1, 2);
        Rectangle b = new Rectangle(3, 4);
        Rectangle[] rectangles = {a, b};
        OrderedCollection<Rectangle> collection = new OrderedCollection<>(rectangles);
        System.out.println(collection.isEmpty());
        collection.insert(new Rectangle(3,6));
        collection.print();

        collection.remove();
        collection.print();

        System.out.println(collection.findMin());
        System.out.println(collection.findMax());
        return "";
    }

    class OrderedCollection<T extends Comparable<? super T>> {
        T[] array;
        int size;

        public OrderedCollection(T[] array) {
            this.array = (T[]) new Comparable[array.length];
            System.arraycopy(array, 0, this.array, 0, array.length);
            size = array.length;
        }

        public boolean isEmpty() {
            return array == null || size == 0;
        }

        public void makeEmpty() {
            array = null;
            size = 0;
        }

        public void insert(T a) {
            array  = Arrays.copyOf(array, ++size);
            array[size-1] = a;
        }

        public void remove() {
            array[size-1] = null;
            array  = Arrays.copyOf(array, --size);
        }

        public T findMin() {
            if (size == 0) return null;
            T min = array[0];
            for (int i = 1; i < size; ++i) {
                if (array[i].compareTo(min) < 0) min = array[i];
            }
            return min;
        }

        public T findMax() {
            if (size == 0) return null;
            T max = array[0];
            for (int i = 1; i < size; ++i) {
                if (array[i].compareTo(max) > 0) max = array[i];
            }
            return max;
        }

        public void print() {
            System.out.println("size is " + size + ", array is " + Arrays.toString(array));
        }
    }

    class Rectangle implements Comparable<Rectangle> {
        private double width;
        private double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        @Override
        public int compareTo(Rectangle o) {
            double thisArea = width*height;
            double argArea = o.width*o.height;
            return (int) (thisArea - argArea);
        }

        @Override
        public String toString() {
            return "width=" + width + ",height=" + height;
        }
    }
}
