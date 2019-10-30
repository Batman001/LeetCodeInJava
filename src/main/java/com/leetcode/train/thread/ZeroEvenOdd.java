package com.leetcode.train.thread;

import java.util.function.IntConsumer;

/**
 * @author Batman create on 2019-09-16 10:35
 * leetcode 1116 打印零与奇偶数
 * 参考地址：https://leetcode-cn.com/problems/print-zero-even-odd/
 */
public class ZeroEvenOdd {


    private int n;

    int wantPrint = 0;

    int num = 1;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    /**
     * 仅打印出 0
     * @param printNumber
     */
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=0; i<n; i++) {
            synchronized (this) {
                while(num % 2 == 0) {
                    this.wait();
                }
                printNumber.accept(0);
                num ++;
                this.notifyAll();
            }
        }


    }

    /**
     * 顺序打印偶数
     * @param printNumber
     */
    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<n; i+=2) {
            synchronized (this) {
                while(num % 4 != 0) {
                    this.wait();
                }

                printNumber.accept( ++ wantPrint);
                num ++;
                this.notifyAll();
            }

        }

    }


    /**
     * 顺序打印奇数
     * @param printNumber
     */
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i=1; i<n; i+=2) {
            synchronized (this) {
                while(num %2 != 0 || num%4 == 0) {
                    this.wait();
                }
                printNumber.accept(num++);
                this.notifyAll();
            }
        }

    }
}
