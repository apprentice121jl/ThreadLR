package JDKCurrentUtil.LockSupport;

import java.util.concurrent.locks.LockSupport;


/**
 * park():阻塞线程
 * unpark():唤醒线程
 * park()可以响应interrupt()
 * 
 * 如果线程调用park而阻塞的话，能够响应中断请求(中断状态被设置成true)，但是不会抛出InterruptedException
 * caution：验证
 */
public class TestPark {
	
	public static void main(String[] args) throws InterruptedException {
		
		final Thread t = new Thread() {
			public void run() {
				System.out.println("线程开始执行");
				LockSupport.park();
				// Thread.currentThread().isInterrupted()  只是判断中断状态，并未清除状态，所以LockSupport.park();依旧不起作用
				// Thread.interrupted() 获取中断状态的同时，将中断状态设置为false，所以LockSupport.park();起作用
				if(Thread.interrupted()) {
					System.out.println("进入中断判断");
					LockSupport.park();
					System.out.println("thread "+Thread.currentThread().getId()+" awake!");
				}
				System.out.println("线程结束执行");
			}
		};
		
		t.start();
		Thread.sleep(3000);
		System.out.println("rrrrr");
		t.interrupt();
		//Thread.sleep(10000);
		LockSupport.unpark(t);
		System.out.println("执行unpark");
	}
	
	
	

}
