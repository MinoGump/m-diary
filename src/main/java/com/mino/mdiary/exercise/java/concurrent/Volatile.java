package com.mino.mdiary.exercise.java.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Volatile {

    private volatile int inc = 0;
    private int inc1 = 0;
    private int inc2 = 0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    Lock lock = new ReentrantLock();

    public void increase() {
        inc++;
    }

    public synchronized void increase1() {
        inc1++;
    }

    public  void increase2() {
        lock.lock();
        try {
            inc2++;
        } finally{
            lock.unlock();
        }
    }

    public void increase3() {
        atomicInteger.getAndIncrement();
    }

    public static int getIncByVolatile() {
        final Volatile test = new Volatile();
        for(int i=0;i<1000;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<100000;j++)
                        test.increase();
                };
            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        return test.inc;
    }

    public static int getIncBySynchronized() {
        final Volatile vol = new Volatile();
        for (int i = 0; i < 1000; ++i) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 100000; j++) {
                        vol.increase1();
                    }
                }
            }.start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        return vol.inc1;
    }

    public static int getIncByLock() {
        final Volatile vol = new Volatile();
        for (int i = 0; i < 1000; ++i) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 100000; j++) {
                        vol.increase2();
                    }
                }
            }.start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        return vol.inc2;
    }

    public static int getIncByAtomicInteger() {
        final Volatile vol = new Volatile();
        for (int i = 0; i < 1000; ++i) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 100000; ++j) {
                        vol.increase3();
                    }
                }
            }.start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        return vol.atomicInteger.get();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(getIncByVolatile());
        System.out.println("used : " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(getIncBySynchronized());
        System.out.println("used : " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(getIncByLock());
        System.out.println("used : " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(getIncByAtomicInteger());
        System.out.println("used : " + (System.currentTimeMillis() - startTime));
    }

    static class Singleton{
        private volatile static Singleton instance = null;

        private Singleton() {

        }

        public static Singleton getInstance() {
            if(instance==null) {
                synchronized (Singleton.class) {
                    if(instance==null)
                        instance = new Singleton();
                }
            }
            return instance;
        }
    }
}
