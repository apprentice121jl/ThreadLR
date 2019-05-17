package meetThread;


public class Demo3_Thread {

	public static void main(String[] args) {
		Runnable  mr = new MyRunnable();         //3.定义一个Runnable的子类对象
		Thread   t  = new  Thread(mr);           //重点：4.Runnable子类对象传入Thread的构造函数
		t.start();                               //5.启动线程
		for(int i = 0;i < 1000;i++)
		{
			System.out.println("bbbbb"+i);
		}
	}
}

class  MyRunnable  implements Runnable       //1.定义一个类实现Runnable接口
{
	
	public void run() {                      //2.实现run方法
		for(int i = 0;i < 1000; i++)
		{
			System.out.println("aaaaaaaaaaaaa"+i);
		}
		
	}
}
