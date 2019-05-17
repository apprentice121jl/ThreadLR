package threadBasicMethod.interruptMethod;

public class MyThread extends Thread {

	public MyThread(String name) {
		super(name);
	}
	
	public void run() {
		try {
			int i = 0;
			while(!isInterrupted()) {
				if(i == 2) {
					Thread.sleep(1000);
				}else {
					Thread.sleep(100);
				}
				i++;
				System.out.println(Thread.currentThread().getName()+" ("+this.getState()+")loop-"+i);
			}
		}catch(InterruptedException e) {
			System.out.println(Thread.currentThread().getName()+" ("+this.getState()+") catch InterruptedException");
		}
	}
}


