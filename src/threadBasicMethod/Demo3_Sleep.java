package threadBasicMethod;
/**
 * Thread�еľ�̬������sleep����,�������ȥ˯��
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
					//��Ϊ����run����û���׳��쳣�����������runֻ�ܲ����쳣
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