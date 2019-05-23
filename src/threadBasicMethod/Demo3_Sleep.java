package threadBasicMethod;
/**
 * Thread中的静态方法:sleep方法,会带着锁去睡觉
 * @author JL
 *
 */
public class Demo3_Sleep {

	public static void main(String[] args) 
	{
		new Thread(){
			public void run(){
				for(int i = 0;i < 20;i++)
				{
					// 因为父类run方法没有抛出异常，所以子类的run只能捕获异常
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(getName()+"aaaaa");
				}
			}
		}.start();
		
		new Thread(
					new Runnable(){
						public void run()
						{
							for(int i = 0;i < 20;i++)
							{
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								System.out.println(Thread.currentThread().getName()+"bbbbb");
							}
						}
					}
				).start();;
	}

}