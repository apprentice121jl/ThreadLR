package SynchronizedKeyWord;

public class Demo4_TicketRunnable {

	public static void main(String[] args) {
		Ticket2  tk = new Ticket2();
		new Thread(tk).start();              //注意：这里是通过一个对象实现了四个线程
		new Thread(tk).start();
		new Thread(tk).start();
		new Thread(tk).start();
		/*Thread t = new Thread(tk);        //多次启动一个线程是非法的
		t.start();
		t.start();*/
	}

}
class  Ticket2  implements Runnable
{
	private   int num = 100;         //不用加static，因为是通过一个Ticket2对象，来创建4个线程
	
	public  void run(){
		while(true)
		{
			synchronized(this)      //注：这里是通过实现Runnable接口，来实现run方法的，所以锁对象this是唯一的
			{
				if(num == 0)
					break;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"这是第"+num--+"号票");
			}
		}
		
	}

}
