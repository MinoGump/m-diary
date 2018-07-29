package com.mino.mdiary.util;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListUtil {

    public static <T extends Comparable> LinkedList<T> intersect(LinkedList<T> l1, LinkedList<T> l2) {
        LinkedList<T> result = new LinkedList<>();
        if (l1 == null || l1.size() == 0 || l2 == null || l2.size() == 0) {
            return result;
        }
        Iterator<T> i1 = l1.iterator();
        Iterator<T> i2 = l2.iterator();
        T t1 = i1.next();
        T t2 = i2.next();
        T ft1 = null, ft2 = null;
        while (i1.hasNext() && i2.hasNext()) {
            while (ft1 != null && i1.hasNext() && t1.equals(ft1)) {
                t1 = i1.next();
            }
            while (ft2 != null && i2.hasNext() && t2.equals(ft2)) {
                t2 = i2.next();
            }
            if (t1.compareTo(t2) == 0) {
                result.add(t1);
                if (i1.hasNext() && i2.hasNext()) {
                    ft1 = t1;
                    ft2 = t2;
                    t1 = i1.next();
                    t2 = i2.next();
                }
            } else if (t1.compareTo(t2) > 0) {
                if (i2.hasNext()) {
                    ft2 = t2;
                    t2 = i2.next();
                }
            } else {
                if (i1.hasNext()) {
                    ft1 = t1;
                    t1 = i1.next();
                }
            }
        }
        return result;
    }

    public static <T extends Comparable> LinkedList<T> union(LinkedList<T> l1, LinkedList<T> l2) {
        LinkedList<T> result = new LinkedList<>();
        if (l1 == null || l1.size() == 0 || l2 == null || l2.size() == 0) {
            return result;
        }
        Iterator<T> i1 = l1.iterator();
        Iterator<T> i2 = l2.iterator();
        T t1 = i1.next();
        T t2 = i2.next();
        T ft1 = null, ft2 = null;
        while (true) {
            while (ft1 != null && i1.hasNext() && t1.equals(ft1)) {
                t1 = i1.next();
            }
            while (ft2 != null && i2.hasNext() && t2.equals(ft2)) {
                t2 = i2.next();
            }
            if (t1.compareTo(t2) == 0) {
                result.add(t1);
                if (i1.hasNext() && i2.hasNext()) {
                    ft1 = t1;
                    ft2 = t2;
                    t1 = i1.next();
                    t2 = i2.next();
                } else {
                    break;
                }
            } else if (t1.compareTo(t2) > 0) {
                result.add(t2);
                if (i2.hasNext()) {
                    ft2 = t2;
                    t2 = i2.next();
                } else {
                    result.add(t1);
                    while (i1.hasNext()) {
                        result.add(i1.next());
                    }
                    break;
                }
            } else {
                result.add(t1);
                if (i1.hasNext()) {
                    ft1 = t1;
                    t1 = i1.next();
                } else {
                    result.add(t2);
                    while (i2.hasNext()) {
                        result.add(i2.next());
                    }
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedList<Integer> l1 = new LinkedList<>();
        l1.add(1);
        l1.add(2);
        l1.add(4);
        l1.add(6);
        LinkedList<Integer> l2 = new LinkedList<>();
        l2.add(1);
        l2.add(2);
        l2.add(3);
        l2.add(4);
        l2.add(5);
        System.out.println(intersect(l1, l2));
        System.out.println(union(l1, l2));
    }


}
