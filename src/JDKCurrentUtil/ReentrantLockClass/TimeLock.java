package JDKCurrentUtil.ReentrantLockClass;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JL
 * 2019/5/12  21:21
 * tryLock：1.给定一个等待时间，超时自动放弃获取锁(可以响应中断)
 *          2.不给定时间，尝试进行获取锁，但不进行等待，获取到锁直接返回true，未获取到锁返回false(不响应中断)
 **/
public class TimeLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();


    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {// 对interrupt()可以进行响应
                Thread.sleep(7000);
                System.out.println(Thread.currentThread().getName()+"-acquire lock");
            } else {
                System.out.println(Thread.currentThread().getName()+"-get lock failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"-release lock ");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new TimeLock(),"t1");
        Thread t2 = new Thread(new TimeLock(),"t2");
        t1.start();
        t2.start();
        // t2.interrupt(); // 验证tryLock可以响应中断
    }
}
