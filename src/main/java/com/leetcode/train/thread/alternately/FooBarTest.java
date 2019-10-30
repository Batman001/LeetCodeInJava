package com.leetcode.train.thread.alternately;

/**
 * @author Batman create on 2019-09-10 15:06
 */
public class FooBarTest {
    public static void main(String[] args) {
        final FooBarObject fb = new FooBarObject(100);
        final PrintFoo foo = new PrintFoo();
        final PrintBar bar = new PrintBar();

        //用匿名内部类创建一个线程打印foo
        new Thread() {
            @Override
            public void run() {
                try {
                    fb.foo(foo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //用匿名内部类创建一个线程打印bar
        new Thread() {
            @Override
            public void run() {
                try {
                    fb.bar(bar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
