package JDKCurrentUtil.threadpool;

import java.util.concurrent.*;

public class DivTask implements Runnable {

    int a,b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double v = a/b;
        System.out.println("value = "+ v);

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5 ; i++) {

            // Future<?> future = threadPoolExecutor.submit(new DivTask(100, i));
            // future.get();

            threadPoolExecutor.execute(new DivTask(100,i));

        }
    }
}
