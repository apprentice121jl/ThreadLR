package JDKCurrentUtil.threadpool;

import java.util.concurrent.*;

/**
 * JL
 * 2019/6/20  0:17
 **/
public class ThreadFactoryTest {

    public static void main(String[] args) throws InterruptedException {
        RejectThreadPoolDemo.MyTask task = new RejectThreadPoolDemo.MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MICROSECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {// 通过实现ThreadFactory接口来进行创建线程池中的线程
                Thread t = new Thread(r);
                t.setDaemon(true);
                System.out.println("create "+t);
                return t;
            }
        });
        for(int i = 0;i < 5;i++){
            es.submit(task);
        }
        Thread.sleep(200);
    }
}
