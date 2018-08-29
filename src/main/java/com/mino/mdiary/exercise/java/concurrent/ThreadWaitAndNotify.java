package com.mino.mdiary.exercise.java.concurrent;

public class ThreadWaitAndNotify {

    private int num = 0;

    public synchronized void threadProcess() {
        for (int i = 0; i < 1000; ++i) {
            num++;
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadWaitAndNotify threadWaitAndNotify = new ThreadWaitAndNotify();
        for (int i = 0; i < 3; ++i) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    threadWaitAndNotify.threadProcess();
                }
            }).start();
        }
        Thread.sleep(5000);
        synchronized (threadWaitAndNotify) {
            threadWaitAndNotify.notifyAll();
        }
        System.out.println(threadWaitAndNotify.num);
    }
}
