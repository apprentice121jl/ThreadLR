package threadBasicMethod.objectMethod;
/**
 * 等待唤醒机制
 * 此程序实现了，print1和print2，交替进行打印。
 */

public class Demo1_Notify {
	public static void main(String[] args)
	{
		final Printer p = new Printer();
		
		new Thread(){
			public void run(){
				while(true)
				{
					try {
						p.print1();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		new Thread(){
			public void run(){
				while(true)
				{
					try {
						p.print2();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}

class Printer
{
	private int  flag = 1;
	public void print1() throws InterruptedException{ 
		
		synchronized(this)   //this代表调用该方法的对象
		{
			if(flag != 1)
			{
				this.wait();        //当前线程等待，会释放锁
			}
			System.out.print("1");
			System.out.print("2");
			System.out.print("程");
			System.out.print("序");
			System.out.print("员");
			System.out.println();
			flag = 2;
			this.notify();           //随机唤醒一个等待的线程，没有等待的线程也可以执行唤醒
		}
	}
	
	
	public   void print2() throws InterruptedException{
		
		synchronized(this)
		{
			if(flag != 2)
			{
				this.wait();
			}
			System.out.print("2");
			System.out.print("3");
			System.out.print("5");
			System.out.print("6");
			System.out.println();
			flag =1;
			this.notify();
		}
	}
}