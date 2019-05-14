package threadBasicMethod2.SynchronizedKeyWord;

/**
 * JL
 * 2019/5/12  11:48
 * 在java中，Integer属于不变对象即对象一旦创建，就不可能被修改，
 * 如果想要改变，就必须新建一个Integer对象，
 * 使用的Integer.valueOf(i.intValue()+i)进行新对象的创建
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
