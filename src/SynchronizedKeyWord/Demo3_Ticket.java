package SynchronizedKeyWord;

public class Demo3_Ticket {

	public static void main(String[] args) {
		//创建了多个线程，注意与实现Runnable接口，来创建线程的区别
		new Ticket().start();      
		new Ticket().start();
		new Ticket().start();
		new Ticket().start();
	}

}
class  Ticket extends Thread
{
	//由于是创建了多个Ticket对象,所以要使用static数据来实现数据的共享
	private  static  int num = 100;   //所有的线程共享此数据
	//如果用引用数据类型成员变量当做锁对象，必须是静态的.因为创建线程时,是new出了多个Ticket对象,如果不是静态的会导致锁对象不是同一个
	//private  static Object obj = new Object();  
	public  void run(){
		while(true)
		{
			synchronized(Ticket.class)      //字节码对象Ticket.class是唯一的
			{
				if(num == 0)
					break;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(getName()+"���ǵ�"+num--+"��Ʊ");
			}
		}
		
	}

}
