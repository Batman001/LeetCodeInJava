package thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author Batman create on 2019-09-09 17:36
 */
public class FooCountDownLatch {

    final CountDownLatch firstLatch = new CountDownLatch(1);

    final CountDownLatch secondLatch = new CountDownLatch(1);

    public FooCountDownLatch() {

    }


    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        firstLatch.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        firstLatch.await();
        printSecond.run();
        secondLatch.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        secondLatch.await();
        printThird.run();
    }

}
