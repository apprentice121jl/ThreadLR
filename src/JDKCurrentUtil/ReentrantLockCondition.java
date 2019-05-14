package JDKCurrentUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JL
 * 2019/5/12  22:00
 * Condition#await():当线程使用此方法时，要求线程持有相关的重入锁，在此方法调用后，这个线程会释放这把锁
 * Condition#signal():当线程使用此方法时，要求线程持有相关的重入锁，在此方法调用后，记得调用unlock释放锁
 **/
public class ReentrantLockCondition implements  Runnable {
    public static  ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();


    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockCondition t = new ReentrantLockCondition();
        Thread t1 = new Thread(t);
        t1.start();
        Thread.sleep(2000);
        lock.lock();
        condition.signal();
        lock.unlock();

    }
}
