package SynchronizedKeyWord;

/**
 * 在java中，Integer属于不变对象即对象一旦创建，是不可能被修改的，
 * 如果想要修改，就必须新建一个Integer对象，
 * 反编译之后可以看到：i++,使用的Integer.valueOf(i.intValue()+i)进行新对象的创建
 **/
public class SynchronizedError implements Runnable{
	
    public static Integer i = 0;

    static SynchronizedError instance = new SynchronizedError();

    @Override
    public void run() {
        for (int j=0;j<10000000;j++){
            synchronized (i){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
