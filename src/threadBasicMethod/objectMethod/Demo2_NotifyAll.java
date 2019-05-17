package threadBasicMethod.objectMethod;

/**
 * notifyAll这是JDK1.5版本前的解决方案
 * @author JL
 *
 */
public class Demo2_NotifyAll {

	public static void main(String[] args) {
		
		final Printer2 p = new Printer2();
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
		
		new Thread(){
			public void run(){
				while(true)
				{
					try {
						p.print3();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

}
/**
 * 1.在同步代码块中，用哪个对象锁，就用哪个对象调用wait方法
 * 2.为什么wait、notify、notifyAll方法，都定义在Object类中？
 *   	因为锁对象可以是任意对象，Object是所有类的基类，所以wait、notify、notifyAll方法需要定义在Object类中
 * 3.sleep和wait方法的区别？
	 * a.sleep方法必须传入参数，参数就是时间，时间到了自动醒来
	 *   wait方法可以传入参数也可以不传入参数，传入参数就是在参数的时间结束后等待，不传入参数就是直接等待
	 * b.sleep方法在同步函数或同步代码块中，不释放锁
	 *   wait方法在同步函数或同步代码块中，释放锁
	 * c.sleep是Thread类中的静态方法，wait是Object类中的方法
 *
 */
class Printer2
{
	private int  flag = 1;
	public   void print1() throws InterruptedException{ 
		
		synchronized(this)
		{
			while(flag != 1 )
			{
				System.out.println("我是1   flag："+flag);
				this.wait();         
			}
			System.out.print("1");
			System.out.print("2");
			System.out.print("程");
			System.out.print("序");
			System.out.print("员");
			System.out.println();
			flag = 2;
			//this.notify();                //随机唤醒一个等待的线程
			this.notifyAll();               //唤醒所有等待的线程
		}
	}
	public   void print2() throws InterruptedException{
		synchronized(this)
		{
			while(flag != 2)
			{
				System.out.println("我是2 flag："+flag);
				this.wait();
			}
			System.out.print("2");
			System.out.print("3");
			System.out.print("5");
			System.out.print("6");
			System.out.println();
			flag =3;
			//this.notify();
			this.notifyAll();
		}
	}
	public   void print3() throws InterruptedException{
		synchronized(this)
		{
			while(flag != 3)
			{
				System.out.println("我是3  flag："+flag);
				this.wait();
			}
			System.out.print("i");
			System.out.print("t");
			System.out.print("h");
			System.out.print("e");
			System.out.print("i");
			System.out.print("m");
			System.out.print("a");
			System.out.println();
			flag =1;
			//this.notify();
			this.notifyAll();
		}
	}
}
