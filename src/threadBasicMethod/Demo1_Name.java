package threadBasicMethod;

public class Demo1_Name {

	/**
	 * 线程有一个默认的名字,例如Thread-0  、Thread-1
	 * 注意:这里都是继承了Thread,所以可以使用setName和getName方法
	 */
	public static void main(String[] args) {
		//  demo1();
		 demo2();
		
	}

	private static void demo1() 
	{
		new Thread("JL"){                        //通过构造函数来设置线程的名字
			public void run()
			{
				System.out.println(this.getName()+".....aaaaa");
			}
		}.start();
		
		new Thread("JL-1"){
			public void run()
			{
				System.out.println(this.getName()+".....ccccc");
			}
		}.start();
	}
	
	private static void demo2() {
		new Thread(){                        
			public void run(){
				this.setName("JL");              //通过setName方法来设置名字
				System.out.println(this.getName()+".....aaaaa");
			}
		}.start();
	}
	

}
