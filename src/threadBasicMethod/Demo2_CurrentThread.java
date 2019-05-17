package threadBasicMethod;
//Thread中的静态方法，currentThread
//注意:currentThread方法在哪里都可以被直接调用
public class Demo2_CurrentThread {

	public static void main(String[] args) {
		
		new Thread("JL"){ //此处代表继承Thread类
			public void run(){
				System.out.println(getName()+"...qqqq");//直接使用了Thread类中的getName方法
			}
		}.start();
		
		
		//仍然是先创建了Thread对象才能启动线程
		new Thread(
				new Runnable(){
					public void run(){
						//内部使用了Thread的静态方法currentThread()
						System.out.println(Thread.currentThread().getName()+"....bbbbb");
					}
				}
				).start();
		//Thread.currentthread()获取当前正在执行的线程，此处获取的是主线程
		System.out.println(Thread.currentThread().getName());
	}
}
