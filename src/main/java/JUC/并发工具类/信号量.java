package JUC.并发工具类;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

public class 信号量 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //每一个Semaphore都会在一开始获得指定的许可证数数量，也就是许可证配额
//        Semaphore semaphore = new Semaphore(3);   //许可证配额设定为2
//
//        for (int i = 0; i < 3; i++) {
//            new Thread(() -> {
//                try {
//                    semaphore.acquire(2);   //申请一个许可证
//                    System.out.println("许可证申请成功！");
//                    semaphore.release();   //归还一个许可证
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
        Semaphore semaphore = new Semaphore(3);   //只配置一个许可证，5个线程进行争抢，不内卷还想要许可证？
        for (int i = 0; i < 5; i++)
            new Thread(semaphore::acquireUninterruptibly).start();   //可以以不响应中断（主要是能简写一行，方便）
        Thread.sleep(500);
        System.out.println("剩余许可证数量："+semaphore.availablePermits());
        System.out.println("是否存在线程等待许可证："+(semaphore.hasQueuedThreads() ? "是" : "否"));
        System.out.println("等待许可证线程数量："+semaphore.getQueueLength());
    }
}

