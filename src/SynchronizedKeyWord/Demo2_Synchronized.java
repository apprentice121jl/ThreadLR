package SynchronizedKeyWord;

public class Demo2_Synchronized {
/**
 * 使用synchronized关键字修饰一个方法, 该方法中所有的代码都是同步的
 */
	public static void main(String[] args) {
		final Printer2 p = new Printer2();
		new Thread(){
			public void run(){
				while(true)
				{
					p.print1();
				}
			}
		}.start();
		
		new Thread(){
			public void run(){
				while(true)
				{
					p.print2();
				}
			}
		}.start();
		
	}

}
/*//运行的时候，注意解注释，两个类名相同的类，一个是静态方法的，一个是非静态方法的
class Printer2{
	//非静态的同步方法的锁对象是this
	public synchronized void print1(){           //同步方法只需在方法头上加上synchronized关键字
		System.out.print("1");
		System.out.print("2");
		System.out.print("��");
		System.out.print("��");
		System.out.print("Ա");
		System.out.println();
		
	}
	public  void print2(){
		synchronized(this)
		{
			System.out.print("2");
			System.out.print("3");
			System.out.print("5");
			System.out.print("6");
			System.out.println();
		}
	}
}*/
class Printer2{
	
	//静态的同步方法的锁对象是该类的字节码对象
	public static synchronized void print1(){    
		System.out.print("1");
		System.out.print("2");
		System.out.print("程");
		System.out.print("序");
		System.out.print("员");
		System.out.println();
		
	}
	
	Object obj = new Object();
	public  static void print2(){
		synchronized(Printer2.class)    //注意:静态方法中synchronized中的锁对象必须是静态的对象,普通对象不行
		{
			System.out.print("2");
			System.out.print("3");
			System.out.print("5");
			System.out.print("6");
			System.out.println();
		}
	}
}