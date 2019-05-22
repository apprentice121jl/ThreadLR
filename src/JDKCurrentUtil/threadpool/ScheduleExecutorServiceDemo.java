package JDKCurrentUtil.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * JL
 * 2019/5/15  22:34
 *
 * 如果任务本身抛出了异常，那么后续的所有周期执行都会被中断;
 * 需要做好异常处理
 **/
public class ScheduleExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService ses =
                Executors.newScheduledThreadPool(10);
        // 如果前面的任务没有完成，则调度不会启动
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println(System.currentTimeMillis()/1000);
                    int i = 1/0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },0,2, TimeUnit.SECONDS);
    }
}
