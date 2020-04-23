package JDKCurrentUtil.ThreadLocal;

import java.util.Random;
import java.util.concurrent.*;

public class GenerateRandomNum {

    public static final int GEN_COUNT = 10000000;

    public static final int THREAD_COUNT = 4;

    static ExecutorService exe = Executors.newFixedThreadPool(THREAD_COUNT);

    public static Random rnd = new Random(123);

    public static ThreadLocal<Random> tRnd = new ThreadLocal<Random>(){

        protected Random initialValue(){
            return new Random(123);
        }
    };


    public static class RndTask implements Callable<Long>{

        private int mode = 0;

        public RndTask(int mode){
            this.mode = mode;
        }

        Random  getRandom(){
            if(mode == 0){
                return rnd;
            }else{
                return tRnd.get();
            }
        }

        @Override
        public Long call() throws Exception {
            try{
                long b = System.currentTimeMillis();
                for (int i = 0; i < GEN_COUNT; i++) {
                        getRandom().nextInt();
                }
                long e = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() +" spend " + ( e -b ) + "ms");
                return e-b;
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }

        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Long>[] futs = new Future[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            futs[i] = exe.submit(new RndTask(0));
        }
        long totaltime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totaltime += futs[i].get();
        }
        System.out.println("多线程访问同一个Random实例："+totaltime+" ms");

        totaltime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            futs[i] = exe.submit(new RndTask(1));
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            totaltime += futs[i].get();
        }
        System.out.println("使用ThreadLocal包装Random实例："+totaltime+" ms");
        exe.shutdown();
    }
}
