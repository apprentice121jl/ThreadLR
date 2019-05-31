package JDKCurrentUtil.threadCommunication.synqueue;

import java.util.concurrent.TimeUnit;

/**
 * 
 * synchronized:不可被中断,即不能响应中断
 * 
 * 线程T2等待synchronized时，进行interrupt操作，执行到sleep时也会抛出InterruptedException异常
 * 
 *
 */
public class SynchronizedDefect {

	public synchronized void syncMethod() {
		System.out.println(Thread.currentThread().getName() + " begin !");
		try {
			/*TimeUnit.SECONDS.sleep(30);*/
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			if(Thread.currentThread().isInterrupted()) {
				System.out.println(Thread.currentThread().getName()+"is interrupted!");
			}
		}
		System.out.println(Thread.currentThread().getName() + " is over!");
	}

	public static void main(String[] args) throws InterruptedException {
		SynchronizedDefect defect = new SynchronizedDefect();
		Thread t1 = new Thread(defect::syncMethod, "T1");
		t1.start();

		TimeUnit.MILLISECONDS.sleep(2);
		Thread t2 = new Thread(defect::syncMethod, "T2");
		t2.start();
		TimeUnit.MILLISECONDS.sleep(2);
		t2.interrupt();
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("t2 interrupt:" + t2.isInterrupted());
		System.out.println("t1 state:" + t1.getState());
		System.out.println("t2 state:" + t2.getState());
	}

}
