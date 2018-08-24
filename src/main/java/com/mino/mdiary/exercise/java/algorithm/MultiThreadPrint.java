package com.mino.mdiary.exercise.java.algorithm;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class MultiThreadPrint {

    @Test
    public void test() throws InterruptedException, IOException {
        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(1);
        Semaphore semaphoreC = new Semaphore(1);

        Thread A =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        semaphoreA.acquire();
                        System.out.print("A");
                        semaphoreB.release();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread B =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        semaphoreB.acquire();
                        System.out.print("B");
                        semaphoreC.release();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread C =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        semaphoreC.acquire();
                        System.out.print("C");
                        semaphoreA.release();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
//        AThread aThread = new AThread();
//        BThread bThread = new BThread();

        semaphoreB.acquire();
        semaphoreC.acquire();
        A.start();
        B.start();
        C.start();
    }

    class AThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.print("A");
            }
        }
    }

    class BThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.print("B");
            }
        }
    }
}
