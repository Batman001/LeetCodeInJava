package thread;

import java.util.concurrent.Semaphore;

/**
 * @author Batman create on 2019-09-16 14:42
 */
public class SemaphoreDemo {
    public static void main(String[] args) {

        // 工人的数量
        int workersNum = 8;

        // 机器的数量
        Semaphore semaphore = new Semaphore(5);

        for(int i=0; i<workersNum; i++) {
            new Worker(i, semaphore).start();
        }
    }

    private static class Worker extends Thread {
        private int num;

        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;

        }

        @Override
        public void run() {
            try{
                // 通过信号量获取许可
                semaphore.acquire();
                System.out.println("工人" + this.num + " 占用一个机器进行生产....");
                Thread.sleep(2000);
                System.out.println("工人" + this.num + " 释放机器....");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
