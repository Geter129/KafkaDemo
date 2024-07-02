package JUC;

/**
 * 不可重入锁示例(同一个线程不可以重入上锁后的代码段)
 * 如下是一个不可重入锁的逻辑过程，会发现执行main方法控制台会打印执行doJob方法前，然后就会一直线程阻塞，不会打印执行doJob方法过程中，
 * 原因在于第一次上锁后，由于没有释放锁，因此执行第一次lock后isLocked = true，
 * 这个时候调用doJob()内部又一次调用了lock()由于上个线程将isLocked = true，导致再次进入的时候就进入死循环。
 * 导致线程无法执行System.out.println("执行doJob方法过程中");这行代码，因此控制台只能打印执行doJob方法前。这种现象就造成了不可重入锁
 */
public class Count{

    MyLock lock = new MyLock();

    public static void main(String[] args) throws InterruptedException {

        new Count().doSomeThing(); // 示例的main方法
    }
    public void doSomeThing() throws InterruptedException {

        lock.lock(); // 第一次上锁
        System.out.println("执行doJob方法前");
        doJob(); // 方法内会再次上锁
        lock.unlock(); // 释放第一次上的锁
    }
    public void doJob() throws InterruptedException {

        lock.lock();
        System.out.println("执行doJob方法过程中");
        lock.unlock();
    }
}

/** * 自定义锁 */
class MyLock{

    private boolean isLocked = false;
    public synchronized void lock() throws InterruptedException{

        while(isLocked){

            wait();
        }
        isLocked = true; // 线程第一次进入后就会将器设置为true，第二次进入是就会由于where true进入死循环
    }
    public synchronized void unlock(){

        isLocked = false;   // 将这个值设置为false目的是释放锁
        notify();           // 接触阻塞
    }
}