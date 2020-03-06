package JDKCurrentUtil.threadpool.fork_join;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10000;
    private static final int GROUP = 1000;
    private long start;
    private long end;



    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end-start)<THRESHOLD;
        if(canCompute){
            for (long i = start; i <= end ; i++) {
                sum += i;
            }
        }else{
            // 分成GROUP=1000个小任务,获取步长
            long step = (end - start + 1)/GROUP;
            ArrayList<CountTask> subTasks = new ArrayList<>();
            long pos = start;
            for (int i = 0; i < GROUP; i++) {
                long lastOne = pos+step;
                // 下一次循环的起始位置
                pos = lastOne +1;
                // 对最后一步的判断
                if(lastOne > end) lastOne = end;
                CountTask subTask = new CountTask(pos,lastOne);
                subTasks.add(subTask);
                subTask.fork();
            }

            for ( CountTask t : subTasks) {
                sum += (long)t.join();
            }

        }
        return sum;
    }

    public static void main(String[] args) {
        long step = (100 - 22 + 1)/10;
        long pos = 22;
        for (int i = 0; i < 10; i++) {
            System.out.print(pos);
            long lastOne = pos + step;
            if (lastOne > 100) lastOne = 100;
            pos = lastOne + 1;
            System.out.println("  "+lastOne);
        }
        long total = 0;
        for (long i = 78; i <= 300000; i++) {
            total +=i;

        }
        System.out.println("total:"+total);

        // ForkJoin线程池，避免创建太多线程
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(78,300000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);

        try {
            // 如果没有结果，会在此处等在返回结果
            long res = result.get();
            System.out.println("sum12:"+res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
