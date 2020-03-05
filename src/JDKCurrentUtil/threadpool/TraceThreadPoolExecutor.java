package JDKCurrentUtil.threadpool;

import java.util.concurrent.*;

public class TraceThreadPoolExecutor extends ThreadPoolExecutor {


    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(wrap(task,clientTrace()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task,clientTrace()));
    }

    private Exception clientTrace(){
        return new Exception("Client stack trace");
    }


    private Runnable wrap(final Runnable task, final Exception clientStack){
        return ()->{
            try {
                task.run();
            } catch (Exception e) {
                clientStack.printStackTrace();
                throw e;
            }
        };
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor =
                new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5 ; i++) {

            // Future<?> future = threadPoolExecutor.submit(new DivTask(100, i));
            // future.get();

            threadPoolExecutor.execute(new DivTask(100,i));

        }
    }

}
