package atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * JL
 * 2020/2/23  19:41
 **/
public class LongAdderDemo {
    // 线程数
     static final int MAX_THREADS = 3;
    // 任务数
    static final int TASK_COUNT = 3;
    // 目标总数
     static final int TARGET_COUNT = 10000000;

    AtomicLong acount = new AtomicLong(0L);
    LongAdder lacount = new LongAdder();
    private long count = 0;

    static CountDownLatch cdl = new CountDownLatch(TASK_COUNT);

    protected   long inc(){
        return ++count;
    }

    protected   long getCount(){
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);

        LongAdderDemo demo = new LongAdderDemo();

        // 1.使用同步锁
        SyncThread sync = new SyncThread(demo);
        // 2.使用原子类
        // AtomicThread sync = new AtomicThread(demo);
        // 3.使用LongAdder
        // LongAddrThread sync = new LongAddrThread(demo);
        for (int i = 0; i < 3; i++) {
            exe.submit(sync);
        }
        cdl.await();
        exe.shutdown();
    }
}
