package JDKCurrentUtil;


import java.util.concurrent.locks.LockSupport;

/**
 * JL
 * 2019/5/14  22:43
 * LockSupport：不同于可重入锁，只存在0(park)和1(unpark)两种状态
 * LockSupport#park():
 *                    1.可以阻塞当前线程，能够支持中断的影响并且不会抛出InterruptedException异常，
 *                    只会默默的返回，可以从Thread#interrupted()方法获得中断标记
 *                    2.unpark()操作发生在park()之前，也可以使下一次的park()操作立即返回
 **/
public class LockSupportDemo {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");


    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        public void run(){
            synchronized (u){
                System.out.println("in "+getName());
                LockSupport.park();
                if(Thread.interrupted()){
                    System.out.println(getName()+"被中断了");
                }
            }
            System.out.println(getName()+"执行结束");
        }
    }
/*
    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();

    }*/
    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t2);


    }
}
