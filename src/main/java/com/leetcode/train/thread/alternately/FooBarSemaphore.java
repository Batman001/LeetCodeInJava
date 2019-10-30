package com.leetcode.train.thread.alternately;

import java.util.concurrent.Semaphore;

/**
 * @author Batman create on 2019-09-10 16:15
 * leetcode 1115
 */
public class FooBarSemaphore {

    private Semaphore firstSem = new Semaphore(0);
    private Semaphore secondSem = new Semaphore(1);
    private int n;

    public FooBarSemaphore(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for(int i=0; i<n; i++) {
            secondSem.acquire();
            printFoo.run();
            firstSem.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for(int i=0; i<n; i++) {
            firstSem.acquire();
            printBar.run();
            secondSem.release();
        }
    }
}
