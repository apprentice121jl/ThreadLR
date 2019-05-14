package threadBasicMethod2;

/**
 * JL
 * 2019/5/10  23:19
 **/
public class ThreadMInterruptMethod {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            public void run(){
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupter!");
                        break;
                    }
                    try{
                        System.out.println("run continue");
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        System.out.println("Interrupted when sleep");
                        Thread.currentThread().interrupt();
                        if(Thread.interrupted()){// 判断是否被中断，并清除当前中断状态
                            System.out.println("reset interrupted state");
                        }
                    }
                    Thread.yield();
                }
            }
        };

        t.start();
        Thread.sleep(2000);
        t.interrupt();
    }


}
