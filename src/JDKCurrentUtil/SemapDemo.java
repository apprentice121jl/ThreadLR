package JDKCurrentUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * JL
 * 2019/5/13  21:37
 * 信号量：允许五个线程进入,同时执行
 **/
public class SemapDemo implements  Runnable {
	
    Semaphore semp = new Semaphore(5);

    @Override
    public void run() {
        try {
            semp.acquire();
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getId()+":done!");
            semp.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        SemapDemo demo = new SemapDemo();
        for(int i =0;i<20;i++){
            exec.submit(demo);
        }
        exec.shutdown();
    }
}
