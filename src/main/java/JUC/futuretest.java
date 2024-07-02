package JUC;


import java.util.concurrent.*;
//线程池带返回的方法
public class futuretest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //方式一
        FutureTask task = new FutureTask(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "我是字符串！");
        executor.submit(task);
        System.out.println(task.get());
        //方式二
        Future<String> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "我是字符串！");
        System.out.println(future.get());
        executor.shutdown();
    }
}
