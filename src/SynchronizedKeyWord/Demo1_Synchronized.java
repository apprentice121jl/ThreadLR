package SynchronizedKeyWord;

public class Demo1_Synchronized {
/**
 * 同步代码块
 * 1.什么情况下需要同步
	* 当多线程并发, 有多段代码同时执行时, 我们希望某一段代码执行的过程中CPU不要切换到其他线程工作. 这时就需要同步.
	* 如果两段代码是同步的, 那么同一时间只能执行一段, 在一段代码没执行结束之前, 不会执行另外一段代码.
* 2.同步代码块
	* 使用synchronized关键字加上一个锁对象来定义一段代码, 这就叫同步代码块;
	* 通过锁来保证同步代码块的原子性，即CPU进行切换时，只要获取不到锁就不执行
	* 多个同步代码块如果使用相同的锁对象, 那么他们就是同步的
 */
	public static void main(String[] args) 
	{
		final Printer p = new Printer();//声明一个普通对象在多线程中使用
		
		new Thread(){
			public void run(){
				while(true)
				{
					p.print1();
				}
			}
		}.start();
		
		new Thread(){
			public void run(){
				while(true)
				{
					p.print2();
				}
			}
		}.start();
	}

}

class Printer{
	
	Object obj = new Object();
	
	public void print1()
	{
		//同步代码块，锁机制，锁对象可以是任意的，但是锁对象不能用匿名对象，因为匿名对象不是同一个对象
		synchronized(obj)                                   
		{
			System.out.print("1");
			System.out.print("2");
			System.out.print("程");
			System.out.print("序");
			System.out.print("员");
			System.out.println();
		}
		
	}
	public void print2()
	{
		synchronized(obj)
		{
			System.out.print("3");
			System.out.print("3");
			System.out.print("5");
			System.out.print("6");
			System.out.println();
		}
	}
}