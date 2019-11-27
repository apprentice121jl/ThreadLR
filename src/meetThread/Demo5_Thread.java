package meetThread;

/**
 * 线程的lambda表达式写法
 * 语法：( object str,....)[参数列表]   ->[箭头符号]     代码块或表达式
 */
public class Demo5_Thread {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for(int i = 1;i < 10;i++)
            {
                System.out.println("aaaa");
            }
        };
        new Thread(runnable,"线程1").start();

    }
}
