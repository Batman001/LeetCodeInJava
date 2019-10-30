package com.leetcode.train.thread.alternately;

/**
 * @author Batman create on 2019-09-10 10:26
 */
public class PrintFoo implements Runnable {
    @Override
    public void run() {
        System.out.printf("Foo");
    }
}
