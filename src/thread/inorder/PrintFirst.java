package thread.inorder;

/**
 * @author Batman create on 2019-09-10 10:35
 */
public class PrintFirst implements Runnable {
    @Override
    public void run() {
        System.out.printf("First ");
    }
}
