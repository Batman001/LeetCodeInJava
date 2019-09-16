package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Batman create on 2019-09-16 10:00
 * 测试 CountDownLatch的作用
 * 使用场景：在发射火箭前需要进行10个任务的检查，等全部检查结束后才可以进行火箭的发射
 * 10个检查任务是多线程并发执行的
 */
public class CountDownDemo implements Runnable {

    static final CountDownLatch latch = new CountDownLatch(10);

    static final CountDownDemo demo = new CountDownDemo();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println(Thread.currentThread().getName() + " Check complete....");
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }

    }


    public static void main(String[] args) throws InterruptedException {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();

        ExecutorService exec = new ThreadPoolExecutor(10,20,200L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), namedThreadFactory);

        for(int i=0; i < 10; i++) {
            exec.submit(demo);
        }

        latch.await();

        System.out.println("Fire !!!! 发射火箭...🚀");

        exec.shutdown();
    }

}
