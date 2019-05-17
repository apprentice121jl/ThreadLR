package SynchronizedKeyWord;
/**
 * 同步代码块嵌套同步代码块，容易产生异常
 * @author JL
 */
public class Demo5_DeadLock {
	public static String left = "筷子左";
	public static String right = "筷子右";
	public static void main(String[] args) {
		new Thread(){
			public void run(){
				while(true)
				{
					synchronized(left)
					{
						System.out.println(getName()+"...拿到了"+left+"等待"+right);
						synchronized(right)
						{
							System.out.println(getName()+"...拿到了筷子右"+"开吃");
						}
					}
				}
			}
		}.start();
		
		
		new Thread(){
			public void run(){
				while(true)
				{
					synchronized(right)
					{
						System.out.println(getName()+"...拿到了"+right+"等待"+left);
						synchronized(left)
						{
							System.out.println(getName()+"...拿到了筷子左"+"开吃");
						}
					}
				}
			}
		}.start();
	}

}
