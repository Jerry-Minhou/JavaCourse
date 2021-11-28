package com.rjkf.demo;

import java.util.concurrent.TimeUnit;

public class ThreadVolatileDemo {
    public static void main(String[] args) throws Exception {
        //使用volatile关键字，使线程共享变量
        long start = System.currentTimeMillis();
        SumThread sumThread = new SumThread();

        sumThread.start();

        while (!sumThread.isSuccess()) {
            TimeUnit.MILLISECONDS.sleep(1);
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
        private volatile boolean success = false;

        public boolean isSuccess() {
            return success;
        }

        public Integer getResult() {
            return result;
        }

        @Override
        public void run() {
            result = sum();
            success = true;
        }
    }
}
