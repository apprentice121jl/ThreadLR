package threadBasicMethod;
/**
 * setDaemon方法,设置守护线程
 * 
 * 垃圾回收线程是一个经典的守护线程，当程序中不再有任何运行的Thread,程序就不会再产生垃圾，
 * 垃圾回收器也就无事可做，所以当垃圾回收线程是JVM上仅剩的线程时，垃圾回收线程会自动离开。
 * 始终在低级别的状态中运行，用于实时监控和管理系统中的可回收资源。
 * 
 * 当JVM中所有的线程都是守护线程的时候，JVM就可以退出了；如果还有一个或以上的非守护线程则JVM不会退出
 * 以上话的引用地址：https://blog.csdn.net/m13666368773/article/details/7245675
 * 
 * 1.设置守护线程必须在线程start之前
 * 2.不设置守护线程，程序将一直执行,不会停止
 */
public class Demo4_setDaemon {
	public static void main(String[] args)
	{
		Thread t1 = new Thread(){
			public void run(){
				for(int i = 0;i < 2;i++)
				{
					System.out.println(getName()+"aaaaaa");
				}
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				while(true){
					System.out.println("I am alive");
	                try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
				}
			}
		};
		//设置了守护线程后，执行的过程即:当t1执行完之后,不管t2执行到何处了,都应该停止执行
		t2.setDaemon(true);    //将t2设置为守护线程;不设置守护线程程序将不会停止
		t1.start();
		t2.start();
	}
}
