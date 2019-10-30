package com.leetcode.train.thread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author Batman create on 2019-09-16 15:20
 */
public class ZeroEvenOddSem {

    private int n;

    private Semaphore s1, s2, s3;

    public ZeroEvenOddSem(int n) {
        this.n = n;
        s1 = new Semaphore(1);
        s2 = new Semaphore(0);
        s3 = new Semaphore(0);
    }


    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i++) {
            s1.acquire();
            printNumber.accept(0);
            // 使用(i & 1) 转换为二进制进行判断 判断i的奇偶性, if 0 则为偶数、否则为奇数
            if((i & 1) == 0) {
                s2.release();
            } else {
                s3.release();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i+=2) {
            s2.acquire();
            printNumber.accept(i);
            s1.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<=n; i+=2) {
            s3.acquire();
            printNumber.accept(i);
            s1.release();
        }
    }
}
