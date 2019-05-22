package JDKCurrentUtil.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JL
 * 2019/5/15  21:39
 **/
public class ThreadPoolDemo {

    public static class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+
                    ":Thread ID "+Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        // 创建一个固定线程数量的线程池
        //ExecutorService es = Executors.newFixedThreadPool(5);
        // 创建一个根据实际情况调整线程数量的线程池
        ExecutorService es = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            es.submit(task);
        }
        es.shutdown();
    }
}
