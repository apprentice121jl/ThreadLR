package meetThread;

import util.TimeUtil;

/**
 * JVM是不是多线程的
 * 答案：JVM是多线程的
 * explanation:在主线程(main)中穿插有finalize方法的执行，所以说明JVM是多线程的。
 * 			         即主线程和垃圾回收线程会抢夺CPU资源，CPU会随机切换，所以会穿插执行
 */

public class Demo1_Thread {

	public static void main(String[] args) {// main函数本身就一条线程
		// 启动了垃圾回收线程
		for(int i = 0;i < 1000000;i++)
		{
			new Demo();
		}
		
		for(int i = 0;i < 100000;i++)
		{
			System.out.println(TimeUtil.intToStrWholeDate(System.currentTimeMillis()/1000L)+"主线程执行了！！");
		}
		System.out.println("-------------------------------------------------");
		System.out.println(1/0);//main函数本来就是一条线程,但是finalize仅仅只是一个方法不是一条线程
	}

}
class  Demo
{
	@Override
	public void finalize ()  
	{
		System.out.println(TimeUtil.intToStrWholeDate(System.currentTimeMillis()/1000L)+"=======清理了垃圾！！");
		
	}
}