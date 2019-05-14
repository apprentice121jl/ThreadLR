package threadBasicMethod2;

/**
 * JL
 * 2019/5/11  13:18
 **/
public class ThreadSuspendResume {
    public static  Object obj = new Object();

    static ChangeObject t1 = new ChangeObject("t1");
    static ChangeObject t2 = new ChangeObject("t2");
    public static  class ChangeObject extends Thread{
        public ChangeObject(String name) {
            super.setName(name);
        }

        public void  run(){
            synchronized (obj){
                System.out.println("in "+getName());
                Thread.currentThread().suspend();
                System.out.println(getName()+"  execute success");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }
}
