package thread.alternately;

/**
 * @author Batman create on 2019-09-10 10:27
 */
public class PrintBar implements Runnable {
    @Override
    public void run() {
        System.out.printf("Bar ");
    }
}
