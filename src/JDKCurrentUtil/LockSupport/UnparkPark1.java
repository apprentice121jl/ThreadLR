package JDKCurrentUtil.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.park():在不调用LockSupport.unpark(t)前，直接调用LockSupport.park()，线程将阻塞
 * 
 * LockSupport：不同于可重入锁，只存在0(park)和1(unpark)两种状态
 */
public class UnparkPark1 {

	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		LockSupport.park();
		// 多次unpark,只能设置为1,不能累加
		LockSupport.unpark(t);
		LockSupport.unpark(t);
		System.out.println("end");
	}
}
