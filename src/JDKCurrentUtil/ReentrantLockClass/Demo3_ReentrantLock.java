package JDKCurrentUtil.ReentrantLockClass;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 可重入锁的使用
 * @author JL
 *
 */
public class Demo3_ReentrantLock {

	public static void main(String[] args) {
		
		final Printer3 p = new Printer3();
		
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

class Printer3
{
	ReentrantLock  r = new ReentrantLock();
	Condition  c1 = r.newCondition();             //创建了监视器
	Condition  c2 = r.newCondition();
	Condition  c3 = r.newCondition();
	
	private int  flag = 1;
	
	public   void print1() throws InterruptedException{ 
		
		r.lock();                        //获取锁
			if(flag !=1)
			{
				c1.await();             //由于唤醒的是指定的线程，所以不用循环进行判断,同时释放了可重入锁
			}
			System.out.print("1");
			System.out.print("2");
			System.out.print("程");
			System.out.print("序");
			System.out.print("员");
			System.out.println();
			flag = 2;
			//this.notifyAll();           
			c2.signal();                  //唤醒指定的线程
		r.unlock();                       //释放锁
	}
	
	public  void print2() throws InterruptedException{
		r.lock();
			if(flag != 2)
			{
				c2.await();
			}
			System.out.print("2");
			System.out.print("3");
			System.out.print("5");
			System.out.print("6");
			System.out.println();
			flag =3;
			//this.notifyAll();
			c3.signal();                     //唤醒指定的线程
		r.unlock();
	}
	
	public  void print3() throws InterruptedException{
		r.lock();
			if(flag != 3)
			{
				c3.await();
			}
			System.out.print("h");
			System.out.print("e");
			System.out.print("l");
			System.out.print("l");
			System.out.print("o");
			System.out.print("b");
			System.out.print("a");
			System.out.println();
			flag =1;
			//this.notifyAll();
			c1.signal();                       //唤醒指定的线程
		r.unlock();
	}
}
