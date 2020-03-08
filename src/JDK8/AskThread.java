package JDK8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * JL
 * 2020/2/23  12:46
 **/
public class AskThread implements Runnable{
    CompletableFuture<Integer> re = null;

    public AskThread(CompletableFuture<Integer> re){
        this.re = re;
    }
    @Override
    public void run() {
        int myRe = 0;
        try {
            myRe = re.get() * re.get();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(myRe);
    }

    public static Integer calc(Integer para){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }

    public static Integer calcByZero(Integer para){
        return para/0;
    }

    public static void main(String[] args) throws Exception {
        // 1.测试CompletableFuture
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        Thread.sleep(10000);
        // 当此行执行完时，AskThread线程才能继续执行
        future.complete(60);

        // 2.测试CompletableFuture的异步执行
        final CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(()->calc(50));
        System.out.println(cf.get());

        // CompletionStage接口的应用
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(()->calc(50))
                                     .thenApply((i)->Integer.toString(i))
                                     .thenApply((str)->"\""+str+"\"")
                                     .thenAccept(System.out::println);
        fu.get();
        // CompletableFuture的异常处理
        CompletableFuture<Void> comF = CompletableFuture.supplyAsync(()->calcByZero(50))
                                        .exceptionally(ex->{
                                            System.out.println(ex.toString());
                                            return 0;
                                        })
                                        .thenApply((i)->Integer.toString(i))
                                        .thenApply((str)->"\""+str+"\"")
                                        .thenAccept(System.out::println);


    }
}
