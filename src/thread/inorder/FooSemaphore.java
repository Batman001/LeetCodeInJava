package thread.inorder;

import java.util.concurrent.Semaphore;

/**
 * @leetcode 1114 按序打印
 * 使用多线程工具 semaphore(信号量)
 * @author Batman create on 2019-09-09 19:30
 */
public class FooSemaphore {
    final Semaphore firstSem = new Semaphore(0);
    final Semaphore secondSem = new Semaphore(0);


    public FooSemaphore() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        firstSem.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        firstSem.acquire();
        printSecond.run();
        secondSem.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        secondSem.acquire();
        printThird.run();
    }
}
