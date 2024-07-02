package JUC;


/**
 * 可重入锁示例(同一个线程可以重入上锁的代码段，不同的线程则需要进行阻塞)
 * java的可重入锁有:ReentrantLock（显式的可重入锁）、synchronized（隐式的可重入锁）
 * 可重入锁诞生的目的就是防止上面不可重入锁的那种情况，导致同一个线程不可重入上锁代码段。
 * 目的就是让同一个线程可以重新进入上锁代码段。
 * 设计可重入锁的示例代码
 */
public class MyReentrantLock {

    boolean isLocked = false;   // 默认没有上锁
    Thread lockedBy = null; // 记录阻塞线程
    int lockedCount = 0;    // 上锁次数计数

    /**
     * 上锁逻辑
     */
    public synchronized void lock() throws InterruptedException {

        Thread thread = Thread.currentThread();
        // 上锁了 并且 如果是同一个线程则放行，否则其它线程需要进入where循环进行等待
        while (isLocked && lockedBy != thread) {

            wait();
        }
        isLocked = true; // 第一次进入就进行上锁
        lockedCount++; // 上锁次数计数
        lockedBy = thread; // 当前阻塞的线程
    }

    /**
     * 释放锁逻辑
     */
    public synchronized void unlock() {

        if (Thread.currentThread() == this.lockedBy) {

            lockedCount--; // 将上锁次数减一
            if (lockedCount == 0) {
                // 当计数为0，说明所有线程都释放了锁
                isLocked = false; // 真正的将释放了所有锁
                notify();
            }
        }
    }
}