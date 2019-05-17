package threadBasicMethod;

/**
 * JL
 * 2019/5/12  7:59
 *
 **/
public class ThreadJoin {
    static volatile int i = 0;

    public static  class PlusTask implements Runnable{
        @Override
        public void run() {
            for(int k =0;k<10000;k++)
                i++;
            System.out.println(Thread.currentThread().getName()+"execute success");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0;i<10;i++){
            threads[i]=new Thread(new PlusTask());
            // 线程在这里启动有可能，线程已经执行完了并没有执行join方法
            threads[i].start();
        }
        for (int i = 0;i<10;i++){
            System.out.println(i);
            // 这10个线程之间互相没有协作，只是main线程需要等待这10个线程执行完之后，才能继续执行
            threads[i].join();
        }
        System.out.print(i);
    }
}
