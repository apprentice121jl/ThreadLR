package JDKCurrentUtil.threadCommunication.synqueue;

import java.util.concurrent.TimeUnit;

public class EventClient {

	public static void main(String[] args) {
		
		final EventQueue eventQueue = new EventQueue();	
		new Thread(() -> {
			while(true) {
				eventQueue.offer(new EventQueue.Event());
			}
		},"producer1").start();
		
		new Thread(() -> {
			while(true) {
				eventQueue.offer(new EventQueue.Event());
			}
		},"producer2").start();
		
		
		new Thread(() ->{
			while(true) {
				eventQueue.take();
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"consumer1").start();
		
		
		new Thread(() ->{
			while(true) {
				eventQueue.take();
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"consumer2").start();
	}
}
