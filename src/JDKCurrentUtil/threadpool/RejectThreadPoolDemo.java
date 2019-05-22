package JDKCurrentUtil.threadpool;

import java.util.concurrent.*;

/**
 * JL
 * 2019/5/22  23:10
 **/
public class RejectThreadPoolDemo {

    public static  class MyTask implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+":Thread ID: "+
                    Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {// 线程池的拒绝策略,需要实现的接口
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString()+" is discard");
                    }
                });

                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    es.submit(task);
                    Thread.sleep(10);
                }
    }
}
