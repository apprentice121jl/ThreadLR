package JDKCurrentUtil;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JL
 * 2019/5/13  23:20
 **/
public class CountDownLatchDemo implements Runnable {

    // 10代表当前计数器的计数个数,即需要有10个线程完成任务，等待在CountDownLatch上的线程才能继续执行
    static final CountDownLatch end= new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();
    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(demo);
        }
        // 要求主线程等待所有10个线程全部执行完毕
        end.await();
        System.out.println("Fire!");
        exec.shutdown();
    }
}
