package com.leetcode.train.thread.inorder;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @leetcode 1114 按序打印
 * @author Batman create on 2019-09-09 17:30
 */
public class FooLock {

    private boolean isFirstPrinted = false;

    private boolean isSecondPrinted = false;

    private Object lock = new Object();

    public FooLock(){

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            printFirst.run();
            isFirstPrinted = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while(!isFirstPrinted) {
                lock.wait();
            }

            printSecond.run();
            isSecondPrinted = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while(!isSecondPrinted) {
                lock.wait();
            }

            printThird.run();
        }
    }

    public static void main(String[] args) {

        final PrintFirst printFirst = new PrintFirst();
        final PrintSecond printSecond = new PrintSecond();
        final PrintThird printThird = new PrintThird();

        final FooLock fooLock = new FooLock();
//        ThreadPoolExecutor executor =
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try{
                    fooLock.first(printFirst);

                }catch(InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try{
                    fooLock.second(printSecond);

                }catch(InterruptedException e) {
                    e.printStackTrace();
                }

            }

        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                try{
                    fooLock.third(printThird);

                }catch(InterruptedException e) {
                    e.printStackTrace();
                }

            }

        };

        t3.start();
        t2.start();
        t1.start();

    }


}
