package com.leetcode.train.thread.inorder;

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




}
