package threadBasicMethod;

/**
 * yield是静态方法
 * 
 * 让出cpu,礼让线程,可以再次进行CPU资源的竞争
 */
public class Demo6_Yield {

	public static void main(String[] args) {
		MyThread  t1 = new MyThread();
		t1.setName("thread-1");
		MyThread  t2 = new MyThread();
		t2.setName("thread-2");
		
		t1.start();
		t2.start();
	}

}
class MyThread extends Thread
{
	public void run(){
		for(int i = 0;i < 1000;i++)
		{
			if(i%10 == 0)
				Thread.yield();    //让出CPU
			System.out.println(getName()+"    "+i);
		}
	}
}
