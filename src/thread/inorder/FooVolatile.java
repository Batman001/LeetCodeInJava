package thread.inorder;

/**
 * @author Batman create on 2019-09-09 17:06
 * leetcode 1114 顺序打印(有可能会出现错误)
 * https://leetcode-cn.com/problems/print-in-order/
 */
class FooVolatile {

    /**
     * 保证printSecond在printFirst之后执行的标志位
     */
    volatile boolean isFirstPrinted = false;

    /**
     * 保证printThird在printSecond之后执行的标志位
     */
    volatile boolean isSecondPrinted = false;

    public FooVolatile() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        isFirstPrinted = true;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(!isFirstPrinted){

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        isSecondPrinted = true;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(!isFirstPrinted && !isSecondPrinted) {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
