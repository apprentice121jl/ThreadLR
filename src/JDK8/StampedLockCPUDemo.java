package JDK8;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * JL
 * 2020/2/23  14:16
 **/
public class StampedLockCPUDemo {

    static Thread[] holdCpuThreads = new Thread[3];
    static final StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                long readLong = lock.writeLock();
                LockSupport.parkNanos(6000000000L);
                lock.unlockWrite(readLong);
            }
        }.start();

        Thread.sleep(100);

        for (int i = 0; i < 3; i++) {
            holdCpuThreads[i] = new Thread(new HoldCPUReadThread());
            holdCpuThreads[i].start();
        }

        Thread.sleep(10000);
        for (int i = 0; i < 3; i++) {
            holdCpuThreads[i].interrupt();
        }
    }

    static class  HoldCPUReadThread implements Runnable{

        @Override
        public void run() {
            long lockr = lock.readLock();
            System.out.println(Thread.currentThread().getId()+"获得read-lock");
            lock.unlockRead(lockr);
        }
    }
}


