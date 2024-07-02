package JUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class 线程池 {
    public 线程池() {

    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        Executors.newWorkStealingPool();
        Executors.newSingleThreadScheduledExecutor();
        Executors.newFixedThreadPool(2);
        Executors.newScheduledThreadPool(1);
        ReentrantLock reentrantLock = new ReentrantLock();

    }
}
