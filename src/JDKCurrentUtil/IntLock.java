package JDKCurrentUtil;

import java.util.concurrent.locks.ReentrantLock;

/**
 * JL
 * 2019/5/12  20:30
 **/
public class IntLock implements Runnable{

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock = 1;

    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

            try {
                if(lock==1){
                    lock1.lockInterruptibly();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.currentThread().getName()+"-sleep InterruptedException");
                    }
                    lock2.lockInterruptibly();
                }else{
                    lock2.lockInterruptibly();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.currentThread().getName()+"-sleep InterruptedException");
                        Thread.currentThread().interrupt();
                    }
                    lock1.lockInterruptibly();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName()+"-ReentrantLock InterruptedException");
            } finally {
                if(lock1.isHeldByCurrentThread()){
                    lock1.unlock();
                }
                if(lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getName()+":线程退出");
            }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new IntLock(1),"t1");
        Thread t2 = new Thread(new IntLock(2),"t2");
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();

    }
}
