package com.rjkf.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadReentrantLockDemo {
    //使用可重入锁ReentrantLock
    private static final ReentrantLock lock = new ReentrantLock(true);
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        SumThread sumThread = new SumThread();
        sumThread.start();

        TimeUnit.MILLISECONDS.sleep(1);
        lock.lock();
        try {
            System.out.println("hello");
        } finally {
            lock.unlock();
        }

        int result = sumThread.getResult();
        System.out.println("异步计算结果：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + "  ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    static class SumThread extends Thread {


        private Integer result;

        public Integer getResult() {
            return result;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                result = sum();
            } finally {
                lock.unlock();
            }
        }
    }
}
