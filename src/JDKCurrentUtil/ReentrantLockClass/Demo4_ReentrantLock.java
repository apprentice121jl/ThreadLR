package JDKCurrentUtil.ReentrantLockClass;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo4_ReentrantLock {
	
	public static void main(String[] args) {
		
		final Printer4 p = new Printer4();
		
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					p.print1();
				}
			}
		}).start();
		
		new Thread() {
			public void run() {
				while(true) {
					p.print2();
				}
			}
		}.start();
	}

}



class Printer4 {
	ReentrantLock  r = new ReentrantLock();
	Condition  c1 = r.newCondition();  
	Condition  c2 = r.newCondition();
	private  int flag =1;
	public void print1()  {
		r.lock();
		if(flag != 1) {
			try {
				c1.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print("1");
		System.out.print("2");
		System.out.print("3");
		System.out.println();
		flag =2;
		c2.signal();
		r.unlock();
	}
	
	public void print2() {
		r.lock();
		if(flag != 2) {
			try {
				c2.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print("batMan");
		System.out.print("superMan");
		System.out.print("spiderMan");
		System.out.println();
		flag =1;
		c1.signal();
		r.unlock();
	}
	
}
