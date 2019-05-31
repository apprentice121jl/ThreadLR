package JDKCurrentUtil.threadCommunication.BooleanLock;

import java.util.List;
import java.util.concurrent.TimeoutException;


public interface Lock {

	// 与synchronize相比较，该方法可以响应中断
	void lock() throws InterruptedException;
	
	void lock(long mills) throws InterruptedException,TimeoutException;
	
	void unlock();
	
	List<Thread> getBlockedThreads();
}
