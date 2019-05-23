package meetThread;
/**
 * 多线程程序实现的方式3
 */
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo6_Callable {

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		// 创建线程池对象
		ExecutorService pool = Executors.newFixedThreadPool(2);
	
		// submit可以执行Runnable对象或者Callable对象代表的线程
		Future<Integer> f1 = pool.submit(new MyCallable(100));
		Future<Integer> f2 = pool.submit(new MyCallable(200));
	
		// V get()
		Integer i1 = f1.get();   // 获取call返回的值 
		Integer i2 = f2.get();
	
		System.out.println(i1);
		System.out.println(i2);
	
		// 结束
		pool.shutdown();
	}
}
/**
 * 实现Callable接口中的call方法
 *
 */
class MyCallable implements Callable<Integer> {

		private int number;
	
		public MyCallable(int number) {
			this.number = number;
		}
	
		/**
		 * 与run方法相比：call有返回值，可以抛异常
		 */
		
		public Integer call() throws Exception {
			int sum = 0;
			for (int x = 1; x <= number; x++) {
				sum += x;
			}
			return sum;
		}
	
	}