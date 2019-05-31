package JDKCurrentUtil.threadCommunication.BooleanLock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;


public class BooleanLock implements Lock {
	
	// 当前拥有锁的线程
	private Thread currentThread;
	
	// 判断锁是否已经被获取
	private boolean locked = false;
	
	// 用来存储哪些线程在获取锁时，进入了阻塞状态
	private final List<Thread> blockedList = new ArrayList<Thread>();

	@Override
	public void lock() throws InterruptedException {
		
		synchronized (this) {
			while(locked) {
				final Thread tempThread = Thread.currentThread();
				try {
					if(!blockedList.contains(tempThread)) {
						blockedList.add(tempThread);
					}
					this.wait();
				}catch(InterruptedException e) {
					// 当被中断时，删除阻塞队列里的当前线程
					blockedList.remove(tempThread);
					throw e;
				}
			}
			blockedList.remove(Thread.currentThread());
			this.locked = true;
			this.currentThread = Thread.currentThread();
		}
		
	}

	@Override
	public void lock(long mills) throws InterruptedException, TimeoutException {
		synchronized (this) 
		{
			if(mills <= 0) {
				this.lock();
			}else {
				long remainingMills = mills;
				long endMills = System.currentTimeMillis() +remainingMills;
				while(locked) {
					final Thread tempThread = Thread.currentThread();
					if(remainingMills <= 0) {
						blockedList.remove(tempThread);
						throw new TimeoutException();
					}
					try {
						if(!blockedList.contains(tempThread)) {
							blockedList.add(tempThread);
						}
						this.wait(remainingMills);
						remainingMills = endMills - System.currentTimeMillis();
					}catch(InterruptedException e) {
						blockedList.remove(tempThread);
						throw e;
					}
				}
				blockedList.remove(Thread.currentThread());
				this.locked = true;
				this.currentThread = Thread.currentThread();
			}
		}
	}

	@Override
	public void unlock() {
		synchronized (this) {
			if(currentThread == Thread.currentThread()) {
				this.locked = false;
				Optional.of(Thread.currentThread().getName()+" release the lock.").
									ifPresent(System.out::println);
				this.notifyAll();
			}
		}
	}

	@Override
	public List<Thread> getBlockedThreads() {
		return Collections.unmodifiableList(blockedList);
	}

}
