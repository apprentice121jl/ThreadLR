package JDKCurrentUtil.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * 判断LockSupport.parkNanos(long)在时间到达自动退出阻塞
 */
public class ParkNanosTest {

	public static void main(String[] args) {
		System.out.println("begin");
		Object obj = new Object();
		LockSupport.parkNanos(obj, 10000000000L);
		System.out.println("end");
	}
}
