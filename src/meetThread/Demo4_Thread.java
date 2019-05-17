package meetThread;

public class Demo4_Thread {

	public static void main(String[] args) {
		//new Thread(){}这代表继承一个类
		//new Thread(){加上对方法的重写}这代表Thread类的子类对象
		new Thread(){ 
			@Override                                   //1.继承Thread类
			public void  run()                          //2.重写run方法
			{                                           //3.将要执行的代码写在run方法中
				for(int i = 0;i < 1999;i++)
				{
					System.out.println("aaaaaaaaa");
				}
			}
		}.start();                                       //4.开启线程
		
		//new  Runnable(){}这表示实现这个接口
		//new  Runnable(){实现run方法}这表示Runnable的子类对象
		new Thread(new Runnable()                       //1.将Runnable的子类对象，传递给Thread的构造方法
				{
					public void run()                   //2.重写run方法
					{
						for(int i = 0; i < 1000;i++)
						{
							System.out.println("bb");
						}
					}
			
				}
			   ).start();
	}

}
