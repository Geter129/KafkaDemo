package JUC.并发工具类;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class 循环屏障 {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(10,   //创建一个初始值为10的循环屏障
                () -> System.out.println("飞机马上就要起飞了，各位特种兵请准备！"));   //人等够之后执行的任务
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep((long) (2000 * new Random().nextDouble()));
                    System.out.println("玩家 "+ finalI +" 进入房间进行等待... ("+barrier.getNumberWaiting()+"/10)");

                    barrier.await();    //调用await方法进行等待，直到等待的线程足够多为止

                    //开始游戏，所有玩家一起进入游戏
                    System.out.println("玩家 "+ finalI +" 进入游戏！");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
