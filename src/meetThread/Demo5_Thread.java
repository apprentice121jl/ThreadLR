package meetThread;

/**
 * 线程的lambda表达式写法
 */
public class Demo5_Thread {

    public static void main(String[] args) {
       new Thread(
               ()->{
                   for(int i = 1;i < 10;i++){
                       System.out.println("aaaa");
                   }
               },"线程1"
       ).start();

    }
}
