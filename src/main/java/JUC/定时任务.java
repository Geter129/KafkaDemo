package JUC;

import java.util.concurrent.*;

public class 定时任务 {
    private static long time;
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        //这里使用ScheduledFuture
//        ScheduledFuture<String> future = executor.schedule(() -> "????", 3, TimeUnit.SECONDS);
//        System.out.println("任务剩余等待时间："+future.getDelay(TimeUnit.MILLISECONDS) / 1000.0 + "s");
//        System.out.println("任务执行结果："+future.get());
//        executor.shutdown();
        //获取CPU核心数
//        System.out.println(Runtime.getRuntime().availableProcessors() );
//        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
//        service.schedule(() -> System.out.println("Hello World!"), 1, TimeUnit.SECONDS);
        //三秒钟延迟开始，之后每隔一秒钟执行一次
        System.out.println(System.currentTimeMillis()/1000.0);
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(6);
        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(() -> {
                    System.out.println("Hello World!");
                    System.out.println((System.currentTimeMillis() - time)/1000.0);
                    time = System.currentTimeMillis();
                },
                3, 1, TimeUnit.SECONDS);
        Thread.sleep(6000);
        scheduledFuture.cancel(false);
        System.out.println(scheduledFuture.isCancelled());
    }
}
