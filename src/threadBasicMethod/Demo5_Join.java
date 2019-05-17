package threadBasicMethod;

public class Demo5_Join {
/**
 * join(),当前线程暂停,等待指定的线程执行结束后,当前线程再继续
 * join(int), 可以等待指定的毫秒之后继续
 */
	public static void main(String[] args) 
	{
		final Thread  t1 = new Thread(){
			public void run(){
				for(int i = 0;i < 10000;i++)
				{
					System.out.println(getName()+"......aaaaa");
				}
			}
		};
		Thread  t2 = new Thread(){
			public void run(){
				for(int i = 1;i < 200;i++)
				{
					try {
						//匿名内部类在使用它所在的方法中的局部变量的时候，该变量必须声明为final
						t1.join();//当前线程暂停, 等待指定的线程执行结束后, 当前线程再继续
						System.out.println(t1.getName()+"-执行完成......ccccc");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(getName()+"......bb-"+i);
				}
			}
		};
		t1.start();
		t2.start();
		System.out.println("-------------------------------------------");
	}

}
