package atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JL
 * 2020/2/23  19:47
 **/
public class SyncThread implements Runnable {

    protected long startTime;
    LongAdderDemo out ;

    public SyncThread( LongAdderDemo out) {
        this.startTime = System.currentTimeMillis();
        this.out = out;
    }

    @Override
    public void run() {
        synchronized (out){

            long v = out.getCount();
            while(v < LongAdderDemo.TARGET_COUNT){
                v = out.inc();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("SyncThread spend:"+(endTime-startTime)+ " v="+ out.getCount());
        LongAdderDemo.cdl.countDown();
    }


}
