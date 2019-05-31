package JDKCurrentUtil.threadCommunication.BooleanLock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class BooleanLockTest {

	private final Lock lock = new BooleanLock();
	
	public void syncMethod() {
		try {
			lock.lock();
			int nextInt = ThreadLocalRandom.current().nextInt(10);
			System.out.println(Thread.currentThread()+"get the lock.");
			TimeUnit.SECONDS.sleep(nextInt);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	
	public void syncMethodTime() throws TimeoutException {
		try {
			lock.lock(1000);
			int nextInt = ThreadLocalRandom.current().nextInt(10);
			System.out.println(Thread.currentThread()+"get the lock.");
			TimeUnit.SECONDS.sleep(nextInt);
		} catch (InterruptedException | TimeoutException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
			/*
			 *  // 测试BooleanLock的同步性
				BooleanLockTest blt = new BooleanLockTest();
				IntStream.range(0,10).mapToObj(i-> new Thread(blt::syncMethod)).forEach(Thread::start);
			*/
		
			/*// 测试BooleanLock的可中断性
			BooleanLockTest blt = new BooleanLockTest();
			new Thread(blt::syncMethod,"T1").start();
			TimeUnit.MILLISECONDS.sleep(2);
			Thread t2 = new Thread(blt::syncMethod,"T2");
			t2.start();
			TimeUnit.MILLISECONDS.sleep(10);
			t2.interrupt();*/
			
			// 阻塞线程可超时
			BooleanLockTest blt = new BooleanLockTest();
			new Thread(() -> {
				try {
					blt.syncMethodTime();
				} catch (TimeoutException e) {
					e.printStackTrace();
				}
			},"T1").start();
			TimeUnit.MILLISECONDS.sleep(10);
			Thread t2 = new Thread(() -> {
				try {
					blt.syncMethodTime();
				} catch (TimeoutException e) {
					e.printStackTrace();
				}
			},"T2");
			t2.start();
			TimeUnit.MILLISECONDS.sleep(10);
	}
}
