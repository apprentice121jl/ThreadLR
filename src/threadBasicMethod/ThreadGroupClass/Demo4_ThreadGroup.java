package threadBasicMethod.ThreadGroupClass;

public class Demo4_ThreadGroup {

	public static void main(String[] args) {
		 // demo1();
		 demo2();
	}

	private static void demo1() {
		//默认情况下，所有的线程都属于主线程组(main)
		MyRunnable  m = new MyRunnable();
		Thread t1 = new Thread(m,"张三");
		Thread t2 = new Thread(m,"李四");
		t1.start();
		t2.start();
	}
	
	private static void demo2() {
		ThreadGroup  tg = new ThreadGroup("我是一个新的线程组");     	//创建一个新的线程组
		MyRunnable  m = new MyRunnable();                        	//创建Runnable的子类对象
		Thread  t1 = new Thread(tg,m,"张三");                     	//创建线程，并将t1放入线程组tg中
		Thread  t2 = new Thread(tg,m,"李四");
		t1.start();
		t2.start();
	}

}

class  MyRunnable  implements Runnable
{
	public void run() {
		for(int i = 0;i < 3;i++)
		System.out.println(Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName() + "...." + i);
	}
}