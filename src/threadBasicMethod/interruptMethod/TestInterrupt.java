package threadBasicMethod.interruptMethod;
/**
 * 
 * interrupt():
 * 				1.如果线程处于阻塞状态，调用此方法将中断标志位设置为true，但由于是阻塞状态会将中断状态清除，设置为false，并抛出InterruptedException异常
 * 				2.如果线程被阻塞在一个Selector选择器(NIO)中，那么通过interrupt()中断它时；线程的中断标记会被设置为true，并且它会立即从选择操作中返回。
 * 				3.如果不属于前面所说的情况，那么通过interrupt()中断线程时，中断标记会被设置为“true”。
 * isInterrupted():获取中断标志位的值
 * 
 */
public class TestInterrupt {
	
	public static void main(String[] args) {
		
		try{
			MyThread t1 = new MyThread("t1");
			System.out.println(t1.getName() +" ("+t1.getState()+") is new.");  
	        t1.start(); // 启动“线程t1”
	        System.out.println(t1.getName() +" ("+t1.getState()+") is started.");  
	        // 主线程休眠300ms，然后主线程给t1发“中断”指令。
	        Thread.sleep(300);
	        t1.interrupt();
	        System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted.");
	        // 主线程休眠300ms，然后查看t1的状态。
	        Thread.sleep(300);
	        System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted now.");
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
