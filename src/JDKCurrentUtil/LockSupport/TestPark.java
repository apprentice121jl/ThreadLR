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
	final static Thread t = new Thread("parkTestThread") {
		public void run() {
			System.out.println("线程开始执行");
			LockSupport.park();
			// 1.Thread.currentThread().isInterrupted()  只是判断中断状态，并未清除状态，所以LockSupport.park();不起作用
			//   注意将LockSupport.unpark(t);注释掉
			// 2.Thread.interrupted() 获取中断状态的同时，将中断状态设置为false，所以LockSupport.park();起作用
			if(Thread.interrupted()) {
				System.out.println("进入中断判断");
				LockSupport.park();
				System.out.println("thread "+Thread.currentThread().getId()+" awake!");
			}
			System.out.println("线程结束执行");
		}
	};
	public static void main(String[] args) throws InterruptedException {
		t.start();
		Thread.sleep(3000);
		System.out.println("rrrrr");
		t.interrupt();
		//Thread.sleep(30); // 睡眠一段时间再次unpark就不会卡在park了
		LockSupport.unpark(t);
		System.out.println("执行unpark");
	}
	
	
	

}
