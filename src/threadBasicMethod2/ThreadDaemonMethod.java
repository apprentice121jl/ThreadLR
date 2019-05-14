package threadBasicMethod2;

/**
 * JL
 * 2019/5/12  9:06
 *
 **/
public class ThreadDaemonMethod {

    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        // 1.设置守护线程必须在线程start之前
        // 2.不设置守护线程，程序将一直执行,不会停止
         t.setDaemon(true);
        t.start();
        Thread.sleep(2000);
    }
}
