package thread.alternately;

/**
 * @author Batman create on 2019-09-10 09:49
 * @leetcode 1115 交替打印foobar
 */
public class FooBarObject {

    private int n;
    /**
     * obj为对象锁
     */
    private Object obj;

    /**
     * count % 2 等于 0 就打印foo，等于1就打印bar
     */
    private static int count = 0;

    public FooBarObject(int n) {
        this.n = n;
        obj = new Object();
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for(int i = 0; i < n; i++) {
            synchronized(obj) {
                //count % 2 == 1本应该是打印bar的，所以就休眠，释放锁
                if(count % 2 == 1) {
                    obj.wait();
                }
                //count % 2 == 0 或者是被唤醒了，就打印foo
                printFoo.run();
                count++;
                //打印完foo，应该去唤醒对面打印bar了
                obj.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        //休眠10毫秒，确保是foo方法先执行
        Thread.sleep(10);
        for(int i = 0; i < n; i++) {
            synchronized(obj) {
                if(count % 2 == 0) {
                    obj.wait();
                }
                printBar.run();
                count++;
                obj.notify();
            }
        }
    }

}
