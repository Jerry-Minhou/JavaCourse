package com.rjkf.demo;

import java.util.concurrent.TimeUnit;

public class ThreadSynchronizedDemo {
    //使用synchronized关键字同步代码块
    private static final Object lock = new Object();
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        SumThread sumThread = new SumThread();
        int result = 0;
        sumThread.start();

        TimeUnit.MILLISECONDS.sleep(1);
        synchronized (lock) {
            result = sumThread.getResult();
        }
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
            synchronized (lock) {
                result = sum();
            }
        }
    }
}
