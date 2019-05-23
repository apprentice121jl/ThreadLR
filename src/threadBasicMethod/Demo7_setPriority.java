package threadBasicMethod;
/**
 * * setPriority()设置线程的优先级
 *
 */
public class Demo7_setPriority {

	public static void main(String[] args) {
		
		Thread  t1 = new Thread(){
			public void run(){
				for(int i =0;i < 100;i++)
				{
					System.out.println(getName()+"  aaaaa");
				}
			}
		};
		
		Thread  t2 = new Thread(){
			public void run(){
				for(int i =0;i < 100;i++)
				{
					System.out.println(getName()+"  bb");
				}
			}
		};
		t1.setPriority(Thread.MIN_PRIORITY); //设置最小的线程优先级
		t2.setPriority(Thread.MAX_PRIORITY);  //设置最大的线程优先级
		t1.start();
		t2.start();
	}

}
