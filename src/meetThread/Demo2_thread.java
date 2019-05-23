package meetThread;


/**
 * 即使main方法执行完毕，开启的线程也会继续执行
 * @author JL
 *
 */
public class Demo2_thread {

	public static void main(String[] args) throws InterruptedException 
	{
		MyThread  mt = new MyThread();
		//start方法使该线程开始执行，Java虚拟机调用该线程的 run方法
		mt.start();                            //3.启动线程，不能用mt.run()，这样不是启动的多线程;开启线程需要时间，所以会先输出bb
		
		for(int i = 0;i < 1000;i++)
		{
			System.out.println("bb"+i);
		}
		System.out.println("-----------------------------");
	}
}

class MyThread  extends Thread                  //1.继承Thread类
{
	public void run()                           //2.重写run方法，并将要执行的代码写在run方法中
	{
		for(int i = 0;i < 10000;i++)
		{
			System.out.println("aaaaaaaaaa"+i);
		}
	}
}